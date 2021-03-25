package web.model.user

import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext
import scala.concurrent.Future


class UserService @Inject() (userDao: UserDao) (implicit ec: ExecutionContext) {

  def create() (implicit createdAt: LocalDateTime): Future[User] = {
    val user = User(UUID.randomUUID(), createdAt)
    userDao.insert(user).map { _ => user }
  }

}
