package endpoints.v1

import javax.inject.Inject

import play.api.libs.json.Json
import play.api.libs.json.Writes
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents

import core.GameFacade
import core.algorithms.Game
import core.algorithms.Payoff


class NewGame @Inject() (val controllerComponents: ControllerComponents) extends BaseController {

  implicit val payoffWrites = new Writes[Payoff] {
    override def writes(p: Payoff) = Json.obj(
      "row" -> p.row,
      "column" -> p.column,
    )
  }

  implicit val gameTupleWrites = new Writes[(Game[String], String)] {
    def writes(tuple: (Game[String], String)) = Json.obj(
      "matrix" -> tuple._1.matrix,
      "id" -> tuple._2,
    )
  }

  def index(rows: Int, colsOpt: Option[Int]) = Action {
    val cols = colsOpt.getOrElse(rows)
    val tuple = GameFacade.generateWithId(rows, cols)
    Ok(Json.toJson(tuple))
  }

}
