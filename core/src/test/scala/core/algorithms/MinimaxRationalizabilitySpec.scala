package core.algorithms

import core.UnitSpec


class MinimaxRationalizabilitySpec extends UnitSpec {

  "Prisoner's dilemma" should "not eliminate anything" in {
    MinimaxRationalizability.all(GameFixtures.prisonersDilemma) should be(List(
      Profile(0, 0), Profile(0, 1),
      Profile(1, 0), Profile(1, 1),
    ))
  }

  "Rock-paper-scissors" should "not eliminate anything" in {
    MinimaxRationalizability.all(GameFixtures.rockPaperScissors) should be(List(
      Profile(0, 0), Profile(0, 1), Profile(0, 2),
      Profile(1, 0), Profile(1, 1), Profile(1, 2),
      Profile(2, 0), Profile(2, 1), Profile(2, 2),
    ))
  }

  "Matching pennies" should "not eliminate anything`" in {
    MinimaxRationalizability.all(GameFixtures.matchingPennies) should be (List(
      Profile(0, 0), Profile(0, 1),
      Profile(1, 0), Profile(1, 1),
    ))
  }


  "Battle of sexes" should "not eliminate anything" in {
    MinimaxRationalizability.all(GameFixtures.battleOfSexes) should be (List(
      Profile(0, 0), Profile(0, 1),
      Profile(1, 0), Profile(1, 1),
    ))
  }


  "Game of chicken" should "not eliminate anything" in {
    MinimaxRationalizability.all(GameFixtures.gameOfChicken) should be (List(
      Profile(0, 0), Profile(0, 1),
      Profile(1, 0), Profile(1, 1),
    ))
  }


  "Public goods game" should "not eliminate anything" in {
    MinimaxRationalizability.all(GameFixtures.publicGoodsGame) should be (List(
      Profile(0, 0), Profile(0, 1), Profile(0, 2),
      Profile(1, 0), Profile(1, 1), Profile(1, 2),
      Profile(2, 0), Profile(2, 1), Profile(2, 2),
    ))
  }


  "Unnamed game 1" should "not eliminate anything" in {
    MinimaxRationalizability.all(GameFixtures.unnamedGame1) should be (List(
      Profile(0, 0), Profile(0, 1), Profile(0, 2),
      Profile(1, 0), Profile(1, 1), Profile(1, 2),
      Profile(2, 0), Profile(2, 1), Profile(2, 2),
    ))
  }


  "Unnamed game 2" should "not eliminate anything" in {
    MinimaxRationalizability.all(GameFixtures.unnamedGame2) should be (List(
      Profile(0, 0), Profile(0, 1), Profile(0, 2),
      Profile(1, 0), Profile(1, 1), Profile(1, 2),
      Profile(2, 0), Profile(2, 1), Profile(2, 2),
      Profile(3, 0), Profile(3, 1), Profile(3, 2),
    ))
  }


  "Unnamed game 2" should "eliminate everything except (2, 2)" in {
    MinimaxRationalizability.all(GameFixtures.unnamedGame3) should be (List(
      Profile(2, 2)
    ))
  }


  "`PTBPE != MR` game" should "eliminate everything except (2, 2)" in {
    MinimaxRationalizability.all(GameFixtures.ptbpeNeMrGame) should be (List(
      Profile(2, 2)
    ))
  }

}
