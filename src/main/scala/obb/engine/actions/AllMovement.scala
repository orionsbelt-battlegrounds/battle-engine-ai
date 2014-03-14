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
          table += (from -> Element(element.player, element.unit, remaining, element.direction))
        }
        var added = table + (to -> Element(element.player, element.unit, quantityToMove, element.direction))
        ActionResult(true, Board(board.sizeX, board.sizeY, added), 1)
      case _ => throw new Exception("Error")
    }
  }

}
