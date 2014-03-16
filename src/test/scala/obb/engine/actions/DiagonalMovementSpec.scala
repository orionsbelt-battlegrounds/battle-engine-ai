
package obb.tests

import org.scalatest._
import obb.engine._
import obb.engine.actions._

class DiagonalMovementSpec extends UnitSpec {

  val board = Board("""
   |           |           |           |
   |           | 2:100:~:N |           |
   |           |           |           |
  """)

  it("move element up fails") {
    val result = DiagonalMovement.run( board, Coordinate(2, 2), Coordinate(2, 1), 100)
    assert(result.msg == Some("InvalidDiagonalMovement"))
    assert(result.success == false)
  }

  it("move element down fails") {
    val result = DiagonalMovement.run( board, Coordinate(2, 2), Coordinate(2, 3), 100)
    assert(result.msg == Some("InvalidDiagonalMovement"))
    assert(result.success == false)
  }

  it("moves element left") {
    val result = DiagonalMovement.run( board, Coordinate(2, 2), Coordinate(1, 2), 100)
    assert(result.msg == Some("InvalidDiagonalMovement"))
    assert(result.success == false)
  }

  it("moves element right") {
    val result = DiagonalMovement.run(board, Coordinate(2, 2), Coordinate(3, 2))
    assert(result.msg == Some("InvalidDiagonalMovement"))
    assert(result.success == false)
  }

  it("moves element up right") {
    val result = DiagonalMovement.run(board, Coordinate(2, 2), Coordinate(3, 1))
    assert(result.success)
    assert(result.board == Board("""
     |           |           | 2:100:~:N |
     |           |           |           |
     |           |           |           |
    """))
  }

  it("moves element down right") {
    val result = DiagonalMovement.run(board, Coordinate(2, 2), Coordinate(3, 3))
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     |           |           |           |
     |           |           | 2:100:~:N |
    """))
  }

  it("moves element down left") {
    val result = DiagonalMovement.run(board, Coordinate(2, 2), Coordinate(1, 3))
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     |           |           |           |
     | 2:100:~:N |           |           |
    """))
  }

  it("moves element top left") {
    val result = DiagonalMovement.run(board, Coordinate(2, 2), Coordinate(1, 1))
    assert(result.success)
    assert(result.board == Board("""
     | 2:100:~:N |           |           |
     |           |           |           |
     |           |           |           |
    """))
  }

  it("moves element partially") {
    val result = DiagonalMovement.run(board, Coordinate(2, 2), Coordinate(1, 1), 50)
    assert(result.cost == result.board.at(1, 1).get.unit.movementCost * 2)
    assert(result.success)
    assert(result.board == Board("""
     | 2:50:~:N |          |          |
     |          | 2:50:~:N |          |
     |          |          |          |
    """))
  }

  it("fails if the source element doesn't exist") {
    val result = DiagonalMovement.run(board, Coordinate(1, 1), Coordinate(1, 1))
    assert(result.success == false)
    assert(result.board == board)
    assert(result.msg == Some("EmptyCoordinate:1,1"))
  }

  it("fails if to coordinate is out of bounds") {
    val result = DiagonalMovement.run(board, Coordinate(2, 2), Coordinate(15, 15))
    assert(result.success == false)
    assert(result.board == board)
    assert(result.msg == Some("OutOfBoundsCoordinate:15,15"))
  }

  it("fails if doesn't move to an adjacent square") {
    val board = Board("""
     |           |           |           |
     |           | 2:100:~:N |           |
     |           |           |           |
     |           |           |           |
    """)

    val result = DiagonalMovement.run(board, Coordinate(2, 2), Coordinate(2, 4))
    assert(result.msg == Some("NotAdjacentCoordinate:2,4"))
    assert(result.success == false)
    assert(result.board == board)
  }

}
