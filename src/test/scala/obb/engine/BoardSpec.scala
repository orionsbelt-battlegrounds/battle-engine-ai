package obb.tests

import org.scalatest._
import obb.engine._

class BoardSpec extends UnitSpec {

  describe("Board") {

    describe("#empty") {

      it("is possible to create an empty board") {
        val board = Board.empty
        assert(board.sizeX == 8)
        assert(board.sizeY == 8)
        assert(board.numberOfPlayers == 2)
      }

      it("is possible to create a board with specified dimensions") {
        val board = Board(3, 3)
        assert(board.sizeX == 3)
        assert(board.sizeY == 3)
        assert(board.numberOfPlayers == 2)
      }

      it("has 0 occupied slots") {
        assert(Board.empty.occupiedSlots == 0)
      }

      it("yields no elements") {
        assert(Board.empty.coordinates.size == 0)
        assert(Board.empty.elements.size == 0)
      }

    }

  }

}
