package obb.engine.actions

import obb.engine._

object DiagonalMovement {

  def run( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) = {
    new DiagonalMovement().run(new ActionArgs(board, from, to, quantity))
  }

  def action( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) = {
    Action(new DiagonalMovement(), new ActionArgs(board, from, to, quantity))
  }
}

class DiagonalMovement extends AllMovement {

  override def specificInvalidResult( args : ActionArgs ) : Option[ActionResult] = {
    if( args.from.x != args.to.x && args.from.y != args.to.y ) {
      None
    } else {
      Some(ActionResult(false, args.board, 0, Some("InvalidDiagonalMovement")))
    }
  }

}
