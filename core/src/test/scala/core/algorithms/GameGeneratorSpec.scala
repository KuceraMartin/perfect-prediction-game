package core.algorithms

import scala.util.Random

import core.UnitSpec


class GameGeneratorSpec extends UnitSpec {

  "GameGenerator" should "generate a 2x2 game" in {
    val random = new Random(1)
    val generate = GameGenerator(random)
    val game = generate(List(1, 2), List(1, 2))
    game.matrix should be (Map(
      1 -> Map(
        1 -> Payoff(4, 4),
        2 -> Payoff(1, 1),
      ),
      2 -> Map(
        1 -> Payoff(2, 3),
        2 -> Payoff(3, 2),
      ),
    ))
  }

  it should "generate a 3x4 game" in {
    val random = new Random(1)
    val generate = GameGenerator(random)
    val game = generate('A' to 'C', 'D' to 'G')
    game.matrix should be (Map(
      'A' -> Map(
        'D' -> Payoff(6, 6),
        'E' -> Payoff(1, 11),
        'F' -> Payoff(5, 3),
        'G' -> Payoff(4, 8),
      ),
      'B' -> Map(
        'D' -> Payoff(12, 12),
        'E' -> Payoff(3, 7),
        'F' -> Payoff(9, 9),
        'G' -> Payoff(2, 5),
      ),
      'C' -> Map(
        'D' -> Payoff(11, 1),
        'E' -> Payoff(8, 4),
        'F' -> Payoff(7, 10),
        'G' -> Payoff(10, 2),
      ),
    ))
  }

}
