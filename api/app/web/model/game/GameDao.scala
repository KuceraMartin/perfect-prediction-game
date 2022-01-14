package web.model.game

import javax.inject.Inject

import scala.concurrent.ExecutionContext

import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.json.JsValue

import web.model.BaseDao
import web.model.BaseTable
import web.model.SlickPgProfile.api._


class GamesTable(tag: Tag) extends BaseTable[Game](tag, "game") {

  def rows = column[Int]("rows")
  def columns = column[Int]("columns")
  def matrix = column[JsValue]("matrix")
  def seed = column[Int]("seed")

  def * = (id, createdAt, rows, columns, matrix, seed) <> (Game.tupled, Game.unapply)

}


class GameDao @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider,
) (implicit executionContext: ExecutionContext) extends BaseDao[Game, GamesTable] {

  protected val table = TableQuery[GamesTable]

}
