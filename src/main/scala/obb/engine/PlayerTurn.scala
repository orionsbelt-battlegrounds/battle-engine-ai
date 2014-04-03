
package obb.engine

import obb.engine.actions.{Action, TurnAction, ActionArgs, ActionResult}

case class PlayerTurn(
  board : Board,
  totalCost : Int = 0,
  valid : Boolean = true,
  history : List[Pair[PlayerTurn, Action]] = Nil
) {

  val maxCost = 6

  def availableCost(cost : Int ) = totalCost + cost <= maxCost

  def ~(raw : String) = push(raw)
  def ~(action : Action) = push(action)
  def push( raw : String ) : PlayerTurn = push(Action.parse(raw, this))

  def push( action : Action ) : PlayerTurn = {
    val result = action.run
    val newCost = result.cost + totalCost

    PlayerTurn(
      result.board,
      newCost,
      valid && result.success && newCost <= maxCost,
      history :+ (this, action)
    )
  }

  def historyToString( sep : String = ";" ) = history.map(_._2.code).mkString(sep)

}
