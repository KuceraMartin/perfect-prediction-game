package web

import scala.io.StdIn

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import spray.json.DefaultJsonProtocol._

import core.GameFacade
import core.algorithms.BestResponse.ColumnStrategy
import core.algorithms.BestResponse.RowStrategyNotFound
import core.algorithms.NashianBestResponse
import core.algorithms.Payoff


object Main extends App {


  case class GameTuple(matrix: Map[String, Map[String, Payoff]], id: String)


  implicit val system = ActorSystem(Behaviors.empty, "my-system")
  implicit val executionContext = system.executionContext

  implicit val payoffFormat = jsonFormat2(Payoff)
  implicit val gameResponseFormat = jsonFormat2(GameTuple)

  val route = concat(
    path("new-game") {
      get {
        parameters('rows.as[Int].withDefault(3), 'cols.as[Int].?) { (rows, colsOpt) =>
          val cols = colsOpt.getOrElse(rows)
          val (game, id) = GameFacade.generateWithId(rows, cols)
          complete(GameTuple(game.matrix, id))
        }
      }
    },
    path("best-response") {
      get {
        parameters('id.as[String], 'rowStrategy.as[String]) { (id, rowStrategy) =>
          GameFacade.get(id) match {
            case Some(game) =>
              NashianBestResponse(game, rowStrategy) match {
                case ColumnStrategy(strategy) => complete(Map("colStrategy" -> strategy))
                case RowStrategyNotFound => complete(StatusCodes.BadRequest -> Map("Invalid row strategy" -> rowStrategy))
              }
            case None => complete(StatusCodes.NotFound, Map("Game id not found" -> id))
          }
        }
      }
    }
  )

  val bindingFuture = Http().newServerAt("localhost", 8080).bind(route)

  println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ => system.terminate()) // and shutdown when done

}
