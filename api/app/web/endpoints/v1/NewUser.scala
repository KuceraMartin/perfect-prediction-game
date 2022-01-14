package web.endpoints.v1

import java.time.LocalDateTime
import javax.inject.Inject

import scala.concurrent.ExecutionContext

import play.api.libs.json.Json
import play.api.mvc.BaseController
import play.api.mvc.ControllerComponents
import structures.response.User

import web.model.user.UserService


class NewUser @Inject() (
  usersService: UserService,
  protected val controllerComponents: ControllerComponents,
) (implicit executionContext: ExecutionContext) extends BaseController {

  def index() = Action.async {
    usersService.create()(LocalDateTime.now()) map { user =>
      val str = User(user.id)
      Ok(Json.toJson(str))
    }
  }

}
