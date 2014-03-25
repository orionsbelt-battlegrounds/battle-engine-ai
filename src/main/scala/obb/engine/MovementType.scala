package obb.engine

import obb.engine.actions._

object MovementType {
  val all : TurnAction = new AllMovement()
  val diagonal : TurnAction = new DiagonalMovement()
  val normal : TurnAction = new NormalMovement()
  val front : TurnAction = new FrontMovement()

  def action(board: Board, from : Coordinate, to : Coordinate, quantity : Int) : Action = {
    val element = board.at(from).get
    if(element.unit.movementType == all) {
      return AllMovement.action(board, from, to, quantity)
    }
    if(element.unit.movementType == diagonal) {
      return DiagonalMovement.action(board, from, to, quantity)
    }
    FrontMovement.action(board, from, to, quantity)
  }
}
