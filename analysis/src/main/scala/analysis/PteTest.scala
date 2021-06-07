package analysis

import org.apache.spark.sql.SparkSession

import core.algorithms.PerfectlyTransparentEquilibrium

object PteTest {

  case class OutputRow(
    y: Seq[Seq[Seq[Int]]], // matrix
    P: Seq[Seq[Int]], // PTE (theirs)
    P2: Seq[Seq[Int]], // PTE (ours)
  )

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("PTE Test").getOrCreate()
    import spark.implicits._

    val fileIn = args.head
    println(s"Analyzing `$fileIn`")
    val ne = spark.read.schema(Format.schema).json(fileIn)
        .map{ plainRow =>
          val row = Format.fromSparkRow(plainRow)
          OutputRow(
            plainRow.getAs[Seq[Seq[Seq[Int]]]]("y"),
            plainRow.getAs[Seq[Seq[Int]]]("P"),
            PerfectlyTransparentEquilibrium(row.game).map(Format.profileToSeq),
          )
        }
        .filter { row =>
          row.P.toSet != row.P2.toSet
        }
    println(ne.count())
    ne.show(5, false)
    spark.stop()
  }

}
