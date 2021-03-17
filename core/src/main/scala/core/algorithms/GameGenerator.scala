package core.algorithms

import scala.util.Random


class GameGenerator(random: Random) {

  def apply(rows: Int, cols: Int): Game = {
    val payoffs = (1 to (rows * cols)).toList
    val rowPayoffs = random.shuffle(payoffs)
    val colPayoffs = random.shuffle(payoffs)
    val matrix = Vector.tabulate(rows, cols)((r, c) => Payoff(rowPayoffs(r), colPayoffs(c)))
    Game(matrix)
  }

}

object GameGenerator {

  def apply(random: Random) = new GameGenerator(random)

}
