package obb.tests

import obb.engine._
import obb.generators.TurnGenerator
import obb.evaluators._

class TurnGeneratorSpec extends UnitSpec {

  it("does nothing if there's nothing to be done") {
    val board = Board("""
      | 1:100:^:N |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run == None)
  }

  it("provides some random move") {
    val board = Board("""
     |           |           |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val possible = turnGenerator.possible
    assert(possible.size == 6)
  }

  it("selects an attack") {
    val board = Board("""
     |           | 2:100:^:N |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best.head
    assert(best.board.elementCount(Player.p2) == 0)
  }
}
