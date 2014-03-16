package obb.engine.actions

import obb.engine._

object AllMovement {

  def run( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) = {
    new AllMovement().run(new ActionArgs(board, from, to, quantity))
  }

}

class AllMovement extends TurnAction {

  def run( args : ActionArgs ) : ActionResult = {
    args.board.at(args.from) match {
      case Some(element) =>
        if( args.board.outOfBounds(args.to) ) {
          return ActionResult(false, args.board, 0, Some(s"OutOfBoundsCoordinate:${args.to.x},${args.to.y}"))
        }
        if( !args.board.adjacent(args.from, args.to) ) {
          return ActionResult(false, args.board, 0, Some(s"NotAdjacentCoordinate:${args.to.x},${args.to.y}"))
        }
        var table = args.board.table - args.from
        val quantityToMove = if(args.quantity < 0) element.quantity else args.quantity
        val remaining = element.quantity - quantityToMove
        var mulFactor = 1

        if(remaining > 0) {
          mulFactor = 2
          table += (args.from -> element.forQuantity(remaining))
        }
        table += (args.to -> element.forQuantity(quantityToMove))

        ActionResult(true, Board(args.board.sizeX, args.board.sizeY, table), element.unit.movementCost * mulFactor)

      case _ =>
        ActionResult(false, args.board, 0, Some(s"EmptyCoordinate:${args.from.x},${args.from.y}"))
    }
  }

}
