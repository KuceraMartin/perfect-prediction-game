package core.algorithms


class PerfectlyTransparentColOptimalProfile(ptbr: BestResponse) {

  def apply(game: Game): Seq[Profile] = {
    val bestResponses = game.indices.flatMap(ptbr(game, _))
    if (bestResponses.isEmpty) Nil
    else bestResponses.groupBy(game(_).column) // group by column payoff
          .maxBy(_._1)._2 // get all with max payoff
  }

}


object PerfectlyTransparentColOptimalProfile {

  object Weak extends PerfectlyTransparentColOptimalProfile(PerfectlyTransparentBestResponse.Weak)


  object Strict extends PerfectlyTransparentColOptimalProfile(PerfectlyTransparentBestResponse.Strict) {

    override def apply(game: Game): Seq[Profile] = {
      val res = super.apply(game)
      def weakPayoff = game(Weak(game).head).column
      def strictPayoff = game(res.head).column
      if (res.length == 1 && strictPayoff == weakPayoff) res
      else Nil
    }
  }

}
