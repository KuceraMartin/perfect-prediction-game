package core.algorithms


trait BestResponse {

  def apply(game: Game, rowStrategy: Int): Seq[Profile]

  def equilibria(game: Game): Seq[Profile] =
    game.indices
      .flatMap { rowStrategy =>
        apply(game, rowStrategy)
        .filter(profile => apply(game.transpose, profile.col).contains(profile.swap))
      }

}
