package structures

object GameType extends Enumeration {

  case class Member(n: String) extends Val

  implicit def valueToMember(v: Value): Member = v.asInstanceOf[Member]

  val Nashian = Member("nash")
  val NonNashian = Member("non-nash")

  def shortNameToMember(n: String): Option[Member] = Map(
    "nash" -> GameType.Nashian,
    "non-nash" -> GameType.NonNashian,
  ).get(n)

}
