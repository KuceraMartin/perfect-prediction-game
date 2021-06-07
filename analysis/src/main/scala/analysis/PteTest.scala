package analysis

import org.apache.spark.sql.SparkSession

import core.algorithms.PerfectlyTransparentBestResponse


object PteTest {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("PTE Best Response Merge").getOrCreate()
    import spark.implicits._

    val fileIn = args.head
    println(s"Analyzing `$fileIn`")
    val ne = spark.read.schema(InputFormat.schema).json(fileIn)
         .filter { plainRow =>
            val row = InputFormat.fromSparkRow(plainRow)
            PerfectlyTransparentBestResponse.pte(row.game).toList != row.pte
         }
    println(ne.count())
    ne.show(5, false)
    spark.stop()
  }

}
