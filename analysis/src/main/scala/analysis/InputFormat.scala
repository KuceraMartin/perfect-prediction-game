package analysis

import org.apache.spark.sql.types.ArrayType
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StructType

import core.algorithms.Game
import core.algorithms.Payoff


object InputFormat {

  val schema = new StructType()
    .add("x", IntegerType, false) // id
    .add("z", IntegerType, false) // dim?
    .add("y", ArrayType(ArrayType(ArrayType(IntegerType, false), false), false), false) // matrix
    .add("P", ArrayType(ArrayType(IntegerType, false), false), false) // Perfectly Transparent Equilibria
    .add("N", ArrayType(ArrayType(IntegerType, false), false), false) // Nash Equilibria

  case class Row(id: Int, game: Game, pte: Option[(Int, Int)], ne: Seq[(Int, Int)])

  implicit def fromSparkRow(row: org.apache.spark.sql.Row): Row = {
    val seq = row.getAs[Seq[Seq[Int]]]("P")
    Row(
      id = row.getAs[Int]("x"),
      game = row.getAs[Seq[Seq[Seq[Int]]]]("y").map(_.map(v => Payoff(v(0), v(1)))),
      pte = if (seq.nonEmpty) Some(seq.head(0), seq.head(1)) else None,
      ne = row.getAs[Seq[Seq[Int]]]("N").map(s => (s(0), s(1)))
    )
  }

}
