package analysis

import org.apache.spark.sql.SparkSession

import core.algorithms.Game
import core.algorithms.IndividualRationality
import core.algorithms.MinimaxRationalizability
import core.algorithms.PerfectlyTransparentBestResponse
import core.algorithms.Profile


case class OutputRow(
  y: Seq[Seq[Seq[Int]]], // matrix
  P: Seq[Seq[Int]], // PTE
  PTRBP: Seq[Seq[Int]], // Perfectly transparent row-best profile
  PTCBT: Seq[Seq[Int]], // Perfectly transparent column-best profile
  PTBRE: Seq[Seq[Int]], // Perfectly transparent best response equilibrium
  IR: Seq[Seq[Int]], // Individually Rational profiles
  MR: Seq[Seq[Int]], // Minimax-Rationalizable profiles
)


object ComputeEquilibria {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("Compute Equilibria").getOrCreate()
    import spark.implicits._

    val fileIn = args.head
    val fileOut = args(1)
    println(s"Analyzing `$fileIn`")
    spark.read.schema(InputFormat.schema).json(fileIn)
      .map { plainRow =>
        val row = InputFormat.fromSparkRow(plainRow)
        val ptrbp = ptBestRow(row.game)
        val ptcbp = ptBestRow(row.game.transpose).swap
        OutputRow(
          plainRow.getAs[Seq[Seq[Seq[Int]]]]("y"),
          plainRow.getAs[Seq[Seq[Int]]]("P"),
          List(List(ptrbp._1, ptrbp._2)),
          List(List(ptcbp._1, ptcbp._2)),
          PerfectlyTransparentBestResponse.equilibria(row.game).map(profileToSeq),
          IndividualRationality.all(row.game).map(profileToSeq),
          MinimaxRationalizability.all(row.game).map(profileToSeq),
        )
      }
      .write.json(fileOut)
    spark.stop()
  }

  private def ptBestRow(game: Game): (Int, Int) =
    game.indices
        .map(i => (i, PerfectlyTransparentBestResponse(game, i)))
        .maxBy(br => game(br._1)(br._2).row)

  private def profileToSeq(profile: Profile): Seq[Int] = List(profile.row, profile.col)

}
