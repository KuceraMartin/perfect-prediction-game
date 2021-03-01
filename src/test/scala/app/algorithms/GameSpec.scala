package app.algorithms

import app.UnitSpec

class GameSpec extends UnitSpec {

  "Prisonner's dilemma" should "be without ties" in {
    GameFixtures.prisonersDilemma.isWithoutTies should be (true)
  }

  "Rock-paper-scissors" should "be with ties" in {
    GameFixtures.rockPaperScissors.isWithoutTies should be (false)
  }

  "Matching pennies" should "be with ties" in {
    GameFixtures.matchingPennies.isWithoutTies should be (false)
  }

  "Battle of sexes" should "be with ties" in {
    GameFixtures.battleOfSexes.isWithoutTies should be (false)
  }

  "Game of chicken" should "be without ties" in {
    GameFixtures.gameOfChicken.isWithoutTies should be (true)
  }

}
