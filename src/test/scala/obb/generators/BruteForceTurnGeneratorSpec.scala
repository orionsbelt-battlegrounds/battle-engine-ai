package obb.tests

import obb.engine._
import obb.generators._
import obb.evaluators._

class BruteForceTurnGeneratorSpec extends UnitSpec {

  it("does nothing if there's nothing to be done") {
    val board = Board("""
      | 1:100:^:N |
    """)

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p1)
    assert(turnGenerator.run == None)
  }

  it("provides some random move") {
    val board = Board("""
     |           |           |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val possible = turnGenerator.possible
    assert(possible.size > 0)
  }

  it("selects an attack") {
    val board = Board("""
     |           | 2:100:^:N |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best
    assert(best.board.elementCount(Player.p2) == 0)
    assert(best.history.size == 1)
    assert(best.historyToString() == "b:2_2-2_1")
  }

  it("selects an attack for player 2") {
    val board = Board("""
     |           | 2:100:^:S |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p2)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best
    assert(best.board.elementCount(Player.p1) == 0)
    assert(best.history.size == 1)
    assert(best.historyToString() == "b:2_1-2_2")
  }

  it("selects two attacks") {
    val board = Board("""
     | 2:100:^:N | 2:100:^:N |           |
     | 1:100:^:N | 1:100:^:N |           |
    """)

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best
    assert(best.board.elementCount(Player.p2) == 0)
    assert(best.historyToString() == "b:1_2-1_1;b:2_2-2_1")
  }

  it("moves and attacks") {
    val board = Board("""
     | 2:100:^:N |           |           |
     |           |           |           |
     |           | 1:100:^:N |           |
    """)

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best
    assert(best.board.elementCount(Player.p2) == 0)
  }

  it("chooses the best move between 2 targets") {
    val board = Board("""
     | 2:100:^:N |           | 2:200:^:N |
     |           |           |           |
     |           | 1:200:^:N |           |
    """)

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best
    assert(best.board.elementCount(Player.p2) == 1)
    assert(best.board.at(1,1) != None)
  }

  it("rotates if needed") {
    val board = Board("""
     | 2:100:^:N |           |           |
     |           |           |           |
     |           | 1:100:^:E |           |
    """)

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best
    assert(best.board.elementCount(Player.p2) == 0)
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

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.best
    assert(best.board.elementCount(Player.p2) == 0)
  }

  it("splits units") {
    val board = Board("""
     | 2:100:^:N |           |           |
     |           |           |           |
     |           | 1:100:^:E |           |
    """)

    val turnGenerator = new BruteForceTurnGenerator(board, Player.p1)
    turnGenerator.run
    val turnGeneratorWithSplits = new BruteForceTurnGenerator(board, Player.p1, true)
    turnGeneratorWithSplits.run

    assert(turnGeneratorWithSplits.possible.size > turnGenerator.possible.size)
  }

}
