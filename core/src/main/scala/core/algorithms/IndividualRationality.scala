package core.algorithms


object IndividualRationality extends Eliminator {

  override def eliminate(game: Game): Game = {
    val rowMin = game.rowMaxMin
    val colMin = game.colMaxMin
    game.map(_.map(p => if (p.row >= rowMin && p.column >= colMin) p else Payoff.Undefined))
  }

}
