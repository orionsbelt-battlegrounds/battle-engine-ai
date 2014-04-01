package obb.generators

import scala.collection.SortedSet
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
    generatePly(turn)
    options.values.toList
  }

  var options : Map[Board, PlayerTurn] = Map[Board, PlayerTurn]()

  def generatePly(turn : PlayerTurn) {
    var ply = List[PlayerTurn]()
    turn.board.elementsFor(player) { (coordinate, element) =>
      ply ++= MovementGenerator.run(turn, coordinate)
      ply ++= AttackGenerator.run(turn, coordinate)
    }
    ply.foreach { playerTurn =>
      if( options.get(playerTurn.board) == None ) {
        options += (playerTurn.board -> playerTurn)
        generatePly(playerTurn)
      }
    }
  }

}
