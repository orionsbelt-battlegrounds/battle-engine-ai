
package obb.tests

import org.scalatest._
import obb.engine._
import obb.engine.actions._

class AllMovementSpec extends UnitSpec {

  val board = Board("""
   |           |           |           |
   |           | 2:100:~:N |           |
   |           |           |           |
  """)

  it("moves element up") {
    val result = AllMovement.run( board, Coordinate(2, 2), Coordinate(2, 1), 100)
    assert(result.success)
    assert(result.board == Board("""
     |           | 2:100:~:N |           |
     |           |           |           |
     |           |           |           |
    """))
  }

  it("moves element down") {
    val result = AllMovement.run( board, Coordinate(2, 2), Coordinate(2, 3), 100)
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     |           |           |           |
     |           | 2:100:~:N |           |
    """))
  }

  it("moves element left") {
    val result = AllMovement.run( board, Coordinate(2, 2), Coordinate(1, 2), 100)
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     | 2:100:~:N |           |           |
     |           |           |           |
    """))
  }

  it("moves element right") {
    val result = AllMovement.run(board, Coordinate(2, 2), Coordinate(3, 2))
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     |           |           | 2:100:~:N |
     |           |           |           |
    """))
  }

  it("moves element up right") {
    val result = AllMovement.run(board, Coordinate(2, 2), Coordinate(3, 1))
    assert(result.success)
    assert(result.board == Board("""
     |           |           | 2:100:~:N |
     |           |           |           |
     |           |           |           |
    """))
  }

  it("moves element down right") {
    val result = AllMovement.run(board, Coordinate(2, 2), Coordinate(3, 3))
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     |           |           |           |
     |           |           | 2:100:~:N |
    """))
  }

  it("moves element down left") {
    val result = AllMovement.run(board, Coordinate(2, 2), Coordinate(1, 3))
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     |           |           |           |
     | 2:100:~:N |           |           |
    """))
  }

  it("moves element top left") {
    val result = AllMovement.run(board, Coordinate(2, 2), Coordinate(1, 1))
    assert(result.success)
    assert(result.board == Board("""
     | 2:100:~:N |           |           |
     |           |           |           |
     |           |           |           |
    """))
  }

  it("moves element partially") {
    val result = AllMovement.run(board, Coordinate(2, 2), Coordinate(1, 1), 50)
    assert(result.success)
    assert(result.board == Board("""
     | 2:50:~:N |          |          |
     |          | 2:50:~:N |          |
     |          |          |          |
    """))
  }

  it("fails if the source element doesn't exist") {
    val result = AllMovement.run(board, Coordinate(1, 1), Coordinate(1, 1))
    assert(result.success == false)
    assert(result.board == board)
    assert(result.msg == Some("EmptyCoordinate:1,1"))
  }

  it("fails if to coordinate is out of bounds") {
    val result = AllMovement.run(board, Coordinate(2, 2), Coordinate(15, 15))
    assert(result.success == false)
    assert(result.board == board)
    assert(result.msg == Some("OutOfBoundsCoordinate:15,15"))
  }

}
