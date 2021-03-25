package web.endpoints.v1

import java.time.LocalDateTime
import javax.inject.Inject

import scala.concurrent.ExecutionContext

import play.api.libs.json.Json
import play.api.libs.json.Writes
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents

import web.model.user.User
import web.model.user.UserService


class NewUser @Inject() (
  usersService: UserService,
  protected val controllerComponents: ControllerComponents,
) (implicit executionContext: ExecutionContext) extends BaseController {

  implicit val userWrites = new Writes[User] {
    override def writes(u: User) = Json.obj(
      "id" -> u.id,
    )
  }

  def index() = Action.async {
    usersService.create()(LocalDateTime.now()) map { user =>
      Ok(Json.toJson(user))
    }
  }

}
