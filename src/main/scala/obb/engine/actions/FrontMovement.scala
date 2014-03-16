
package obb.engine.actions

import obb.engine._

object FrontMovement {

  def run( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 ) = {
    new FrontMovement().run(new ActionArgs(board, from, to, quantity))
  }

}

class FrontMovement extends AllMovement {

  override def invalidResult(args : ActionArgs) : Option[ActionResult] = {
    val superResult = super.invalidResult(args)
    if(superResult != None ) {
      superResult
    } else {
      var element = args.board.at(args.from).get
      if( args.from.x + element.direction.x == args.to.x && args.from.y + element.direction.y == args.to.y ) {
        None
      } else {
        Some(ActionResult(false, args.board, 0, Some("InvalidFrontMovement")))
      }
    }
  }

}
