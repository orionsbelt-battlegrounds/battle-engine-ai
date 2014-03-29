package obb.engine.actions

import obb.engine._

object AllMovement {

  def run( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) = {
    action(board, from, to, quantity).run
  }

  def action( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) = {
    Action(new AllMovement(), new ActionArgs(board, from, to, quantity))
  }

}

class AllMovement extends MovementAction {

  def invalidResult(args : ActionArgs) : Option[ActionResult] = {
    if( args.board.outOfBounds(args.to) ) {
      return Some(ActionResult(false, args.board, 0, Some(s"OutOfBoundsCoordinate:${args.to.x},${args.to.y}")))
    }
    if( !args.board.adjacent(args.from, args.to) ) {
      return Some(ActionResult(false, args.board, 0, Some(s"NotAdjacentCoordinate:${args.to.x},${args.to.y}")))
    }

    val toElement = args.board.at(args.to)
    if( toElement != None ) {
      val source = args.board.at(args.from).get
      val target = toElement.get
      if( source.player != target.player || source.unit != target.unit  ) {
        return Some(ActionResult(false, args.board, 0, Some("TargetNotAcceptable")))
      }
    }

    specificInvalidResult(args)
  }

  def specificInvalidResult( args : ActionArgs ) : Option[ActionResult] = None

  def process(args : ActionArgs, element : Element) : ActionResult = {
    invalidResult(args).getOrElse {
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
    }
  }

}
