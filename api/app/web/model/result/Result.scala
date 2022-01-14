package web.model.result

import java.time.LocalDateTime
import java.util.UUID

import structures.GameType


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
