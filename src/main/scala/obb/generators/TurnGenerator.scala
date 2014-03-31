package obb.generators

import obb.engine._
import obb.engine.actions._

class TurnGenerator(board : Board, player : Player) {

  def run : Option[PlayerTurn] = {
    val turns = possible
    if(turns == Nil) {
      None
    } else {
      Some(possible.head)
    }
  }

  def possible : List[PlayerTurn] = {
    val turn = PlayerTurn(board)
    var options = List[PlayerTurn]()
    turn.board.elementsFor(player) { (coordinate, element) =>
      options ++= MovementGenerator.run(turn, coordinate)
      options ++= AttackGenerator.run(turn, coordinate)
    }
    options
  }


}
