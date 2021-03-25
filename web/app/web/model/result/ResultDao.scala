package web.model.result

import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext

import play.api.db.slick.DatabaseConfigProvider

import web.model.BaseDao
import web.model.BaseTable
import web.model.SlickPgProfile.api._

class ResultTable(tag: Tag) extends BaseTable[Result](tag, "result") {

  def userId = column[UUID]("user_id")
  def gameId = column[UUID]("game_id")
  def gameType = column[GameType.Member]("game_type")
  def rowStrategy = column[Int]("row_strategy")
  def colStrategy = column[Int]("col_strategy")

  def * = (id, createdAt, userId, gameId, gameType, rowStrategy, colStrategy) <> (Result.tupled, Result.unapply)

}


class ResultDao @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider,
) (implicit executionContext: ExecutionContext) extends BaseDao[Result, ResultTable] {

  protected val table = TableQuery[ResultTable]

}
