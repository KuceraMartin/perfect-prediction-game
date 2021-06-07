package core.algorithms

import scala.annotation.tailrec


object PerfectlyTransparentBestResponse extends BestResponse {

  override def apply(game: Game, rowStrategy: Int): Int = {
    @tailrec
    def loop(oldGame: Game, newGame: Game): Game = {
      val availableInRow = newGame(rowStrategy).exists(notEliminated)
      val canEliminate = availableCells(newGame) > 1
      if (availableInRow) {
        if (canEliminate) loop(newGame, IndividualRationality.eliminate(newGame))
        else newGame
      } else oldGame
    }
    NashianBestResponse(loop(game, game), rowStrategy)
  }


  @tailrec
  def pte(game: Game): Option[Profile] = {
    val newGame = IndividualRationality.eliminate(game)
    val available = availableCells(newGame)
    if (available > 1) pte(newGame)
    else if (available == 1) {
      val row = newGame.indexWhere(_.exists(notEliminated))
      val col = newGame(row).indexWhere(notEliminated)
      Some(Profile(row, col))
    } else None
  }


  private def notEliminated(payoff: Payoff): Boolean = payoff != Payoff.Undefined


  private def availableCells(game: Game): Int = game.flatten.count(notEliminated)

}
