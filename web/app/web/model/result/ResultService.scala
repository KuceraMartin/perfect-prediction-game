package web.model.result

import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import core.algorithms.NashianBestResponse
import core.algorithms.NonNashianBestResponse

import web.model.game.Game
import web.model.game.CoreBridge.modelGametoCoreGame
import web.model.user.User


class ResultService @Inject() (resultDao: ResultDao) (implicit executionContext: ExecutionContext) {

  private val bestResponse = Map(
    GameType.Nashian -> NashianBestResponse,
    GameType.NonNashian -> NonNashianBestResponse,
  )

  def create(user: User, game: Game, gameType: GameType.Member, rowStrategy: Int) (implicit createdAt: LocalDateTime): Future[Result] = {
    val result = Result(
      UUID.randomUUID(),
      createdAt,
      user.id,
      game.id,
      gameType,
      rowStrategy,
      colStrategy = bestResponse(gameType)(game, rowStrategy),
    )
    resultDao.insert(result) map { _ => result }
  }

}
