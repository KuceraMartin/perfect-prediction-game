package core.algorithms

import scala.annotation.tailrec


object NonNashianBestResponse extends BestResponse {

  override def apply(game: Game, rowStrategy: Int): Int = {
    def maxMin(matrix: Seq[Seq[Int]]): Int = matrix.map(_.min).max

    @tailrec
    def eliminate(matrix: Seq[Seq[Payoff]]): Seq[Seq[Payoff]] = {
      val rowMin = maxMin(matrix.map(_.map(_.row)))
      val colMin = maxMin(matrix.map(_.map(_.column)).transpose)
      val newMatrix = matrix.map(_.map(p => if (p.row >= rowMin && p.column >= colMin) p else Payoff(Int.MaxValue, Int.MaxValue)))
      if (newMatrix(rowStrategy).exists(_ != Payoff(Int.MaxValue, Int.MaxValue))) eliminate(newMatrix)
      else matrix.map(_.map(p => if (p == Payoff(Int.MaxValue, Int.MaxValue)) Payoff(Int.MinValue, Int.MinValue) else p))
    }

    val newGame = Game(eliminate(game.matrix))
    NashianBestResponse(newGame, rowStrategy)
  }

}
