package analysis

import org.apache.spark.sql.SparkSession

import core.algorithms.IndividualRationality
import core.algorithms.MinimaxRationalizability
import core.algorithms.PerfectlyTransparentColBestProfile
import core.algorithms.PerfectlyTransparentBestResponse
import core.algorithms.Profile


case class OutputRow(
  y: Seq[Seq[Seq[Int]]], // matrix
  P: Seq[Seq[Int]], // PTE
  PTRBP: Seq[Seq[Int]], // Perfectly transparent row-best profile
  PTCBP: Seq[Seq[Int]], // Perfectly transparent column-best profile
  PTBPE: Seq[Seq[Int]], // Perfectly transparent best profile equilibrium
  SPTRBP: Seq[Seq[Int]], // Perfectly transparent row-best profile (strict)
  SPTCBP: Seq[Seq[Int]], // Perfectly transparent column-best profile (strict)
  SPTBPE: Seq[Seq[Int]], // Perfectly transparent best profile equilibrium (strict
  PTBRE: Seq[Seq[Int]], // Perfectly transparent best response equilibrium
  SPTBRE: Seq[Seq[Int]], // Perfectly transparent best response equilibrium (strict)
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
    spark.read.schema(Format.schema).json(fileIn)
      .map { plainRow =>
        val row = Format.fromSparkRow(plainRow)
        val ptrbp = PerfectlyTransparentColBestProfile.Weak(row.game.transpose).map(p => Format.profileToSeq(p.swap))
        val ptcbp = PerfectlyTransparentColBestProfile.Weak(row.game).map(Format.profileToSeq)
        val sptrbp = PerfectlyTransparentColBestProfile.Strict(row.game.transpose).map(p => Format.profileToSeq(p.swap))
        val sptcbp = PerfectlyTransparentColBestProfile.Strict(row.game).map(Format.profileToSeq)
        OutputRow(
          plainRow.getAs[Seq[Seq[Seq[Int]]]]("y"),
          plainRow.getAs[Seq[Seq[Int]]]("P"),
          ptrbp,
          ptcbp,
          ptrbp.intersect(ptcbp),
          sptrbp,
          sptcbp,
          sptrbp.intersect(sptcbp),
          PerfectlyTransparentBestResponse.Weak.equilibria(row.game).map(Format.profileToSeq),
          PerfectlyTransparentBestResponse.Strict.equilibria(row.game).map(Format.profileToSeq),
          IndividualRationality.all(row.game).map(Format.profileToSeq),
          MinimaxRationalizability.all(row.game).map(Format.profileToSeq),
        )
      }
      .write.json(fileOut)
    spark.stop()
  }

}
