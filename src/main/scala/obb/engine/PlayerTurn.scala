
package obb.engine

import obb.engine.actions.{Action, TurnAction, ActionArgs, ActionResult}

case class PlayerTurn(
  board : Board,
  totalCost : Int = 0,
  valid : Boolean = true
) {

  def push( action : Action ) : PlayerTurn = {
    val result = action.run
    val newCost = result.cost + totalCost
    PlayerTurn(result.board, newCost, result.success && newCost <= 6 )
  }

}
