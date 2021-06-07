package core.algorithms

import scala.annotation.tailrec


object PerfectlyTransparentEquilibrium {

  @tailrec
  def apply(game: Game): Seq[Profile] = {
    val newGame = IndividualRationality.eliminate(game)
    if (newGame == game)
      newGame.zipWithIndex.flatMap { case (cols: Seq[Payoff], row: Int) =>
        cols.zipWithIndex.flatMap { case (payoff: Payoff, col: Int) =>
          if (payoff == Payoff.Undefined) Nil
          else List(Profile(row, col))
        }
      }
    else apply(newGame)
  }

  def notEliminated(payoff: Payoff): Boolean = payoff != Payoff.Undefined

}
