package structures

import java.util.UUID

import play.api.libs.json.Json


case class User(id: UUID)


object User {

  implicit val writes = Json.writes[User]
  implicit val reads = Json.reads[User]

}
