package core.algorithms

import scala.annotation.tailrec


object NonNashianBestResponse extends BestResponse {

  private val Undefined = Payoff(Int.MinValue, Int.MinValue)


  override def apply(game: Game, rowStrategy: Int): Int = {
    @tailrec
    def loop(game: Game): Game = {
      val newGame = eliminate(game)
      val availableInRow = newGame(rowStrategy).exists(notEliminated)
      val canEliminate = availableCells(newGame) > 1
      if (availableInRow) {
        if (canEliminate) loop(newGame)
        else newGame
      } else game
    }
    NashianBestResponse(loop(game), rowStrategy)
  }


  @tailrec
  def pte(game: Game): Option[(Int, Int)] = {
    val newGame = eliminate(game)
    val available = availableCells(newGame)
    if (available > 1) pte(newGame)
    else if (available == 1) {
      val row = newGame.indexWhere(_.exists(notEliminated))
      val col = newGame(row).indexWhere(notEliminated)
      Some(row, col)
    } else None
  }


  private def notEliminated(payoff: Payoff): Boolean = payoff != Undefined


  private def availableCells(game: Game): Int = game.flatten.count(notEliminated)


  private def eliminate(game: Game): Game = {
    val rowMin = maxMin(game.map(_.map(_.row)))
    val colMin = maxMin(game.map(_.map(_.column)).transpose)
    game.map(_.map(p => if (p.row >= rowMin && p.column >= colMin) p else Undefined))
  }


  private def maxMin(matrix: Seq[Seq[Int]]): Int =
    matrix.map(
      _.map(v => if (v == Int.MinValue) Int.MaxValue else v)
       .min
    )
    .map(v => if (v == Int.MaxValue) Int.MinValue else v)
    .max

}
