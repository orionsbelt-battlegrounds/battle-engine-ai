package obb.engine.actions

import obb.engine._

case class ActionResult(success : Boolean, board: Board, cost : Int, msg : Option[String] = None)
