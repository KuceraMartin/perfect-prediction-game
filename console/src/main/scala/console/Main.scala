package console

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.util.Random

import core.algorithms.Game
import core.algorithms.GameGenerator
import core.algorithms.NashianBestResponse


object Main extends App {

  val numRows = if (args.length >= 1) args(0).toInt else 3
  val numCols = if (args.length >= 2) args(1).toInt else numRows

  val rowFirst = 'A'
  val colFirst = ('A'.toInt + numRows).toChar
  val colEnd = (colFirst.toInt + numCols).toChar

  val rows = (rowFirst until colFirst).map(_.toString)
  val cols = (colFirst until colEnd).map(_.toString)

  val game = GameGenerator(Random)(numRows, numCols)
  printGame(game)

  val r = readRowStrategy("Choose your strategy: ")
  val c = NashianBestResponse(game, r)
  println(s"Best response: ${cols(c)}")

  @tailrec
  private def readRowStrategy(prompt: String): Int = {
    val row = rows.indexOf(readLine(prompt))
    if (row >= 0) row
    else readRowStrategy("Please choose a valid strategy: ")
  }

  private def printGame(game: Game): Unit = {
    val w = (
        rows.map(_.length) ++
        cols.map(_.length) ++
        game.matrix.flatten.map(_.toString.length)
      ).max
    print("\t")
    for (col <- cols) {
      print(col.padTo(w, ' ') + "\t")
    }
    println()
    for (i <- 0 until rows) {
      print(s"${rows(i)}\t")
      print(game.matrix(i).map(_.toString.padTo(w, ' ')).mkString("\t"))
    }
    println()
  }

}
