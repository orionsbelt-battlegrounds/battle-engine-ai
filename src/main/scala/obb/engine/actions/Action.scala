
package obb.engine.actions

import obb.engine._

object Action {

  def parse(raw : String, turn : PlayerTurn) : Action = {
    raw.trim.split(":|-") match {
      case Array("m", rawFrom, rawTo, quantity) =>
        MovementType.action( turn.board, Coordinate(rawFrom), Coordinate(rawTo), quantity.toInt)
      case Array("b", rawFrom, rawTo) =>
        Attack.action(turn.board, Coordinate(rawFrom), Coordinate(rawTo))
      case Array("r", rawFrom, rawDir) =>
        ChangeDirection.action(turn.board, Coordinate(rawFrom), Direction(rawDir))
      case _ =>
        throw new Exception(s"Can't parse $raw")
    }
  }

  def getCode(action : Action) : String = {
    if( action.processor.isInstanceOf[Attack] ) {
      s"b:${action.args.from.code}-${action.args.to.code}"
    } else if( action.processor.isInstanceOf[AllMovement] ) {
      s"m:${action.args.from.code}-${action.args.to.code}-${action.args.quantity}"
    } else if( action.processor.isInstanceOf[ChangeDirection] ) {
      s"r:${action.args.from.code}-${action.args.direction.get}"
    } else {
      "unknown"
    }
  }

}

case class Action( processor : TurnAction, args : ActionArgs ) {

  def run = processor.run(args)

  def code = Action.getCode(this)

}
