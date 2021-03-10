package app.console

import app.algorithms.BestResponse._
import app.algorithms.NashianBestResponse
import app.model.GameFacade

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object Main extends App {

  val numRows = if (args.length >= 1) args(0).toInt else 3
  val numCols = if (args.length >= 2) args(1).toInt else numRows

  val game = GameFacade.generate(numRows, numCols)
  println(game)
  println()

  val r = readAndProcess("Choose your strategy: ")
  println(s"Best response: $r")

  @tailrec
  private def readAndProcess(prompt: String): String = {
    print(prompt)
    val row = readLine
    NashianBestResponse(game, row) match {
      case ColumnStrategy(strategy) => strategy
      case RowStrategyNotFound => readAndProcess("Please choose a valid strategy: ")
    }
  }

}
