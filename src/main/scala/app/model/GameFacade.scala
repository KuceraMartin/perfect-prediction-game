package app.model

import app.algorithms.{Game, GameGenerator}

import scala.util.Random

object GameFacade {

  def generate(numRows: Int, numCols: Int, seed: Int = Random.nextInt()): Game[String] = {
    require(numRows >= 1 && numRows <= 9, s"Required 1--9 rows, got $numRows.")
    require(numCols >= 1 && numCols <= 9, s"Required 1--9 cols, got $numCols.")

    val rowFirst = 'A'
    val colFirst = ('A'.toInt + numRows).toChar
    val colEnd = (colFirst.toInt + numCols).toChar

    val rows = (rowFirst until colFirst).map(_.toString)
    val cols = (colFirst until colEnd).map(_.toString)

    val random = new Random(seed)
    GameGenerator(random)(rows, cols)
  }

}
