package web.endpoints.v1

import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

import scala.concurrent.ExecutionContext
import scala.concurrent.Future

import play.api.libs.json._
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents

import web.model.game.GameDao
import web.model.result.GameType
import web.model.result.ResultService
import web.model.user.UserDao


class Play @Inject() (
  gameDao: GameDao,
  userDao: UserDao,
  resultService: ResultService,
  protected val controllerComponents: ControllerComponents,
) (implicit executionContext: ExecutionContext) extends BaseController {

  def index(gameIdStr: String, gameTypeStr: String, rowStrategy: Int) = Action.async { request =>
    request.headers.get("userId") match {
      case None => Future.successful(BadRequest("Missing header `userId`."))
      case Some(userId) =>
        GameType.shortNameToMember(gameTypeStr) match {
          case None => Future.successful(BadRequest("Invalid game type."))
          case Some(gameType) =>
            userDao.get(UUID.fromString(userId)) flatMap {
              case None => Future.successful(BadRequest("User not found."))
              case Some(user) =>
                gameDao.get(UUID.fromString(gameIdStr)) flatMap {
                  case None => Future.successful(NotFound("Game not found."))
                  case Some(game) =>
                    resultService.create(user, game, gameType, rowStrategy)(LocalDateTime.now()) map { result =>
                      Ok(Json.toJson(Map("colStrategy" -> result.colStrategy)))
                    }
                }
            }
        }
    }
  }

}
