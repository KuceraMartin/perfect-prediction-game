package web.model.result

import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import core.algorithms.NashianBestResponse
import core.algorithms.PerfectlyTransparentBestResponse

import structures.GameType

import web.model.game.Game
import web.model.game.CoreBridge.modelGametoCoreGame
import web.model.user.User


class ResultService @Inject() (resultDao: ResultDao) (implicit executionContext: ExecutionContext) {

  private val bestResponse = Map(
    GameType.Nashian -> NashianBestResponse.Weak,
    GameType.NonNashian -> PerfectlyTransparentBestResponse.Weak,
  )

  def create(user: User, game: Game, gameType: GameType.Member, rowStrategy: Int) (implicit createdAt: LocalDateTime): Future[Result] = {
    val profile = bestResponse(gameType)(game, rowStrategy).head
    val result = Result(
      UUID.randomUUID(),
      createdAt,
      user.id,
      game.id,
      gameType,
      profile.row,
      profile.col,
      rowPayoff = game(profile).row
    )
    resultDao.insert(result) map { _ => result }
  }

}
