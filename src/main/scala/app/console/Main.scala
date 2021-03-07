package app.console

import app.algorithms.NashianBestResponse
import app.model.GameFacade

import scala.annotation.tailrec
import scala.io.StdIn.readLine

object Main extends App {

  val numRows = if (args.length >= 1) args(0).toInt else 3
  val numCols = if (args.length >= 2) args(1).toInt else numRows

  val game = GameFacade.generate(numRows, numCols)
  println(game)

  print("\nChoose your strategy: ")
  val s = readStrategy()

  val r = NashianBestResponse(game, s)
  println(s"Best response: $r")

  @tailrec
  private def readStrategy(): String = {
    val s = readLine
    if (game.matrix.contains(s)) s
    else {
      print("Please choose a valid strategy: ")
      readStrategy()
    }
  }

}
