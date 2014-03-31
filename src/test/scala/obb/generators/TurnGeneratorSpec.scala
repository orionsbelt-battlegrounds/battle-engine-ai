package obb.tests

import obb.engine._
import obb.generators.TurnGenerator

class TurnGeneratorSpec extends UnitSpec {

  it("does nothing if there's nothing to be done") {
    val board = Board("""
      | 1:100:^:N |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run == None)
  }

  it("provies some random move") {
    val board = Board("""
     |           |           |           |
     |           | 1:100:^:N |           |
     |           |           |           |
     |           | 1:100:^:N |           |
     |           |           |           |
     |           |           |           |
     |           | 1:100:^:N |           |
     |           |           |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val possible = turnGenerator.possible
    possible.foreach(println)
    assert(possible.size == 3)
  }
}
