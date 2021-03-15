package endpoints.v1

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents

import core.GameFacade
import core.algorithms.BestResponse.ColumnStrategy
import core.algorithms.BestResponse.RowStrategyNotFound
import core.algorithms.NashianBestResponse


class Play @Inject() (val controllerComponents: ControllerComponents) extends BaseController {

  def index(id: String, rowStrategy: String) = Action {
    GameFacade.get(id) match {
      case Some(game) =>
        NashianBestResponse(game, rowStrategy) match {
          case ColumnStrategy(strategy) => Ok(Json.toJson(Map("colStrategy" -> strategy)))
          case RowStrategyNotFound => BadRequest(Json.toJson(Map("Invalid row strategy" -> rowStrategy)))
        }
      case None => NotFound(Json.toJson(Map("Game id not found" -> id)))
    }
  }

}
