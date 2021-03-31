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
import structures.Game
import structures.Result
import structures.User

class ApiClient (ws: StandaloneWSClient, apiUrl: String = "http://localhost:9000")(implicit ec: ExecutionContext) {

  def newUser(): Future[User] =
    ws.url(s"$apiUrl/v1/new-user")
      .post("")
      .map { response =>
        val body = response.body[JsValue]
        Json.fromJson[User](body) match {
          case JsSuccess(user, _) => user
          case e @ JsError(_) => throw new RuntimeException(e.toString)
        }
      }


  def newGame(rows: Int, cols: Int): Future[Game] =
    ws.url(s"$apiUrl/v1/new-game")
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


  def play(user: User, game: Game, rowStrategy: Int): Future[Result] =
    ws.url(s"$apiUrl/v1/play")
      .withQueryStringParameters(
        "gameId" -> game.id.toString,
        "gameType" -> "non-nash",
        "rowStrategy" -> rowStrategy.toString,
      )
      .withHttpHeaders(
        "userId" -> user.id.toString,
      )
      .post("")
      .map { response =>
        val body = response.body[JsValue]
        Json.fromJson[Result](body) match {
          case JsSuccess(result, _) => result
          case e @ JsError(_) => throw new RuntimeException(e.toString)
        }
      }

}
