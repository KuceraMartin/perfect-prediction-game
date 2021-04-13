package web.endpoints.v1

import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import play.api.libs.json._
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents
import structures.Stats

import web.model.result.ResultDao
import web.model.user.UserDao
import web.model.user.UserService


class Stats @Inject() (
  userDao: UserDao,
  userService: UserService,
  protected val controllerComponents: ControllerComponents,
) (implicit executionContext: ExecutionContext) extends BaseController {

  def index() = Action.async { request =>
    request.headers.get("userId") match {
      case None => Future.successful(BadRequest("Missing header `userId`."))
      case Some(userId) =>
        userDao.get(UUID.fromString(userId)) flatMap {
          case None => Future.successful(BadRequest("User not found."))
          case Some(user) =>
            userService.stats(user) map { stats =>
              Ok(Json.toJson(stats))
            }
        }
    }
  }

}
