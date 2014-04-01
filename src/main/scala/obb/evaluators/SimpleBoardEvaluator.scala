
package obb.evaluators

import obb.engine.{Board, Player}

class SimpleBoardEvaluator extends BoardEvaluator {

  def evaluate(board : Board, player : Player) : Int = {
    var sum = 0
    board.elementsFor(player) { (coordinate, element) =>
      sum += element.unit.value * element.quantity
    }
    sum
  }

}
