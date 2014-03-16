package obb.engine.actions

import obb.engine._

object AllMovement {

  def run( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) : ActionResult = {
    board.at(from) match {
      case Some(element) =>
        var table = board.table - from
        val quantityToMove = if(quantity < 0) element.quantity else quantity
        val remaining = element.quantity - quantityToMove
        if(remaining > 0) {
          table += (from -> element.forQuantity(remaining))
        }
        table += (to -> element.forQuantity(quantityToMove))
        ActionResult(true, Board(board.sizeX, board.sizeY, table), 1)
      case _ =>
        ActionResult(false, board, 0, Some(s"EmptyCoordinate:${from.x},${from.y}"))
    }
  }

}
