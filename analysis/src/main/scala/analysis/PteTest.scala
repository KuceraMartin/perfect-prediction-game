package analysis

import analysis.InputFormat.Row
import org.apache.spark.sql.SparkSession

import core.algorithms.Game
import core.algorithms.NonNashianBestResponse
import core.algorithms.Payoff


object PteTest {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("PTE Best Response Merge").getOrCreate()
    import spark.implicits._

    val fileIn = args.head
    println(s"Analyzing `$fileIn`")
    val ne = spark.read.schema(InputFormat.schema).json(fileIn)
         .filter { plainRow =>
            val row = InputFormat.fromSparkRow(plainRow)
             NonNashianBestResponse.pte(row.game) != row.pte
         }
    println(ne.count())
    ne.show(5, false)
    spark.stop()
  }

}
