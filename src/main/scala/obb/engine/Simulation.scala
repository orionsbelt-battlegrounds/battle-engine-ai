
package obb.engine

import obb.controllers._

class Simulation(board : Board) {

  var plays = List[PlayerTurn]()

  def play(first : PlayerController, second : PlayerController) : List[PlayerTurn] = {
    var currentBoard = board
    while(currentBoard.elementCount(first.player) > 0 && currentBoard.elementCount(second.player) > 0) {
      val firstTurn = first.play(currentBoard)
      plays ::= firstTurn
      val secondTurn = second.play(firstTurn.board)
      plays ::= secondTurn
      currentBoard = secondTurn.board.reset
    }
    plays
  }

  override def toString = {
    var str = "--\n"
    str ++= board.toString
    plays.foreach { turn =>
      str ++= "\n"
      str += turn.historyToString()
      str ++= "\n"
      str ++= turn.board.toString
      str ++= "\n"
    }
    str
  }

}
