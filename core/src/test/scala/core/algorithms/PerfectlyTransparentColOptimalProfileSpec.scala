package core.algorithms

import core.UnitSpec


class PerfectlyTransparentColOptimalProfileSpec extends UnitSpec {

  val game = Game(List(
    List((2, 1), (6, 4), (0, 7)),
    List((4, 7), (2, 2), (0, 8)),
    List((3, 5), (6, 2), (2, 2)),
  ))

  "PerfectlyTransparentColBestProfile.Weak" should "work correctly" in {
    PerfectlyTransparentColOptimalProfile.Weak(game) should be (List(Profile(1, 0)))
    PerfectlyTransparentColOptimalProfile.Weak(game.transpose) should be (List(Profile(1, 0), Profile(1, 2)))
  }

  "PerfectlyTransparentColBestProfile.Strict" should "work correctly" in {
    PerfectlyTransparentColOptimalProfile.Strict(game) should be (List(Profile(1, 0)))
    PerfectlyTransparentColOptimalProfile.Strict(game.transpose) should be (Nil)
  }

}
