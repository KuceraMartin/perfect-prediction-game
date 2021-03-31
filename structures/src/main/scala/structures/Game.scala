package structures

import java.util.UUID

import play.api.libs.json.Json


case class Game(id: UUID, matrix: Seq[Seq[(Int, Int)]])

object Game {

  implicit val writes = Json.writes[Game]
  implicit val reads = Json.reads[Game]

}
