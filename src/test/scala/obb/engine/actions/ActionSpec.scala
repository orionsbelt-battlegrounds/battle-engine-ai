
package obb.tests

import org.scalatest._
import obb.engine._
import obb.engine.actions._

class ActionSpec extends UnitSpec {

  describe("#parse") {

    it("parses attack move") {
      val board = Board("""
       |           | 2:1:~:N   |           |
       |           |           |           |
       |           | 1:100:^:N |           |
      """)

      val action = Action.parse("b:2_3-2_1", PlayerTurn(board))
      assert(action.processor.isInstanceOf[Attack])

      assert(action.run.board == Board("""
       |           |           |           |
       |           |           |           |
       |           | 1:100:^:N |           |
        """))
    }

    it("parses all movement") {
      val board = Board("""
       |           | 1:100:^:S |           |
       |           |           |           |
      """)

      val action = Action.parse("m:2_1-2_2:100", PlayerTurn(board))
      assert(action.processor.isInstanceOf[AllMovement])

      assert(action.run.board == Board("""
       |           |           |           |
       |           | 1:100:^:S |           |
        """))
    }

  }

}
