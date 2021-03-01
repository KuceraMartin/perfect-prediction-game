package app.algorithms

object GameFixtures {

  def prisonersDilemma = Game(Map(
    "C" -> Map(
      "C" -> Payoff(3, 3),
      "D" -> Payoff(0, 5),
    ),
    "D" -> Map(
      "C" -> Payoff(5, 0),
      "D" -> Payoff(1, 1),
    )
  ))

  def rockPaperScissors = Game(Map(
    "R" -> Map(
      "R" -> Payoff(0, 0),
      "P" -> Payoff(-1, 1),
      "S" -> Payoff(1, -1),
    ),
    "P" -> Map(
      "R" -> Payoff(1, -1),
      "P" -> Payoff(0, 0),
      "S" -> Payoff(-1, 1),
    ),
    "S" -> Map(
      "R" -> Payoff(-1, 1),
      "P" -> Payoff(1, -1),
      "S" -> Payoff(0, 0),
    ),
  ))

  def matchingPennies = Game(Map(
    "H" -> Map(
      "H" -> Payoff(1, -1),
      "T" -> Payoff(-1, 1),
    ),
    "T" -> Map(
      "H" -> Payoff(-1, 1),
      "T" -> Payoff(1, -1),
    ),
  ))

  def battleOfSexes = Game(Map(
    "F" -> Map(
      "F" -> Payoff(2, 1),
      "O" -> Payoff(0, 0),
    ),
    "O" -> Map(
      "F" -> Payoff(0, 0),
      "O" -> Payoff(1, 2),
    ),
  ))

  def gameOfChicken = Game(Map(
    "Sw" -> Map(
      "Sw" -> Payoff(0, 0),
      "St" -> Payoff(-1, 1),
    ),
    "St" -> Map(
      "Sw" -> Payoff(1, -1),
      "St" -> Payoff(-10, -10),
    ),
  ))

}
