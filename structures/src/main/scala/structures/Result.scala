package structures

import play.api.libs.json.Json


case class Result(columnStrategy: Int)


object Result {

  implicit val writes = Json.writes[Result]
  implicit val reads = Json.reads[Result]

}
