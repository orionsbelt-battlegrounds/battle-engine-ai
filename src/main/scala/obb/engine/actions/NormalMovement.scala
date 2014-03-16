package obb.engine.actions

import obb.engine._

object NormalMovement {

  def run( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) = {
    new NormalMovement().run(new ActionArgs(board, from, to, quantity))
  }

}

class NormalMovement extends AllMovement {

  override def invalidResult(args : ActionArgs) : Option[ActionResult] = {
    val superResult = super.invalidResult(args)
    if(superResult != None ) {
      superResult
    } else {
      if( args.from.x == args.to.x || args.from.y == args.to.y ) {
        None
      } else {
        Some(ActionResult(false, args.board, 0, Some("InvalidNormalMovement")))
      }
    }
  }

}
