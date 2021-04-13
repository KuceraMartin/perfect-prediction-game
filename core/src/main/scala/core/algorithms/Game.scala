package core.algorithms


case class Payoff(row: Int, column: Int) {

  override def toString: String = s"$row, $column"

}


object Payoff {

  implicit def fromTuple(tuple: (Int, Int)): Payoff = Payoff(tuple._1, tuple._2)

}


case class Game(matrix: Seq[Seq[Payoff]]) {

  def isWithoutTies: Boolean = {
    val payoffs = matrix.flatten
    val rp = payoffs.map(_.row).toSet
    val cp = payoffs.map(_.column).toSet
    rp.size == payoffs.size && cp.size == payoffs.size
  }

}
