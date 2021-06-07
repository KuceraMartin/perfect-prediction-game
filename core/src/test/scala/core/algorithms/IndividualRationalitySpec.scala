package core.algorithms

import core.UnitSpec


class IndividualRationalitySpec extends UnitSpec {

  "Prisoner's dilemma" should "eliminate (cooperate-defect) and (defect-cooperate)" in {
    IndividualRationality.all(GameFixtures.prisonersDilemma) should be(List(
      Profile(0, 0), /* -------- */
      /* -------- */ Profile(1, 1),
    ))
  }

  "Rock-paper-scissors" should "not eliminate anything" in {
    IndividualRationality.all(GameFixtures.rockPaperScissors) should be(List(
      Profile(0, 0), Profile(0, 1), Profile(0, 2),
      Profile(1, 0), Profile(1, 1), Profile(1, 2),
      Profile(2, 0), Profile(2, 1), Profile(2, 2),
    ))
  }

  "Matching pennies" should "not eliminate anything`" in {
    IndividualRationality.all(GameFixtures.matchingPennies) should be (List(
      Profile(0, 0), Profile(0, 1),
      Profile(1, 0), Profile(1, 1),
    ))
  }


  "Battle of sexes" should "not eliminate anything" in {
    IndividualRationality.all(GameFixtures.battleOfSexes) should be (List(
      Profile(0, 0), Profile(0, 1),
      Profile(1, 0), Profile(1, 1),
    ))
  }


  "Game of chicken" should "eliminate (straight, straight)" in {
    IndividualRationality.all(GameFixtures.gameOfChicken) should be (List(
      Profile(0, 0), Profile(0, 1),
      Profile(1, 0), /* -------- */
    ))
  }


  "Public goods game" should "be `nothing`" in {
    IndividualRationality.all(GameFixtures.publicGoodsGame) should be (List(
      Profile(0, 0), /* -------- */ /* -------- */
      /* -------- */ Profile(1, 1), Profile(1, 2),
      /* -------- */ Profile(2, 1), Profile(2, 2),
    ))
  }


  "Unnamed game 1" should "eliminate first and last row" in {
    IndividualRationality.all(GameFixtures.unnamedGame1) should be (List(
      /* -------- */ /* -------- */ /* -------- */
      Profile(1, 0), Profile(1, 1), Profile(1, 2),
      /* -------- */ /* -------- */ /* -------- */
    ))
  }


  "Unnamed game 2" should "eliminate (0, 2), (2, 1), (3, 0), (3, 1)" in {
    IndividualRationality.all(GameFixtures.unnamedGame2) should be (List(
      Profile(0, 0), Profile(0, 1), /* -------- */
      Profile(1, 0), Profile(1, 1), Profile(1, 2),
      Profile(2, 0), /* -------- */ Profile(2, 2),
      /* -------- */ /* -------- */ Profile(3, 2),
    ))
  }

}
