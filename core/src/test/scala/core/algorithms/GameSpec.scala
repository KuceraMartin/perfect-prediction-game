package core.algorithms

import core.UnitSpec


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


  "Transpose" should "work correctly" in {
    Game(List(
      List((2, 1), (6, 4), (0, 7)),
      List((4, 7), (2, 2), (0, 8)),
      List((3, 5), (6, 2), (2, 2)),
    )).transpose should be (Game(List(
      List((1, 2), (7, 4), (5, 3)),
      List((4, 6), (2, 2), (2, 6)),
      List((7, 0), (8, 0), (2, 2)),
    )))
  }

}
