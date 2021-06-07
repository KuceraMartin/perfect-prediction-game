package core.algorithms


trait Eliminator {

  def eliminate(game: Game): Game

  def all(game: Game): Seq[Profile] =
    eliminate(game).map(_.zipWithIndex.filterNot(_._1 == Payoff.Undefined))
                   .zipWithIndex
                   .flatMap(zr => zr._1.map(zc => Profile(zr._2, zc._2)))

}
