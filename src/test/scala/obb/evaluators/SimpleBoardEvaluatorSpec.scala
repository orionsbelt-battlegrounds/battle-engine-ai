
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

      assert(evaluator.evaluate(board, Player.p3) == 0)
      assert(evaluator.evaluate(board, Player.p2) == CombatUnit("~").value * 50 + CombatUnit("^").value * 100)
      assert(evaluator.evaluate(board, Player.p1) == CombatUnit("~").value * 200)
  }

}
