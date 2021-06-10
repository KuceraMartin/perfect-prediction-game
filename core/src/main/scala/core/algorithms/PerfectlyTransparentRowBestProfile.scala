package core.algorithms


object PerfectlyTransparentRowBestProfile {

  object Weak extends {

    def apply(game: Game): Seq[Profile] =
      game.rowIndices
          .flatMap(PerfectlyTransparentBestResponse.Weak(game, _))
          .groupBy(game(_).row)
          .maxBy(_._1)
          ._2

  }

  object Strict {

    def apply(game: Game): Seq[Profile] =
      Weak(game).filter { profile =>
        PerfectlyTransparentBestResponse.Strict(game, profile.row).contains(profile)
      }

  }

}
