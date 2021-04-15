package structures

import scala.util.Try

import play.api.libs.json.JsResult
import play.api.libs.json.JsString
import play.api.libs.json.JsValue
import play.api.libs.json.Reads
import play.api.libs.json.Writes


object GameType extends Enumeration {

  case class Member(n: String) extends Val

  implicit def valueToMember(v: Value): Member = v.asInstanceOf[Member]

  val Nashian = Member("nash")
  val NonNashian = Member("non-nash")

  def shortNameToMember(n: String): Option[Member] = Map(
    "nash" -> GameType.Nashian,
    "non-nash" -> GameType.NonNashian,
  ).get(n)

  implicit val writes = new Writes[Member]  {
    def writes(m: Member): JsString = JsString(m.n)
  }

  implicit val reads = new Reads[Member] {
    override def reads(json: JsValue): JsResult[Member] = JsResult.fromTry {
      Try(shortNameToMember(json.as[String]).get)
    }
  }

}
