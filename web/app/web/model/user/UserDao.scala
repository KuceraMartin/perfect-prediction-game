package web.model.user

import javax.inject.Inject

import scala.concurrent.ExecutionContext

import play.api.db.slick.DatabaseConfigProvider

import web.model.BaseDao
import web.model.BaseTable
import web.model.SlickPgProfile.api._


protected class UsersTable(tag: Tag) extends BaseTable[User](tag, "user") {

  def * = (id, createdAt) <> (User.tupled, User.unapply)

}


class UserDao @Inject() (
  protected val dbConfigProvider: DatabaseConfigProvider,
) (implicit executionContext: ExecutionContext) extends BaseDao[User, UsersTable] {

  protected val table = TableQuery[UsersTable]

}
