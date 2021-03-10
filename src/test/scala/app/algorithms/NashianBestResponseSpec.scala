package app.algorithms

import app.UnitSpec
import app.algorithms.BestResponse.{ColumnStrategy, RowStrategyNotFound}

class NashianBestResponseSpec extends UnitSpec {

  "Nashian best response to a non-existing row strategy" should "report that the row strategy was not found" in {
    NashianBestResponse(GameFixtures.prisonersDilemma, "bfuiosfb") should be (RowStrategyNotFound)
  }


  "Best response in prisoner's dilemma to `cooperate`" should "be `defect`" in {
    NashianBestResponse(GameFixtures.prisonersDilemma, "C") should be (ColumnStrategy("D"))
  }
  "Best response in prisoner's dilemma to `defect`" should "be `defect`" in {
    NashianBestResponse(GameFixtures.prisonersDilemma, "D") should be (ColumnStrategy("D"))
  }


  "Nashian best response in rock-paper-scissors to `rock`" should "be `paper`" in {
    NashianBestResponse(GameFixtures.rockPaperScissors, "R") should be (ColumnStrategy("P"))
  }
  "Nashian best response in rock-paper-scissors to `paper`" should "be `scissors`" in {
    NashianBestResponse(GameFixtures.rockPaperScissors, "P") should be (ColumnStrategy("S"))
  }
  "Nashian best response in rock-paper-scissors to `scissors`" should "be `rock`" in {
    NashianBestResponse(GameFixtures.rockPaperScissors, "S") should be (ColumnStrategy("R"))
  }


  "Nashian best response in matching pennies to `heads`" should "be `tails`" in {
    NashianBestResponse(GameFixtures.matchingPennies, "H") should be (ColumnStrategy("T"))
  }
  "Nashian best response in matching pennies to `tails`" should "be `heads`" in {
    NashianBestResponse(GameFixtures.matchingPennies, "T") should be (ColumnStrategy("H"))
  }


  "Nashian best response in battle of sexes to `football`" should "be `football`" in {
    NashianBestResponse(GameFixtures.battleOfSexes, "F") should be (ColumnStrategy("F"))
  }
  "Nashian best response in battle of sexes to `opera`" should "be `opera`" in {
    NashianBestResponse(GameFixtures.battleOfSexes, "O") should be (ColumnStrategy("O"))
  }


  "Nashian best response in game of chicken to `swerve`" should "be `straight`" in {
    NashianBestResponse(GameFixtures.gameOfChicken, "Sw") should be (ColumnStrategy("St"))
  }
  "Nashian best response in game of chicken to `straight`" should "be `swerve`" in {
    NashianBestResponse(GameFixtures.gameOfChicken, "St") should be (ColumnStrategy("Sw"))
  }

}
