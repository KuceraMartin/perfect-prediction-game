package core.algorithms


case class Payoff(row: Int, column: Int) {

  override def toString: String = s"$row, $column"

  def swap: Payoff = Payoff(column, row)

}


object Payoff {

  val Undefined: Payoff = Payoff(Int.MinValue, Int.MinValue)

  implicit def fromTuple(tuple: (Int, Int)): Payoff = Payoff(tuple._1, tuple._2)

}


case class Profile(row: Int, col: Int)


object Profile {

  implicit def fromTuple(tuple: (Int, Int)): Profile = Profile(tuple._1, tuple._2)

}


case class Game(matrix: Seq[Seq[Payoff]]) extends Seq[Seq[Payoff]] {


  override def length: Int = matrix.length

  override def apply(idx: Int): Seq[Payoff] = matrix(idx)

  override def iterator: Iterator[Seq[Payoff]] = matrix.iterator

  def rowIndices: Range = indices

  def colIndices: Range = head.indices

  def width: Int = rowIndices.length

  def height: Int = colIndices.length

  def isWithoutTies: Boolean = {
    val payoffs = flatten
    val rp = payoffs.map(_.row).toSet
    val cp = payoffs.map(_.column).toSet
    rp.size == payoffs.size && cp.size == payoffs.size
  }

  def transpose: Game = transpose(_.map(_.swap))

  def rowMax: Seq[Int] = map(_.map(_.row).max)

  def colMax: Seq[Int] = transpose.rowMax

  def rowMaxMin: Int =
    map(
      _.map(p => if (p == Payoff.Undefined) Int.MaxValue else p.row)
       .min
    )
    .map(v => if (v == Int.MaxValue) Int.MinValue else v)
    .max

  def colMaxMin: Int = transpose.rowMaxMin

}


object Game {

  implicit def fromMatrix(matrix: Seq[Seq[Payoff]]): Game = Game(matrix)

}
