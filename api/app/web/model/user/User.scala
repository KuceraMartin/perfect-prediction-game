package web.model.user

import java.time.LocalDateTime
import java.util.UUID


case class User(
  id: UUID,
  createdAt: LocalDateTime,
)
