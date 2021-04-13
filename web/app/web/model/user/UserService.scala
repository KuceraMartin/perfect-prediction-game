package web.model.user

import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import structures.Stats

import web.model.result.ResultDao


class UserService @Inject() (
  userDao: UserDao,
  resultDao: ResultDao,
) (implicit ec: ExecutionContext) {

  def create() (implicit createdAt: LocalDateTime): Future[User] = {
    val user = User(UUID.randomUUID(), createdAt)
    userDao.insert(user).map { _ => user }
  }


  def stats(user: User): Future[Stats] = {
    for {
      gamesCount <- resultDao.gamesCount(user.id)
      avgScore <- resultDao.averageScore(user.id)
    } yield Stats(gamesCount, avgScore)
  }

}
