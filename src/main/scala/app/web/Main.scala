package app.web

import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import app.algorithms.{NashianBestResponse, Payoff}
import app.algorithms.BestResponse._
import app.model.GameFacade

import scala.io.StdIn
import spray.json.DefaultJsonProtocol._

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
          val cols = colsOpt match {
            case Some(num) => num
            case None => rows
          }
          val id = GameFacade.generateId(rows, cols)
          val game = GameFacade.get(id)
          complete(GameTuple(game.matrix, id))
        }
      }
    },
    path("best-response") {
      get {
        parameters('id.as[String], 'rowStrategy.as[String]) { (id, rowStrategy) =>
          val game = GameFacade.get(id)
          NashianBestResponse(game, rowStrategy) match {
            case ColumnStrategy(strategy) => complete(Map("colStrategy" -> strategy))
            case RowStrategyNotFound => complete(StatusCodes.BadRequest -> Map("Invalid row strategy" -> rowStrategy))
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
