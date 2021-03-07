package app.algorithms

import app.UnitSpec

class NashianBestResponseSpec extends UnitSpec {

  "Best response in prisoner's dilemma to `cooperate`" should "be `defect`" in {
    NashianBestResponse(GameFixtures.prisonersDilemma, "C") should be ("D")
  }
  "Best response in prisoner's dilemma to `defect`" should "be `defect`" in {
    NashianBestResponse(GameFixtures.prisonersDilemma, "D") should be ("D")
  }


  "Nashian best response in rock-paper-scissors to `rock`" should "be `paper`" in {
    NashianBestResponse(GameFixtures.rockPaperScissors, "R") should be ("P")
  }
  "Nashian best response in rock-paper-scissors to `paper`" should "be `scissors`" in {
    NashianBestResponse(GameFixtures.rockPaperScissors, "P") should be ("S")
  }
  "Nashian best response in rock-paper-scissors to `scissors`" should "be `rock`" in {
    NashianBestResponse(GameFixtures.rockPaperScissors, "S") should be ("R")
  }


  "Nashian best response in matching pennies to `heads`" should "be `tails`" in {
    NashianBestResponse(GameFixtures.matchingPennies, "H") should be ("T")
  }
  "Nashian best response in matching pennies to `tails`" should "be `heads`" in {
    NashianBestResponse(GameFixtures.matchingPennies, "T") should be ("H")
  }


  "Nashian best response in battle of sexes to `football`" should "be `football`" in {
    NashianBestResponse(GameFixtures.battleOfSexes, "F") should be ("F")
  }
  "Nashian best response in battle of sexes to `opera`" should "be `opera`" in {
    NashianBestResponse(GameFixtures.battleOfSexes, "O") should be ("O")
  }


  "Nashian best response in game of chicken to `swerve`" should "be `straight`" in {
    NashianBestResponse(GameFixtures.gameOfChicken, "Sw") should be ("St")
  }
  "Nashian best response in game of chicken to `straight`" should "be `swerve`" in {
    NashianBestResponse(GameFixtures.gameOfChicken, "St") should be ("Sw")
  }

}
