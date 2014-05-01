
package obb.tests

import obb.engine._
import obb.generators._
import obb.evaluators._
import obb.generators.MaxValueTurnGenerator._

class PlaygroundSpec extends UnitSpec {
  it("selects an attack") {
    val board = Board("""
     |           | 2:100:^:N |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new MaxValueTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.run.get
    assert(best.board.elementCount(Player.p2) == 0)
    assert(best.historyToString() == "b:2_2-2_1")
  }
}
