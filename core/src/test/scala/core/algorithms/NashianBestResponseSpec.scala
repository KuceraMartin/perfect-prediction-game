package core.algorithms

import core.UnitSpec


class NashianBestResponseSpec extends UnitSpec {

  "Best response in prisoner's dilemma to `cooperate`" should "be `defect`" in {
    NashianBestResponse(GameFixtures.prisonersDilemma, 0) should be (1)
  }
  "Best response in prisoner's dilemma to `defect`" should "be `defect`" in {
    NashianBestResponse(GameFixtures.prisonersDilemma, 1) should be (1)
  }


  "Nashian best response in rock-paper-scissors to `rock`" should "be `paper`" in {
    NashianBestResponse(GameFixtures.rockPaperScissors, 0) should be (1)
  }
  "Nashian best response in rock-paper-scissors to `paper`" should "be `scissors`" in {
    NashianBestResponse(GameFixtures.rockPaperScissors, 1) should be (2)
  }
  "Nashian best response in rock-paper-scissors to `scissors`" should "be `rock`" in {
    NashianBestResponse(GameFixtures.rockPaperScissors, 2) should be (0)
  }


  "Nashian best response in matching pennies to `heads`" should "be `tails`" in {
    NashianBestResponse(GameFixtures.matchingPennies, 0) should be (1)
  }
  "Nashian best response in matching pennies to `tails`" should "be `heads`" in {
    NashianBestResponse(GameFixtures.matchingPennies, 1) should be (0)
  }


  "Nashian best response in battle of sexes to `football`" should "be `football`" in {
    NashianBestResponse(GameFixtures.battleOfSexes, 0) should be (0)
  }
  "Nashian best response in battle of sexes to `opera`" should "be `opera`" in {
    NashianBestResponse(GameFixtures.battleOfSexes, 1) should be (1)
  }


  "Nashian best response in game of chicken to `swerve`" should "be `straight`" in {
    NashianBestResponse(GameFixtures.gameOfChicken, 0) should be (1)
  }
  "Nashian best response in game of chicken to `straight`" should "be `swerve`" in {
    NashianBestResponse(GameFixtures.gameOfChicken, 1) should be (0)
  }

}
