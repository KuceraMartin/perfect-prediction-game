package core.algorithms


trait BestResponse {

  def apply(game: Game, rowStrategy: Int): Int

  def equilibria(game: Game): Seq[Profile] =
    game.indices
        .map(i => Profile(i, apply(game, i)))
        .filter(p => apply(game.transpose, p.col) == p.row)

}
