package analysis

import org.apache.spark.sql.types.ArrayType
import org.apache.spark.sql.types.IntegerType
import org.apache.spark.sql.types.StructType

import core.algorithms.Game
import core.algorithms.Payoff
import core.algorithms.Profile


object Format {

  val schema = new StructType()
    .add("x", IntegerType, false) // id
    .add("z", IntegerType, false) // dim?
    .add("y", ArrayType(ArrayType(ArrayType(IntegerType, false), false), false), false) // matrix
    .add("P", ArrayType(ArrayType(IntegerType, false), false), false) // Perfectly Transparent Equilibria

  case class Row(game: Game, pte: Seq[Profile])

  implicit def fromSparkRow(row: org.apache.spark.sql.Row): Row = {
    val seq = row.getAs[Seq[Seq[Int]]]("P")
    Row(
      game = row.getAs[Seq[Seq[Seq[Int]]]]("y").map(_.map(v => Payoff(v(0), v(1)))),
      pte = if (seq.nonEmpty) List((seq.head(0), seq.head(1))) else Nil,
    )
  }

  def profileToSeq(profile: Profile): Seq[Int] = List(profile.row, profile.col)

}
