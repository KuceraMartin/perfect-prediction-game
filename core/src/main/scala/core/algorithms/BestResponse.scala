package core.algorithms

import core.algorithms.BestResponse.Result


trait BestResponse {

  def apply[T](game: Game[T], rowStrategy: T): Result[T]

}


object BestResponse {

  sealed abstract class Result[+T]()

  final case class ColumnStrategy[+T](strategy: T) extends Result[T]

  case object RowStrategyNotFound extends Result[Nothing]

}
