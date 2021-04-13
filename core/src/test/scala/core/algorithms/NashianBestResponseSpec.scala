package core.algorithms

import core.UnitSpec


class NashianBestResponseSpec extends UnitSpec {

  "1x1 game" should "respond 0 to 0" in {
    NashianBestResponse(GameFixtures.oneByOne, 0) should be (0)
  }


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


  "Nashian best response in the public goods game to `nothing`" should "be `nothing`" in {
    NashianBestResponse(GameFixtures.publicGoodsGame, 0) should be (0)
  }
  "Nashian best response in the public goods game to `half`" should "be `nothing`" in {
    NashianBestResponse(GameFixtures.publicGoodsGame, 1) should be (0)
  }
  "Nashian best response in the public goods game to `everything`" should "be `nothing`" in {
    NashianBestResponse(GameFixtures.publicGoodsGame, 2) should be (0)
  }


  "Unnamed game 1" should "respond 2 to 0" in {
    NashianBestResponse(GameFixtures.unnamedGame1, 0) should be (2)
  }
  it should "respond 0 to 1" in {
    NashianBestResponse(GameFixtures.unnamedGame1, 1) should be (0)
  }
  it should "respond 2 to 2" in {
    NashianBestResponse(GameFixtures.unnamedGame1, 2) should be (2)
  }


  "Unnamed game 2" should "respond 0 to 0" in {
    NashianBestResponse(GameFixtures.unnamedGame2, 0) should be (0)
  }
  it should "respond 1 to 1" in {
    NashianBestResponse(GameFixtures.unnamedGame2, 1) should be (1)
  }
  it should "respond 2 to 2" in {
    NashianBestResponse(GameFixtures.unnamedGame2, 2) should be (2)
  }
  it should "respond 1 to 3" in {
    NashianBestResponse(GameFixtures.unnamedGame2, 3) should be (1)
  }

}
