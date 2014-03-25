
package obb.engine.actions

import obb.engine._

object Action {

  def parse(raw : String, turn : PlayerTurn) : Action = {
    AllMovement.action( turn.board, Coordinate(2, 2), Coordinate(2, 1), 100)
  }

}

case class Action( processor : TurnAction, args : ActionArgs ) {

  def run = processor.run(args)

}
