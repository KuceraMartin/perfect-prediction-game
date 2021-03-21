package web.endpoints.v1

import javax.inject.Inject

import scala.util.Failure
import scala.util.Random
import scala.util.Success
import scala.util.Try

import play.api.libs.json.Json
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents

import core.algorithms.GameGenerator
import core.algorithms.NashianBestResponse


class Play @Inject() (val controllerComponents: ControllerComponents) extends BaseController {

  def index(id: String, rowStrategy: Int) = Action {
    val rows = id(0).asDigit
    val cols = id(1).asDigit
    Try(Integer.parseInt(id.drop(2), 16)) match {
      case Success(seed) => {
        val game = GameGenerator(new Random(seed))(rows, cols)
        val colStrategy = NashianBestResponse(game, rowStrategy)
        Ok(Json.toJson(Map("colStrategy" -> colStrategy)))
      }
      case Failure(_) => NotFound(Json.toJson(Map("Game id not found" -> id)))
    }
  }

}
