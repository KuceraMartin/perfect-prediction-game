package web.model.result

import java.time.LocalDateTime
import java.util.UUID

import slick.ast.BaseTypedType

import web.model.SlickPgProfile


object GameType extends Enumeration {

  case class Member(n: String) extends Val

  implicit def valueToMember(v: Value): Member = v.asInstanceOf[Member]

  val Nashian = Member("nash")
  val NonNashian = Member("non-nash")

  def shortNameToMember(n: String): Option[Member] = Map(
    "nash" -> GameType.Nashian,
    "non-nash" -> GameType.NonNashian,
  ).get(n)

  implicit val gameTypeMapping: BaseTypedType[Member] = new SlickPgProfile.GenericJdbcType[Member](
    "game_type",
    GameType.withName,
    _.n,
  )

}


case class Result(
  id: UUID,
  createdAt: LocalDateTime,
  userId: UUID,
  gameId: UUID,
  gameType: GameType.Member,
  rowStrategy: Int,
  colStrategy: Int,
  rowPayoff: Int,
)
