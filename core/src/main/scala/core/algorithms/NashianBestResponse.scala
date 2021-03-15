package core.algorithms

import core.algorithms.BestResponse._


object NashianBestResponse extends BestResponse {

  override def apply[T](game: Game[T], rowStrategy: T): Result[T] =
    game.matrix.get(rowStrategy) match {
      case Some(row) => ColumnStrategy(row.max(Ordering.by[(T, Payoff), Int](_._2.column))._1)
      case None => RowStrategyNotFound
    }

}
