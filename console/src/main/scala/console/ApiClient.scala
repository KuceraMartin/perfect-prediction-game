package console

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import play.api.libs.json.JsError
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.ws.DefaultBodyWritables.writeableOf_String
import play.api.libs.ws.JsonBodyReadables._
import play.api.libs.ws.StandaloneWSClient
import play.api.libs.ws.StandaloneWSRequest
import structures.Game
import structures.Result
import structures.Stats
import structures.User
import structures.request.Play


class ApiClient (ws: StandaloneWSClient, apiUrl: String = "http://localhost:9000") (implicit ec: ExecutionContext) {

  def newUser(): Future[User] =
    req("new-user")
      .post("")
      .map { response =>
        val body = response.body[JsValue]
        Json.fromJson[User](body) match {
          case JsSuccess(user, _) => user
          case e @ JsError(_) => throw new RuntimeException(e.toString)
        }
      }


  def newGame(rows: Int, cols: Int): Future[Game] =
    req("new-game")
      .addQueryStringParameters(
        "rows" -> rows.toString,
        "cols" -> cols.toString,
      )
      .post("")
      .map { response =>
      val body = response.body[JsValue]
      Json.fromJson[Game](body) match {
        case JsSuccess(game, _) => game
        case e @ JsError(_) => throw new RuntimeException(e.toString)
      }
    }


  def play(user: User, game: Game, body: Play): Future[Result] =
    req("play")
      .withQueryStringParameters(
        "gameId" -> game.id.toString,
      )
      .withHttpHeaders(
        "userId" -> user.id.toString,
      )
      .post(Json.stringify(Json.toJson(body)))
      .map { response =>
        val body = response.body[JsValue]
        Json.fromJson[Result](body) match {
          case JsSuccess(result, _) => result
          case e @ JsError(_) => throw new RuntimeException(e.toString)
        }
      }


  def stats(user: User): Future[Stats] =
    req("stats")
      .withHttpHeaders(
        "userId" -> user.id.toString,
      )
      .get()
      .map {response =>
        val body = response.body[JsValue]
        Json.fromJson[Stats](body) match {
          case JsSuccess(stats, _) => stats
          case e @ JsError(_) => throw new RuntimeException(e.toString)
        }
      }


  private def req(path: String, version: Int = 1): StandaloneWSRequest = ws.url(s"$apiUrl/v$version/$path")

}
