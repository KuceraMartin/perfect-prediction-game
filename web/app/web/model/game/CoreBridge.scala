package web.model.game

import play.api.libs.json.JsArray

import core.algorithms.Payoff


object CoreBridge {

  implicit def modelGametoCoreGame(g: Game): core.algorithms.Game =
    core.algorithms.Game(
      g.matrix.as[JsArray].value.map(row =>
        row.as[JsArray].value.map(cell =>
          Payoff(cell(0).as[Int], cell(1).as[Int])
        ).toVector
      ).toVector
    )

}
