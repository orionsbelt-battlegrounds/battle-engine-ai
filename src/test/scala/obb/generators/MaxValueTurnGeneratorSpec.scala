
package obb.tests

import obb.engine._
import obb.generators._
import obb.evaluators._

class MaxValueTurnGeneratorSpec extends UnitSpec {

  it("does nothing if there's nothing to be done") {
    val board = Board("""
      | 1:100:^:N |
    """)

    val turnGenerator = new MaxValueTurnGenerator(board, Player.p1)
    assert(turnGenerator.run == None)
  }

  it("provides some random move") {
    val board = Board("""
     |           |           |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new MaxValueTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)
  }

}
