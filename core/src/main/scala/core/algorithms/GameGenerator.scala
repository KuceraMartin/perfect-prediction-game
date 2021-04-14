package core.algorithms

import scala.util.Random


class GameGenerator(random: Random) {

  def apply(rows: Int, cols: Int): Game = {
    val cnt = rows * cols
    val payoffs = (-cnt / 2 to cnt / 2).toList.filterNot(cnt % 2 == 0 && _ == 0)
    val rowPayoffs = random.shuffle(payoffs)
    val colPayoffs = random.shuffle(payoffs)
    val matrix = Vector.tabulate(rows, cols) { (r, c) =>
      val i = r * cols + c
      Payoff(rowPayoffs(i), colPayoffs(i))
    }
    Game(matrix)
  }

}

object GameGenerator {

  def apply(random: Random) = new GameGenerator(random)

}
