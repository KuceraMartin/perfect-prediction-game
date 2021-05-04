package analysis

import org.apache.spark.sql.SparkSession

import core.algorithms.Game
import core.algorithms.NonNashianBestResponse


case class OutputRow(y: Seq[Seq[Seq[Int]]], P: Seq[Seq[Int]], NBR: Seq[Seq[Int]], NBC: Seq[Seq[Int]])


object PteBestResponseMerge {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("PTE Best Response Merge").getOrCreate()
    import spark.implicits._

    val fileIn = args.head
    val fileOut = args(1)
    println(s"Analyzing `$fileIn`")
    spark.read.schema(InputFormat.schema).json(fileIn)
      .map { plainRow =>
        val row = InputFormat.fromSparkRow(plainRow)
        val nbr = bestRowNnbr(row.game)
        val nbc = bestRowNnbr(row.game.transpose).swap
        OutputRow(
          plainRow.getAs[Seq[Seq[Seq[Int]]]]("y"),
          plainRow.getAs[Seq[Seq[Int]]]("P"),
          List(List(nbr._1, nbr._2)),
          List(List(nbc._1, nbc._2)),
        )
      }
      .write.json(fileOut)
    spark.stop()
  }

  private def bestRowNnbr(game: Game): (Int, Int) =
    game.indices
        .map(i => (i, NonNashianBestResponse(game, i)))
        .maxBy(br => game(br._1)(br._2).row)

}
