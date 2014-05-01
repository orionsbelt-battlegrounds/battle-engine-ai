
package obb.engine.actions

import obb.engine._

object InvalidAction {

  def message(msg : String) = {
    Action(new InvalidAction(msg), ActionArgs.empty)
  }

}

class InvalidAction(message : String) extends TurnAction {

  def process(args : ActionArgs, element : Element) : ActionResult = {
    ActionResult(false, args.board, 0, Some(message))
  }

}
