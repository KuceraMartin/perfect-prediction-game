package endpoints.v1

import javax.inject.Inject

import scala.util.Random

import play.api.libs.json.Json
import play.api.libs.json.Writes
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents

import core.algorithms.Game
import core.algorithms.GameGenerator
import core.algorithms.Payoff


class NewGame @Inject() (val controllerComponents: ControllerComponents) extends BaseController {

  implicit val payoffWrites = new Writes[Payoff] {
    override def writes(p: Payoff) = Json.arr(
      p.row,
      p.column,
    )
  }

  implicit val gameTupleWrites = new Writes[(Game, String)] {
    def writes(tuple: (Game, String)) = Json.obj(
      "matrix" -> tuple._1.matrix,
      "id" -> tuple._2,
    )
  }

  def index(rows: Int, colsOpt: Option[Int]) = Action {
    val cols = colsOpt.getOrElse(rows)
    val seed = Random.nextInt(Int.MaxValue)
    val id = s"$rows$cols${seed.toHexString.toUpperCase}"
    val game = GameGenerator(new Random(seed))(rows, cols)
    Ok(Json.toJson((game, id)))
  }

}
