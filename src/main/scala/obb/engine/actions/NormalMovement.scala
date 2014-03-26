package obb.engine.actions

import obb.engine._

object NormalMovement {

  def run( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) = {
    action(board, from, to, quantity).run
  }

  def action( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) = {
    Action(new NormalMovement(), new ActionArgs(board, from, to, quantity))
  }

}

class NormalMovement extends AllMovement {

  override def specificInvalidResult( args : ActionArgs ) : Option[ActionResult] = {
    if( args.from.x == args.to.x || args.from.y == args.to.y ) {
      None
    } else {
      Some(ActionResult(false, args.board, 0, Some("InvalidNormalMovement")))
    }
  }

}
