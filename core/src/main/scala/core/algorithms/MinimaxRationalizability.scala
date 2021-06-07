package core.algorithms

import scala.annotation.tailrec


object MinimaxRationalizability extends Eliminator {

  @tailrec
  override def eliminate(game: Game): Game = {
    val rowMax = game.rowMax
    val colMax = game.colMax
    val rowMaxMin = game.rowMaxMin
    val colMaxMin = game.colMaxMin
    val newGame = Game(
      Vector.tabulate(game.width, game.height) { (i, j) =>
        if (rowMax(i) < rowMaxMin || colMax(j) < colMaxMin) Payoff.Undefined
        else game(i)(j)
      }
    )
    if (newGame == game) game
    else eliminate(newGame)
  }

}
