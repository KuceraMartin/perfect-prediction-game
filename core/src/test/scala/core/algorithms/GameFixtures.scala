package core.algorithms


object GameFixtures {

  def oneByOne = Game(
    List(List((0, 0)))
  )

  def prisonersDilemma = Game(List(
    List(
      (3, 3),
      (0, 5),
    ),
    List(
      (5, 0),
      (1, 1),
    ),
  ))

  def rockPaperScissors = Game(List(
    List(
      (0, 0),
      (-1, 1),
      (1, -1),
    ),
    List(
      (1, -1),
      (0, 0),
      (-1, 1),
    ),
    List(
      (-1, 1),
      (1, -1),
      (0, 0),
    ),
  ))

  def matchingPennies = Game(List(
    List(
      (1, -1),
      (-1, 1),
    ),
    List(
      (-1, 1),
      (1, -1),
    ),
  ))

  def battleOfSexes = Game(List(
    List(
      (2, 1),
      (0, 0),
    ),
    List(
      (0, 0),
      (1, 2),
    ),
  ))

  def gameOfChicken = Game(List(
    List(
      (0, 0),
      (-1, 1),
    ),
    List(
      (1, -1),
      (-10, -10),
    ),
  ))

  def publicGoodsGame = Game(List(
    List(
      (8, 8),
      (11, 7),
      (14, 6),
    ),
    List(
      (7, 11),
      (10, 10),
      (13, 9),
    ),
    List(
      (6, 14),
      (9, 13),
      (12, 12),
    ),
  ))

  def unnamedGame1 = Game(List(
    List(
      (0, -1),
      (-3, -2),
      (-4, 1),
    ),
    List(
      (2, 3),
      (1, 2),
      (-1, 0),
    ),
    List(
      (3, -3),
      (4, -4),
      (-2, 4),
    ),
  ))

  def unnamedGame2 = Game(List(
    List(
      (-1, 6),
      (6, 2),
      (-4, -3),
    ),
    List(
      (-2, -1),
      (5, 4),
      (2, -2),
    ),
    List(
      (3, 1),
      (-3, -6),
      (4, 3),
    ),
    List(
      (-5, -5),
      (-6, 5),
      (1, -4),
    ),
  ))

  def unnamedGame3 = Game(List(
    List(
      (5, 3),
      (1, 2),
      (3, 9),
    ),
    List(
      (4, 7),
      (2, 8),
      (6, 5),
    ),
    List(
      (8, 1),
      (9, 4),
      (7, 6),
    ),
  ))

  def ptbpeNeMrGame = Game(List(
    List(
      (1, 1),
      (2, 2),
      (3, 3),
    ),
    List(
      (4, 5),
      (6, 8),
      (7, 9),
    ),
    List(
      (5, 6),
      (8, 3),
      (9, 7),
    ),
  ))

}
