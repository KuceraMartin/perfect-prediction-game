package core.algorithms

import core.UnitSpec


class NonNashianBestResponseSpec extends UnitSpec {

  "1x1 game" should "respond 0 to 0" in {
    NonNashianBestResponse(GameFixtures.oneByOne, 0) should be (0)
  }


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


  "Unnamed game 1" should "respond 2 to 0" in {
    NonNashianBestResponse(GameFixtures.unnamedGame1, 0) should be (2)
  }
  it should "respond 0 to 1" in {
    NonNashianBestResponse(GameFixtures.unnamedGame1, 1) should be (0)
  }
  it should "respond 2 to 2" in {
    NonNashianBestResponse(GameFixtures.unnamedGame1, 2) should be (2)
  }


  "Unnamed game 2" should "respond 1 to 0" in {
    NonNashianBestResponse(GameFixtures.unnamedGame2, 0) should be (1)
  }
  it should "respond 1 to 1" in {
    NonNashianBestResponse(GameFixtures.unnamedGame2, 1) should be (1)
  }
  it should "respond 2 to 2" in {
    NonNashianBestResponse(GameFixtures.unnamedGame2, 2) should be (2)
  }
  it should "respond 2 to 3" in {
    NonNashianBestResponse(GameFixtures.unnamedGame2, 3) should be (2)
  }

}
