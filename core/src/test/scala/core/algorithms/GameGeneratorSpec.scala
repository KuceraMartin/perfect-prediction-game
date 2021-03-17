package core.algorithms

import scala.util.Random

import core.UnitSpec


class GameGeneratorSpec extends UnitSpec {

  "GameGenerator" should "generate a 2x2 game" in {
    val random = new Random(1)
    val generate = GameGenerator(random)
    val game = generate(2, 2)
    game.matrix should be (Vector(
      Vector(
        Payoff(4, 4),
        Payoff(1, 1),
      ),
      Vector(
        (2, 3),
        (3, 2),
      ),
    ))
  }

  it should "generate a 3x4 game" in {
    val random = new Random(1)
    val generate = GameGenerator(random)
    val game = generate(3, 4)
    game.matrix should be (Vector(
      Vector(
        Payoff(6, 6),
        Payoff(1, 11),
        Payoff(5, 3),
        Payoff(4, 8),
      ),
      Vector(
        Payoff(12, 12),
        Payoff(3, 7),
        Payoff(9, 9),
        Payoff(2, 5),
      ),
      Vector(
        Payoff(11, 1),
        Payoff(8, 4),
        Payoff(7, 10),
        Payoff(10, 2),
      ),
    ))
  }

}
