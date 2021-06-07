package core.algorithms

import scala.annotation.tailrec


class PerfectlyTransparentBestResponse(nashianBestResponse: BestResponse) extends BestResponse {

  override def apply(game: Game, rowStrategy: Int): Seq[Profile] = {
    @tailrec
    def loop(oldGame: Game, newGame: Game, firstRun: Boolean = true): Game = {
      val availableInRow = newGame(rowStrategy).exists(PerfectlyTransparentEquilibrium.notEliminated)
      if (availableInRow) {
        if (newGame != oldGame || firstRun) loop(newGame, IndividualRationality.eliminate(newGame), firstRun = false)
        else newGame
      } else oldGame
    }
    nashianBestResponse(loop(game, game), rowStrategy)
  }




}


object PerfectlyTransparentBestResponse {

  object Weak extends PerfectlyTransparentBestResponse(NashianBestResponse.Weak)

  object Strict extends PerfectlyTransparentBestResponse(NashianBestResponse.Strict)

}
