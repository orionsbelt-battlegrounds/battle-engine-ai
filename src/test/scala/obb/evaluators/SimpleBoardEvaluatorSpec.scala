
package obb.tests

import org.scalatest._
import obb.engine._
import obb.evaluators.SimpleBoardEvaluator

class SimpleBoardEvaluatorSpec extends UnitSpec {

  it("evaluates by unit value") {
      val board = Board("""
       |           |           |           |
       |           | 2:50:~:N  |           |
       |           | 2:100:^:N |           |
       |           | 1:200:~:N |           |
       |           |           |           |
      """)

      val evaluator = new SimpleBoardEvaluator()

      val p2score = CombatUnit("~").value * 50 + CombatUnit("^").value * 100 - CombatUnit("~").value * 200
      assert(evaluator.evaluate(board, Player.p2) == p2score)
      val p1score = CombatUnit("~").value * -50 + CombatUnit("^").value * -100 + CombatUnit("~").value * 200
      assert(evaluator.evaluate(board, Player.p1) == p1score)
  }

}
