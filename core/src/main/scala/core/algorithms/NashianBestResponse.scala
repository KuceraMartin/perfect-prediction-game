package core.algorithms


object NashianBestResponse extends BestResponse {

  override def apply(game: Game, rowStrategy: Int): Int = {
    require(rowStrategy >= 0 && rowStrategy <= game.length)
    game(rowStrategy).zipWithIndex.max(Ordering.by[(Payoff, Int), Int](_._1.column))._2
  }

}
