package obb.tests

import org.scalatest._
import obb.engine._

class BoardSpec extends UnitSpec {

  describe("Board") {

    describe("#adjacent") {

      it("succeeds on adjacent squares") {
        val board = Board.empty
        for( x <- 1 to 3 ) {
          for( y <- 1 to 3 ) {
            assert( board.adjacent( Coordinate(2, 2), Coordinate(x, y)) == true )
          }
        }
      }

      it("fails for not adjacent squares") {
        val board = Board.empty
        assert( board.adjacent( Coordinate(2, 2), Coordinate(4, 4)) == false )
        assert( board.adjacent( Coordinate(2, 2), Coordinate(1, 4)) == false )
        assert( board.adjacent( Coordinate(2, 2), Coordinate(4, 1)) == false )
      }

    }

    describe("#outOfBounds") {

      it("succeeds if coordinate is inside board") {
        val board = Board.empty
        assert( board.outOfBounds(Coordinate(1, 1))  == false )
        assert( board.outOfBounds(Coordinate(board.sizeX, board.sizeY))  == false )
      }

      it("fails if coordinate is outside the board") {
        val board = Board.empty
        assert( board.outOfBounds(Coordinate(0, 0))  == true )
        assert( board.outOfBounds(Coordinate(1, 0))  == true )
        assert( board.outOfBounds(Coordinate(0, 1))  == true )

        assert( board.outOfBounds(Coordinate(board.sizeX + 1, board.sizeY))  == true )
        assert( board.outOfBounds(Coordinate(board.sizeX, board.sizeY + 1))  == true )
        assert( board.outOfBounds(Coordinate(board.sizeX + 1, board.sizeY + 1))  == true )
      }

    }

    describe("#empty") {

      it("is possible to create an empty board") {
        val board = Board.empty
        assert(board.sizeX == 8)
        assert(board.sizeY == 8)
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
