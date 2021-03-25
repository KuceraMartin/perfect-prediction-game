package web.endpoints.v1

import java.time.LocalDateTime
import javax.inject.Inject

import scala.concurrent.ExecutionContext

import play.api.libs.json.Json
import play.api.libs.json.Writes
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents

import web.model.game.Game
import web.model.game.GameService


class NewGame @Inject() (
  gameService: GameService,
  protected val controllerComponents: ControllerComponents
) (implicit executionContext: ExecutionContext) extends BaseController {

  implicit val gameWrites = new Writes[Game] {
    override def writes(g: Game) = Json.obj(
      "id" -> g.id,
      "matrix" -> g.matrix,
    )
  }


  def index(rows: Int, colsOpt: Option[Int]) = Action.async {
    val cols = colsOpt.getOrElse(rows)
    gameService.generate(rows, cols)(LocalDateTime.now()) map { game =>
      Ok(Json.toJson(game))
    }
  }

}
