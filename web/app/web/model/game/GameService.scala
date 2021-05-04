package web.model.game

import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext
import scala.concurrent.Future
import scala.util.Random

import play.api.libs.json.Json
import play.api.libs.json.Writes

import core.algorithms.GameGenerator
import core.algorithms.Payoff


class GameService @Inject()(gameDao: GameDao, random: Random)(implicit ec: ExecutionContext) {

  implicit private val payoffWrites = new Writes[Payoff] {
    override def writes(p: Payoff) = Json.arr(
      p.row,
      p.column,
    )
  }

  def generate(rows: Int, cols: Int)(implicit createdAt: LocalDateTime): Future[Game] = {
    val seed = random.nextInt()
    val game = Game(
      UUID.randomUUID(),
      createdAt,
      rows,
      cols,
      Json.toJson(GameGenerator(new Random(seed))(rows, cols)),
      seed,
    )
    gameDao.insert(game).map { _ => game }
  }

}
