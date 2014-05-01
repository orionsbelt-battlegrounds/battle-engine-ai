package obb.engine.actions

import obb.engine._

object ActionArgs {
  val empty = ActionArgs(new Board, Coordinate(-1, -1), Coordinate(-1, -1))
}

case class ActionArgs(
  board : Board,
  from : Coordinate,
  to : Coordinate,
  quantity : Int = -1,
  direction : Option[Direction] = None
)
