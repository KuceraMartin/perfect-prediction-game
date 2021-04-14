package structures.request

import play.api.libs.json.Json


case class Play(gameType: String, rowStrategy: Int)


object Play {

  implicit val writes = Json.writes[Play]
  implicit val reads = Json.reads[Play]

}
