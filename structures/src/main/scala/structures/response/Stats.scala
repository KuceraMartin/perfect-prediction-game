package structures.response

import play.api.libs.json.Json


case class Stats(gamesCount: Int, averageScore: Int)


object Stats {

  implicit val writes = Json.writes[Stats]
  implicit val reads = Json.reads[Stats]

}
