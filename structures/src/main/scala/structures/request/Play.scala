package structures.request

import play.api.libs.json.Json

import structures.GameType


case class Play(gameType: GameType.Member, rowStrategy: Int)


object Play {

  implicit val writes = Json.writes[Play]
  implicit val reads = Json.reads[Play]

}
