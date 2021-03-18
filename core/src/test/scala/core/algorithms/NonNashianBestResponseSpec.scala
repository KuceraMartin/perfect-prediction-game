package core.algorithms

import core.UnitSpec


class NonNashianBestResponseSpec extends UnitSpec {

  "Non-Nashian Best response in prisoner's dilemma to `cooperate`" should "be `cooperate`" in {
    NonNashianBestResponse(GameFixtures.prisonersDilemma, 0) should be (0)
  }
  "Non-Nashian Best response in prisoner's dilemma to `defect`" should "be `defect`" in {
    NonNashianBestResponse(GameFixtures.prisonersDilemma, 1) should be (1)
  }


  "Non-Nashian best response in matching pennies to `heads`" should "be `tails`" in {
    NonNashianBestResponse(GameFixtures.matchingPennies, 0) should be (1)
  }
  "Non-Nashian best response in matching pennies to `tails`" should "be `heads`" in {
    NonNashianBestResponse(GameFixtures.matchingPennies, 1) should be (0)
  }


  "Non-Nashian best response in battle of sexes to `football`" should "be `football`" in {
    NonNashianBestResponse(GameFixtures.battleOfSexes, 0) should be (0)
  }
  "Non-Nashian best response in battle of sexes to `opera`" should "be `opera`" in {
    NonNashianBestResponse(GameFixtures.battleOfSexes, 1) should be (1)
  }


  "Non-Nashian best response in game of chicken to `swerve`" should "be `straight`" in {
    NonNashianBestResponse(GameFixtures.gameOfChicken, 0) should be (1)
  }
  "Non-Nashian best response in game of chicken to `straight`" should "be `swerve`" in {
    NonNashianBestResponse(GameFixtures.gameOfChicken, 1) should be (0)
  }

}