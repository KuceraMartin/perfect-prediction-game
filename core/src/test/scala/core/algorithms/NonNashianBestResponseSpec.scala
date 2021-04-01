package core.algorithms

import core.UnitSpec


class NonNashianBestResponseSpec extends UnitSpec {

  "Non-Nashian Best response in prisoner's dilemma to `cooperate`" should "be `cooperate`" in {
    NonNashianBestResponse(GameFixtures.prisonersDilemma, 0) should be (0)
  }
  "Non-Nashian Best response in prisoner's dilemma to `defect`" should "be `defect`" in {
    NonNashianBestResponse(GameFixtures.prisonersDilemma, 1) should be (1)
  }


  "Non-Nashian best response in game of chicken to `swerve`" should "be `straight`" in {
    NonNashianBestResponse(GameFixtures.gameOfChicken, 0) should be (1)
  }
  "Non-Nashian best response in game of chicken to `straight`" should "be `swerve`" in {
    NonNashianBestResponse(GameFixtures.gameOfChicken, 1) should be (0)
  }


  "Non-Nashian best response in the public goods game to `nothing`" should "be `nothing`" in {
    NonNashianBestResponse(GameFixtures.publicGoodsGame, 0) should be (0)
  }
  "Non-Nashian best response in the public goods game to `half`" should "be `half`" in {
    NonNashianBestResponse(GameFixtures.publicGoodsGame, 1) should be (1)
  }
  "Non-Nashian best response in the public goods game to `everything`" should "be `everything`" in {
    NonNashianBestResponse(GameFixtures.publicGoodsGame, 2) should be (2)
  }

}
