package analysis

import scala.math.Ordering.Implicits.seqDerivedOrdering

import org.apache.spark.sql.SparkSession

import core.algorithms.IndividualRationality
import core.algorithms.MinimaxRationalizability
import core.algorithms.PerfectlyTransparentColOptimalProfile
import core.algorithms.PerfectlyTransparentBestResponse
import core.algorithms.PerfectlyTransparentRowBestProfile


case class OutputRow(
  y: Seq[Seq[Seq[Int]]], // matrix
  P: Seq[Seq[Int]], // PTE
  PTROP: Seq[Seq[Int]], // Perfectly transparent row-optimal profile
  PTCOP: Seq[Seq[Int]], // Perfectly transparent column-optimal profile
  PTOPE: Seq[Seq[Int]], // Perfectly transparent optimal profile equilibrium
  SPTROP: Seq[Seq[Int]], // Perfectly transparent row-optimal profile (strict)
  SPTCOP: Seq[Seq[Int]], // Perfectly transparent column-optimal profile (strict)
  SPTOPE: Seq[Seq[Int]], // Perfectly transparent optimal profile equilibrium (strict)
  PTRBP: Seq[Seq[Int]], // Perfectly transparent row-best profile
  PTCBP: Seq[Seq[Int]], // Perfectly transparent column-best profile
  PTBPE: Seq[Seq[Int]], // Perfectly transparent best profile equilibrium
  SPTRBP: Seq[Seq[Int]], // Perfectly transparent row-best profile (strict)
  SPTCBP: Seq[Seq[Int]], // Perfectly transparent column-best profile (strict)
  SPTBPE: Seq[Seq[Int]], // Perfectly transparent best profile equilibrium (strict)
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
        val ptrop = PerfectlyTransparentColOptimalProfile.Weak(row.game.transpose).map(p => Format.profileToSeq(p.swap)).sorted
        val ptcop = PerfectlyTransparentColOptimalProfile.Weak(row.game).map(Format.profileToSeq).sorted
        val sptrop = PerfectlyTransparentColOptimalProfile.Strict(row.game.transpose).map(p => Format.profileToSeq(p.swap)).sorted
        val sptcop = PerfectlyTransparentColOptimalProfile.Strict(row.game).map(Format.profileToSeq).sorted
        val ptrbp = PerfectlyTransparentRowBestProfile.Weak(row.game).map(Format.profileToSeq).sorted
        val ptcbp = PerfectlyTransparentRowBestProfile.Weak(row.game.transpose).map(p => Format.profileToSeq(p.swap)).sorted
        val sptrbp = PerfectlyTransparentRowBestProfile.Strict(row.game).map(Format.profileToSeq).sorted
        val sptcbp = PerfectlyTransparentRowBestProfile.Strict(row.game.transpose).map(p => Format.profileToSeq(p.swap)).sorted
        OutputRow(
          plainRow.getAs[Seq[Seq[Seq[Int]]]]("y"),
          plainRow.getAs[Seq[Seq[Int]]]("P"),
          ptrop,
          ptcop,
          ptrop.intersect(ptcop),
          sptrop,
          sptcop,
          sptrop.intersect(sptcop),
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
