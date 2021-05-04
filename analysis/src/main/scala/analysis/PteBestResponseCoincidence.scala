package analysis

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.size


object PteBestResponseCoincidence {

  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder.appName("PTE Best Response Coincidence").getOrCreate()
    import spark.implicits._

    val fileIn = args.head
    println(s"Analyzing `$fileIn`")
    val data = spark.read.json(fileIn)
    val all = data.count()
    val withPte = data.filter(size($"P") >= 1).count()
    val pteEqNbr = data.filter($"P" === $"NBR").count()
    val pteEqNbc = data.filter($"P" === $"NBC").count()
    val nbrEqNbc = data.filter($"NBR" === $"NBC").count()
    val pteEqNbrEqNbc = data.filter($"P" === $"NBR" && $"P" === $"NBC").count()
    val pteNeNbrNeNbc = data.filter(size($"P") >= 1 && $"P" =!= $"NBR" && $"P" =!= $"NBC").count()
    println(s"all: $all")
    println(s"PTE: $withPte")
    println(s"PTE = NBR: $pteEqNbr")
    println(s"PTE = NBC: $pteEqNbc")
    println(s"NBR = NBC: $nbrEqNbc")
    println(s"PTE = NBR = NBC: $pteEqNbrEqNbc")
    println(s"PTE != NBR, PTE != NBC: $pteNeNbrNeNbc")
    spark.stop()
  }

}
