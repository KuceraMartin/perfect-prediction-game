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
        Payoff(2, 2),
        Payoff(-2, -2),
      ),
      Vector(
        Payoff(-1, 1),
        Payoff(1, -1),
      ),
    ))
  }


  it should "generate a 3x3 game" in {
    val random = new Random(1)
    val generate = GameGenerator(random)
    val game = generate(3, 3)
    game.matrix should be (Vector(
      Vector(
        Payoff(1, 0),
        Payoff(4, 2),
        Payoff(-2, -1),
      ),
      Vector(
        Payoff(3, -4),
        Payoff(0, -2),
        Payoff(-1, 4),
      ),
      Vector(
        Payoff(-3, 3),
        Payoff(-4, 1),
        Payoff(2, -3),
      ),
    ))
  }


  it should "generate a 3x4 game" in {
    val random = new Random(1)
    val generate = GameGenerator(random)
    val game = generate(3, 4)
    game.matrix should be (Vector(
      Vector(
        Payoff(-1, -1),
        Payoff(-6, 5),
        Payoff(-2, -4),
        Payoff(-3, 2),
      ),
      Vector(
        Payoff(6, 6),
        Payoff(-4, 1),
        Payoff(3, 3),
        Payoff(-5, -2),
      ),
      Vector(
        Payoff(5, -6),
        Payoff(2, -3),
        Payoff(1, 4),
        Payoff(4, -5),
      ),
    ))
  }

}
