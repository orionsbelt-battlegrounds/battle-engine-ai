package obb.engine

import obb.engine.actions._

object MovementType {
  val all : TurnAction = new AllMovement()
  val diagonal : TurnAction = new DiagonalMovement()
  val normal : TurnAction = new NormalMovement()
}
