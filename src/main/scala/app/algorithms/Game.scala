package app.algorithms

case class Payoff (row: Int, column: Int) {

  override def toString: String = s"$row, $column"

}

case class Game[K] (matrix: Map[K, Map[K, Payoff]]) {

  def isWithoutTies: Boolean = {
    val payoffs = matrix.values.flatMap(_.values)
    val rp = payoffs.map(_.row).toSet
    val cp = payoffs.map(_.column).toSet
    rp.size == payoffs.size && cp.size == payoffs.size
  }

  override def toString: String = {
    val w = (
      matrix.keys.map(_.toString.length) ++
      matrix.values.head.keys.map(_.toString.length) ++
      matrix.values.flatMap(_.values).map(_.toString.length)
    ).max
    "\t" +
    matrix.values.head.keys.map(_.toString.padTo(w, ' ')).mkString("\t") +
    "\n" +
    matrix.map { case (row: K, cols: Map[K, Payoff]) =>
      s"$row\t" +
      cols.values.map(_.toString.padTo(w, ' ')).mkString("\t")
    }.mkString("\n")
  }

}
