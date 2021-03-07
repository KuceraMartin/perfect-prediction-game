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

  def generateId(numRows: Int, numCols: Int, seed: Int = Random.nextInt(Int.MaxValue)) =
    s"$numRows$numCols${seed.toHexString.toUpperCase}"

  def get(id: String): Game[String] = {
    require(id.length >= 3)

    val numRows = id(0).asDigit
    val numCols = id(1).asDigit
    val seed = Integer.parseInt(id.drop(2), 16)
    generate(numRows, numCols, seed)
  }

}
