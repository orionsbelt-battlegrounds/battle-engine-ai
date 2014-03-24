
package obb.engine

import obb.engine.actions.{Action, TurnAction, ActionArgs, ActionResult}

case class PlayerTurn(
  board : Board,
  totalCost : Int = 0
) {

  def push( action : Action ) : PlayerTurn = {
    val result = action.run
    PlayerTurn(result.board, result.cost + totalCost)
  }



}
