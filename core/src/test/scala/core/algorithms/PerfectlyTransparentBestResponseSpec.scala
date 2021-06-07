package core.algorithms

import core.UnitSpec


class PerfectlyTransparentBestResponseSpec extends UnitSpec {

  "1x1 game" should "respond 0 to 0" in {
    PerfectlyTransparentBestResponse(GameFixtures.oneByOne, 0) should be (0)
  }


  "Non-Nashian Best response in prisoner's dilemma to `cooperate`" should "be `cooperate`" in {
    PerfectlyTransparentBestResponse(GameFixtures.prisonersDilemma, 0) should be (0)
  }
  "Non-Nashian Best response in prisoner's dilemma to `defect`" should "be `defect`" in {
    PerfectlyTransparentBestResponse(GameFixtures.prisonersDilemma, 1) should be (1)
  }


  "Non-Nashian best response in game of chicken to `swerve`" should "be `straight`" in {
    PerfectlyTransparentBestResponse(GameFixtures.gameOfChicken, 0) should be (1)
  }
  "Non-Nashian best response in game of chicken to `straight`" should "be `swerve`" in {
    PerfectlyTransparentBestResponse(GameFixtures.gameOfChicken, 1) should be (0)
  }


  "Non-Nashian best response in the public goods game to `nothing`" should "be `nothing`" in {
    PerfectlyTransparentBestResponse(GameFixtures.publicGoodsGame, 0) should be (0)
  }
  "Non-Nashian best response in the public goods game to `half`" should "be `half`" in {
    PerfectlyTransparentBestResponse(GameFixtures.publicGoodsGame, 1) should be (1)
  }
  "Non-Nashian best response in the public goods game to `everything`" should "be `everything`" in {
    PerfectlyTransparentBestResponse(GameFixtures.publicGoodsGame, 2) should be (2)
  }


  "Unnamed game 1" should "respond 2 to 0" in {
    PerfectlyTransparentBestResponse(GameFixtures.unnamedGame1, 0) should be (2)
  }
  it should "respond 0 to 1" in {
    PerfectlyTransparentBestResponse(GameFixtures.unnamedGame1, 1) should be (0)
  }
  it should "respond 2 to 2" in {
    PerfectlyTransparentBestResponse(GameFixtures.unnamedGame1, 2) should be (2)
  }


  "Unnamed game 2" should "respond 1 to 0" in {
    PerfectlyTransparentBestResponse(GameFixtures.unnamedGame2, 0) should be (1)
  }
  it should "respond 1 to 1" in {
    PerfectlyTransparentBestResponse(GameFixtures.unnamedGame2, 1) should be (1)
  }
  it should "respond 2 to 2" in {
    PerfectlyTransparentBestResponse(GameFixtures.unnamedGame2, 2) should be (2)
  }
  it should "respond 2 to 3" in {
    PerfectlyTransparentBestResponse(GameFixtures.unnamedGame2, 3) should be (2)
  }


  "`PTBPE != MR` game" should "respond 2 to 0" in {
    PerfectlyTransparentBestResponse(GameFixtures.ptbpeNeMrGame, 0) should be (2)
  }
  it should "respond 2 to 1" in {
    PerfectlyTransparentBestResponse(GameFixtures.ptbpeNeMrGame, 1) should be (2)
  }
  it should "respond 2 to 2" in {
    PerfectlyTransparentBestResponse(GameFixtures.ptbpeNeMrGame, 2) should be (2)
  }

}
