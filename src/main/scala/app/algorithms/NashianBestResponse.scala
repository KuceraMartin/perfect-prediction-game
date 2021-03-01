package app.algorithms

object NashianBestResponse extends BestResponse {

  override def apply[T](game: Game[T], rowStrategy: T): T =
    game.matrix(rowStrategy).max(Ordering.by[(T, Payoff), Int](_._2.column))._1

}
