
package obb.evaluators

import obb.engine.{Board, Player}

trait BoardEvaluator {
  def evaluate(board : Board, player : Player) : Int
}
