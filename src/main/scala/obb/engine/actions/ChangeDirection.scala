
package obb.engine.actions

import obb.engine._

object ChangeDirection {

  def run( board : Board, coordinate : Coordinate, direction : Direction) = {
    action(board, coordinate, direction).run
  }

  def action( board : Board, coordinate : Coordinate, direction : Direction) = {
    Action(new ChangeDirection(), new ActionArgs(board, coordinate, coordinate, -1, Some(direction)))
  }

}

class ChangeDirection extends TurnAction {

  def process(args : ActionArgs, element : Element) : ActionResult = {
    val table = args.board.table + (args.from -> element.forDirection(args.direction.get))
    ActionResult(true, Board(args.board.sizeX, args.board.sizeY, table), 1)
  }


}
