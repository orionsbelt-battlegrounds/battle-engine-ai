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
    options.toList
  }

  val playerTurnOrder = Ordering[Int].on[PlayerTurn](_.totalCost)
  var options : SortedSet[PlayerTurn] = SortedSet[PlayerTurn]()(playerTurnOrder)

  def generatePly(turn : PlayerTurn) {
    var ply = List[PlayerTurn]()
    turn.board.elementsFor(player) { (coordinate, element) =>
      ply ++= MovementGenerator.run(turn, coordinate)
      ply ++= AttackGenerator.run(turn, coordinate)
    }
    options ++= ply
    ply.foreach { playerTurn =>
      generatePly(playerTurn)
    }
  }

}
