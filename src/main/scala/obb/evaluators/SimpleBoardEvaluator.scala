
package obb.evaluators

import obb.engine.{Board, Player}

class SimpleBoardEvaluator extends BoardEvaluator {

  def evaluate(board : Board, player : Player) : Float = {
    board.elements.foldLeft(0) { (acc, element) =>
      val factor = if(element.player == player) 1 else -1
      acc + (element.unit.value * element.quantity * factor)
    }
  }

}
