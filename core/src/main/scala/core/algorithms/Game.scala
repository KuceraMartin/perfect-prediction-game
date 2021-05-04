package core.algorithms

import scala.collection.GenTraversableOnce


case class Payoff(row: Int, column: Int) {

  override def toString: String = s"$row, $column"

  def swap: Payoff = Payoff(column, row)

}


object Payoff {

  implicit def fromTuple(tuple: (Int, Int)): Payoff = Payoff(tuple._1, tuple._2)

}

case class Game(matrix: Seq[Seq[Payoff]]) extends Seq[Seq[Payoff]] {


  override def length: Int = matrix.length

  override def apply(idx: Int): Seq[Payoff] = matrix(idx)

  override def iterator: Iterator[Seq[Payoff]] = matrix.iterator

  def isWithoutTies: Boolean = {
    val payoffs = flatten
    val rp = payoffs.map(_.row).toSet
    val cp = payoffs.map(_.column).toSet
    rp.size == payoffs.size && cp.size == payoffs.size
  }

  def transpose: Game = transpose(_.map(_.swap))

}


object Game {

  implicit def fromMatrix(matrix: Seq[Seq[Payoff]]): Game = Game(matrix)

}
