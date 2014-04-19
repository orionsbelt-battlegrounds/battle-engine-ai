
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

  it("selects two attacks") {
    val board = Board("""
     |           | 2:100:^:N | 2:100:^:N |
     |           | 1:100:^:N | 1:100:^:N |
    """)

    val turnGenerator = new MaxValueTurnGenerator(board, Player.p1)
    assert(turnGenerator.run != None)

    val best = turnGenerator.run.get
    assert(best.board.elementCount(Player.p2) == 0)
    assert(best.historyToString() == "b:2_2-2_1;b:3_2-3_1")
  }

  describe(".possibleBest") {

    it("finds all adjacent options") {
      val board = Board("""
       |           |           |           |
       |           | 1:100:^:N |           |
      """)

      val generator = new MaxValueTurnGenerator(board, Player.p1)
      val coord = Coordinate(2, 2)
      val ce = CoordinateElement(coord, board.at(coord).get)
      val options = generator.possibleBest(PlayerTurn(board))(ce)
      //options foreach{ et => println(et.turn.historyToString()) }
      assert(options.size == 5)
    }

    it("resolves double ply") {
      val board = Board("""
       |           |
       |           |
       | 1:100:^:N |
      """)

      val generator = new MaxValueTurnGenerator(board, Player.p1)
      val coord = Coordinate(1, 3)
      val ce = CoordinateElement(coord, board.at(coord).get)
      val options = generator.possibleBest(PlayerTurn(board))(ce)
      //options foreach{ et => println(et.turn.historyToString()) }
      assert(options.size == 2)
    }

  }

}
