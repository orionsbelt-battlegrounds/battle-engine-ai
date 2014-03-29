
package obb.engine.actions

import obb.engine._

trait MovementAction extends TurnAction {

  def movementPossible(board : Board, from : Coordinate, to : Coordinate) : Boolean = {
    invalidResult(ActionArgs(board, from, to)) == None
  }

  def invalidResult(args : ActionArgs) : Option[ActionResult]

}
