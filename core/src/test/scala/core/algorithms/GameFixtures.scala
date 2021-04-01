package core.algorithms


object GameFixtures {

  def prisonersDilemma = Game(List(
    List(
      Payoff(3, 3),
      Payoff(0, 5),
    ),
    List(
      Payoff(5, 0),
      Payoff(1, 1),
    ),
  ))

  def rockPaperScissors = Game(List(
    List(
      Payoff(0, 0),
      Payoff(-1, 1),
      Payoff(1, -1),
    ),
    List(
      Payoff(1, -1),
      Payoff(0, 0),
      Payoff(-1, 1),
    ),
    List(
      Payoff(-1, 1),
      Payoff(1, -1),
      Payoff(0, 0),
    ),
  ))

  def matchingPennies = Game(List(
    List(
      Payoff(1, -1),
      Payoff(-1, 1),
    ),
    List(
      Payoff(-1, 1),
      Payoff(1, -1),
    ),
  ))

  def battleOfSexes = Game(List(
    List(
      Payoff(2, 1),
      Payoff(0, 0),
    ),
    List(
      Payoff(0, 0),
      Payoff(1, 2),
    ),
  ))

  def gameOfChicken = Game(List(
    List(
      Payoff(0, 0),
      Payoff(-1, 1),
    ),
    List(
      Payoff(1, -1),
      Payoff(-10, -10),
    ),
  ))

  def publicGoodsGame = Game(List(
    List(
      Payoff(8, 8),
      Payoff(11, 7),
      Payoff(14, 6),
    ),
    List(
      Payoff(7, 11),
      Payoff(10, 10),
      Payoff(13, 9),
    ),
    List(
      Payoff(6, 14),
      Payoff(9, 13),
      Payoff(12, 12),
    ),
  ))

}
