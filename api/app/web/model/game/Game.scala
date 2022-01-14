package web.model.game

import java.time.LocalDateTime
import java.util.UUID

import play.api.libs.json.JsValue


case class Game(
  id: UUID,
  createdAt: LocalDateTime,
  rows: Int,
  columns: Int,
  matrix: JsValue,
  seed: Int,
)

