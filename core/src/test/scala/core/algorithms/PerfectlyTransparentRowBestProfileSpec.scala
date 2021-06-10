package core.algorithms

import core.UnitSpec


class PerfectlyTransparentRowBestProfileSpec extends UnitSpec {

  "PerfectlyTransparentRowBestProfile.Weak" should "work correctly" in {
    val game = Game(List(
      List((6, 6), (8, 1), (4, 5)),
      List((1, 8), (7, 7), (5, 5)),
      List((5, 4), (5, 5), (5, 5)),
    ))
    PerfectlyTransparentRowBestProfile.Weak(game) should be (List(Profile(1, 1)))
  }

}
