package core.algorithms


case class Payoff(row: Int, column: Int) {

  override def toString: String = s"$row, $column"

}


case class Game(matrix: Seq[Seq[Payoff]]) {

  def isWithoutTies: Boolean = {
    val payoffs = matrix.flatten
    val rp = payoffs.map(_.row).toSet
    val cp = payoffs.map(_.column).toSet
    rp.size == payoffs.size && cp.size == payoffs.size
  }

}
