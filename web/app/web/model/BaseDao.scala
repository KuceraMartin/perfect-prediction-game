package web.model

import java.time.LocalDateTime
import java.util.UUID

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import play.api.db.slick.DatabaseConfigProvider
import play.api.db.slick.HasDatabaseConfig
import slick.jdbc.PostgresProfile

import web.model.SlickPgProfile.api._


abstract protected class BaseTable[Entity](tag: Tag, _tableName: String) extends Table[Entity](tag, _tableName) {

  def id = column[UUID]("id", O.PrimaryKey)
  def createdAt = column[LocalDateTime]("created_at")

}


abstract class BaseDao[Entity, Table <: BaseTable[Entity]]() (implicit executionContext: ExecutionContext)
  extends HasDatabaseConfig[PostgresProfile] {

    protected val dbConfigProvider: DatabaseConfigProvider

    protected val dbConfig = dbConfigProvider.get[PostgresProfile]

    protected val table: TableQuery[Table]

    def findAll(): Future[Seq[Entity]] = db.run(table.result)

    def get(id: UUID): Future[Option[Entity]] = db.run(table.filter(_.id === id).result).map(_.headOption)

    def insert(entity: Entity): Future[_] = db.run(table += entity)

}
