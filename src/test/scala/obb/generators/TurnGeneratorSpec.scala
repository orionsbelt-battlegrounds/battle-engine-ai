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
    assert(possible.size > 0)
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
    assert(best.history.size == 1)
    assert(best.historyToString() == "b:2_2-2_1")
  }

  it("selects two attacks") {
    val board = Board("""
     | 2:100:^:N | 2:100:^:N |           |
     | 1:100:^:N | 1:100:^:N |           |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best.head
    assert(best.board.elementCount(Player.p2) == 0)
    assert(best.historyToString() == "b:1_2-1_1;b:2_2-2_1")
  }

  it("moves and attacks") {
    val board = Board("""
     | 2:100:^:N |           |           |
     |           |           |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best.head
    assert(best.historyToString() == "m:2_3-1_2-100;b:1_2-1_1")
  }

  it("chooses the best move between 2 targets") {
    val board = Board("""
     | 2:100:^:N |           | 2:200:^:N |
     |           |           |           |
     |           | 1:200:^:N |           |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best.head
    assert(best.historyToString() == "m:2_3-3_2-200;b:3_2-3_1")
  }

  it("rotates if needed") {
    val board = Board("""
     | 2:100:^:N |           |           |
     |           |           |           |
     |           | 1:100:^:E |           |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best.head
    assert(best.historyToString() == "m:2_3-1_2-100;r:1_2-N;b:1_2-1_1")
  }

  it("moves two squares and attacks") {
    val board = Board("""
     | 2:100:^:N |           |           |
     |           |           |           |
     |           |           |           |
     |           |           |           |
     |           |           |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new TurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best.head
    assert(best.historyToString() == "m:2_6-2_5-100;m:2_5-1_4-100;b:1_4-1_1")
  }

}
