package core.algorithms

import core.UnitSpec


class NashianBestResponseSpec extends UnitSpec {

  "1x1 game" should "respond 0 to 0" in {
    NashianBestResponse.Weak(GameFixtures.oneByOne, 0) should be (List(Profile(0, 0)))
  }


  "Best response in prisoner's dilemma to `cooperate`" should "be `defect`" in {
    NashianBestResponse.Weak(GameFixtures.prisonersDilemma, 0) should be (List(Profile(0, 1)))
  }
  "Best response in prisoner's dilemma to `defect`" should "be `defect`" in {
    NashianBestResponse.Weak(GameFixtures.prisonersDilemma, 1) should be (List(Profile(1, 1)))
  }


  "Nashian best response in rock-paper-scissors to `rock`" should "be `paper`" in {
    NashianBestResponse.Weak(GameFixtures.rockPaperScissors, 0) should be (List(Profile(0, 1)))
  }
  "Nashian best response in rock-paper-scissors to `paper`" should "be `scissors`" in {
    NashianBestResponse.Weak(GameFixtures.rockPaperScissors, 1) should be (List(Profile(1, 2)))
  }
  "Nashian best response in rock-paper-scissors to `scissors`" should "be `rock`" in {
    NashianBestResponse.Weak(GameFixtures.rockPaperScissors, 2) should be (List(Profile(2, 0)))
  }


  "Nashian best response in matching pennies to `heads`" should "be `tails`" in {
    NashianBestResponse.Weak(GameFixtures.matchingPennies, 0) should be (List(Profile(0, 1)))
  }
  "Nashian best response in matching pennies to `tails`" should "be `heads`" in {
    NashianBestResponse.Weak(GameFixtures.matchingPennies, 1) should be (List(Profile(1, 0)))
  }


  "Nashian best response in battle of sexes to `football`" should "be `football`" in {
    NashianBestResponse.Weak(GameFixtures.battleOfSexes, 0) should be (List(Profile(0, 0)))
  }
  "Nashian best response in battle of sexes to `opera`" should "be `opera`" in {
    NashianBestResponse.Weak(GameFixtures.battleOfSexes, 1) should be (List(Profile(1, 1)))
  }


  "Nashian best response in game of chicken to `swerve`" should "be `straight`" in {
    NashianBestResponse.Weak(GameFixtures.gameOfChicken, 0) should be (List(Profile(0, 1)))
  }
  "Nashian best response in game of chicken to `straight`" should "be `swerve`" in {
    NashianBestResponse.Weak(GameFixtures.gameOfChicken, 1) should be (List(Profile(1, 0)))
  }


  "Nashian best response in the public goods game to `nothing`" should "be `nothing`" in {
    NashianBestResponse.Weak(GameFixtures.publicGoodsGame, 0) should be (List(Profile(0, 0)))
  }
  "Nashian best response in the public goods game to `half`" should "be `nothing`" in {
    NashianBestResponse.Weak(GameFixtures.publicGoodsGame, 1) should be (List(Profile(1, 0)))
  }
  "Nashian best response in the public goods game to `everything`" should "be `nothing`" in {
    NashianBestResponse.Weak(GameFixtures.publicGoodsGame, 2) should be (List(Profile(2, 0)))
  }


  "Unnamed game 1" should "respond 2 to 0" in {
    NashianBestResponse.Weak(GameFixtures.unnamedGame1, 0) should be (List(Profile(0, 2)))
  }
  it should "respond 0 to 1" in {
    NashianBestResponse.Weak(GameFixtures.unnamedGame1, 1) should be (List(Profile(1, 0)))
  }
  it should "respond 2 to 2" in {
    NashianBestResponse.Weak(GameFixtures.unnamedGame1, 2) should be (List(Profile(2, 2)))
  }


  "Unnamed game 2" should "respond 0 to 0" in {
    NashianBestResponse.Weak(GameFixtures.unnamedGame2, 0) should be (List(Profile(0, 0)))
  }
  it should "respond 1 to 1" in {
    NashianBestResponse.Weak(GameFixtures.unnamedGame2, 1) should be (List(Profile(1, 1)))
  }
  it should "respond 2 to 2" in {
    NashianBestResponse.Weak(GameFixtures.unnamedGame2, 2) should be (List(Profile(2, 2)))
  }
  it should "respond 1 to 3" in {
    NashianBestResponse.Weak(GameFixtures.unnamedGame2, 3) should be (List(Profile(3, 1)))
  }

}
