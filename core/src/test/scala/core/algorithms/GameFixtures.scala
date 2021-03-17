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

}
