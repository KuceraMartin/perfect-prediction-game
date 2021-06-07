package core.algorithms


object NashianBestResponse {

  object Weak extends BestResponse {

    override def apply(game: Game, rowStrategy: Int): Seq[Profile] = {
      require(rowStrategy >= 0 && rowStrategy <= game.length)
      game(rowStrategy).zipWithIndex
                       .groupBy(_._1.column)
                       .maxBy(_._1)
                       ._2.map(s => Profile(rowStrategy, s._2))
    }

  }


  object Strict extends BestResponse {

    override def apply(game: Game, rowStrategy: Int): Seq[Profile] = {
      val res = Weak(game, rowStrategy)
      if (res.length == 1) res
      else Nil
    }

  }

}
