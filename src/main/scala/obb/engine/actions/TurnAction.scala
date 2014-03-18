package obb.engine.actions

import obb.engine._

trait TurnAction {

  def process(args : ActionArgs, element : Element ) : ActionResult

  def run( args : ActionArgs ) : ActionResult = {
    args.board.at(args.from) match {
      case Some(element) =>
        process(args, element)
      case _ =>
        ActionResult(false, args.board, 0, Some(s"EmptyCoordinate:${args.from.x},${args.from.y}"))
    }
  }


}
