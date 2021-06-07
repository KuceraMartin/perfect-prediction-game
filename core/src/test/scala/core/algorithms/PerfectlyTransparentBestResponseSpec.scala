package core.algorithms

import core.UnitSpec


class PerfectlyTransparentBestResponseSpec extends UnitSpec {

  "1x1 game" should "respond 0 to 0" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.oneByOne, 0) should be (List(Profile(0, 0)))
  }


  "Non-Nashian Best response in prisoner's dilemma to `cooperate`" should "be `cooperate`" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.prisonersDilemma, 0) should be (List(Profile(0, 0)))
  }
  "Non-Nashian Best response in prisoner's dilemma to `defect`" should "be `defect`" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.prisonersDilemma, 1) should be (List(Profile(1, 1)))
  }


  "Non-Nashian best response in game of chicken to `swerve`" should "be `straight`" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.gameOfChicken, 0) should be (List(Profile(0, 1)))
  }
  "Non-Nashian best response in game of chicken to `straight`" should "be `swerve`" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.gameOfChicken, 1) should be (List(Profile(1, 0)))
  }


  "Non-Nashian best response in the public goods game to `nothing`" should "be `nothing`" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.publicGoodsGame, 0) should be (List(Profile(0, 0)))
  }
  "Non-Nashian best response in the public goods game to `half`" should "be `half`" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.publicGoodsGame, 1) should be (List(Profile(1, 1)))
  }
  "Non-Nashian best response in the public goods game to `everything`" should "be `everything`" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.publicGoodsGame, 2) should be (List(Profile(2, 2)))
  }


  "Unnamed game 1" should "respond 2 to 0" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.unnamedGame1, 0) should be (List(Profile(0, 2)))
  }
  it should "respond 0 to 1" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.unnamedGame1, 1) should be (List(Profile(1, 0)))
  }
  it should "respond 2 to 2" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.unnamedGame1, 2) should be (List(Profile(2, 2)))
  }


  "Unnamed game 2" should "respond 1 to 0" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.unnamedGame2, 0) should be (List(Profile(0, 1)))
  }
  it should "respond 1 to 1" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.unnamedGame2, 1) should be (List(Profile(1, 1)))
  }
  it should "respond 2 to 2" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.unnamedGame2, 2) should be (List(Profile(2, 2)))
  }
  it should "respond 2 to 3" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.unnamedGame2, 3) should be (List(Profile(3, 2)))
  }


  "`PTBPE != MR` game" should "respond 2 to 0" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.ptbpeNeMrGame, 0) should be (List(Profile(0, 2)))
  }
  it should "respond 2 to 1" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.ptbpeNeMrGame, 1) should be (List(Profile(1, 2)))
  }
  it should "respond 2 to 2" in {
    PerfectlyTransparentBestResponse.Weak(GameFixtures.ptbpeNeMrGame, 2) should be (List(Profile(2, 2)))
  }

}
