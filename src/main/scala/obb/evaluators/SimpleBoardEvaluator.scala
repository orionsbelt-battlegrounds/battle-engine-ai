
package obb.evaluators

import obb.engine.{Board, Player}

class SimpleBoardEvaluator extends BoardEvaluator {

  def evaluate(board : Board, player : Player) : Float = {
    var sum = 0
    board.eachElement{ element =>
      val factor = if(element.player == player) 1 else -1
      sum += (element.unit.value * element.quantity * factor)
    }
    sum
  }

}
