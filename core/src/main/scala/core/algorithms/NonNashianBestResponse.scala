package core.algorithms

import scala.annotation.tailrec


object NonNashianBestResponse extends BestResponse {

  private val Undefined = Payoff(Int.MinValue, Int.MinValue)

  override def apply(game: Game, rowStrategy: Int): Int = {
    def maxMin(matrix: Seq[Seq[Int]]): Int =
      matrix.map(
        _.map(v => if (v == Int.MinValue) Int.MaxValue else v)
         .min
      )
        .map(v => if (v == Int.MaxValue) Int.MinValue else v)
        .max

    @tailrec
    def eliminate(matrix: Seq[Seq[Payoff]]): Seq[Seq[Payoff]] = {
      val rowMin = maxMin(matrix.map(_.map(_.row)))
      val colMin = maxMin(matrix.map(_.map(_.column)).transpose)
      val newMatrix = matrix.map(_.map(p => if (p.row >= rowMin && p.column >= colMin) p else Undefined))
      val availableInRow = newMatrix(rowStrategy).exists(_ != Undefined)
      val canEliminate = newMatrix.flatten.count(_ != Undefined) > 1
      if (availableInRow) {
        if (canEliminate) eliminate(newMatrix)
        else newMatrix
      } else matrix
    }

    val newGame = Game(eliminate(game.matrix))
    NashianBestResponse(newGame, rowStrategy)
  }

}
