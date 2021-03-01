package app.algorithms

trait BestResponse {

  def apply[T](game: Game[T], rowStrategy: T): T

}
