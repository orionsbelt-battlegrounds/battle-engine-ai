package obb.engine

import obb.engine.actions._

object MovementType {
  val all : MovementAction = new AllMovement()
  val diagonal : MovementAction = new DiagonalMovement()
  val normal : MovementAction = new NormalMovement()
  val front : MovementAction = new FrontMovement()

  def action(board: Board, from : Coordinate, to : Coordinate, quantity : Int) : Action = {
    val elementOption = board.at(from)
    if(elementOption == None) {
      return InvalidAction.message(s"No element at ${from}")
    }
    val element = elementOption.get
    if(element.unit.movementType == all) {
      return AllMovement.action(board, from, to, quantity)
    }
    if(element.unit.movementType == diagonal) {
      return DiagonalMovement.action(board, from, to, quantity)
    }
    FrontMovement.action(board, from, to, quantity)
  }
}
