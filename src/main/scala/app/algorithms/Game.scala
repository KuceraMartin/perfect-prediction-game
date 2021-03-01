package app.algorithms

case class Payoff (row: Int, column: Int)

case class Game[K] (matrix: Map[K, Map[K, Payoff]]) {

  def isWithoutTies: Boolean = {
    val payoffs = matrix.values.flatMap(_.values)
    val rp = payoffs.map(_.row).toSet
    val cp = payoffs.map(_.column).toSet
    rp.size == payoffs.size && cp.size == payoffs.size
  }

}
