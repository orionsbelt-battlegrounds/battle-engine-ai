
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
    val processed = AllMovement.run( board, Coordinate(2, 2), Coordinate(2, 1), 100)
    assert(processed == Board("""
     |           | 2:100:~:N |           |
     |           |           |           |
     |           |           |           |
    """))
  }

  it("moves element down") {
    val processed = AllMovement.run( board, Coordinate(2, 2), Coordinate(2, 3), 100)
    assert(processed == Board("""
     |           |           |           |
     |           |           |           |
     |           | 2:100:~:N |           |
    """))
  }

  it("moves element left") {
    val processed = AllMovement.run( board, Coordinate(2, 2), Coordinate(1, 2), 100)
    assert(processed == Board("""
     |           |           |           |
     | 2:100:~:N |           |           |
     |           |           |           |
    """))
  }

  it("moves element right") {
    val processed = AllMovement.run(board, Coordinate(2, 2), Coordinate(3, 2))
    assert(processed == Board("""
     |           |           |           |
     |           |           | 2:100:~:N |
     |           |           |           |
    """))
  }

  it("moves element up right") {
    val processed = AllMovement.run(board, Coordinate(2, 2), Coordinate(3, 1))
    assert(processed == Board("""
     |           |           | 2:100:~:N |
     |           |           |           |
     |           |           |           |
    """))
  }

  it("moves element down right") {
    val processed = AllMovement.run(board, Coordinate(2, 2), Coordinate(3, 3))
    assert(processed == Board("""
     |           |           |           |
     |           |           |           |
     |           |           | 2:100:~:N |
    """))
  }

  it("moves element down left") {
    val processed = AllMovement.run(board, Coordinate(2, 2), Coordinate(1, 3))
    assert(processed == Board("""
     |           |           |           |
     |           |           |           |
     | 2:100:~:N |           |           |
    """))
  }

  it("moves element top left") {
    val processed = AllMovement.run(board, Coordinate(2, 2), Coordinate(1, 1))
    assert(processed == Board("""
     | 2:100:~:N |           |           |
     |           |           |           |
     |           |           |           |
    """))
  }

  it("moves element partially") {
    val processed = AllMovement.run(board, Coordinate(2, 2), Coordinate(1, 1), 50)
    assert(processed == Board("""
     | 2:50:~:N |          |          |
     |          | 2:50:~:N |          |
     |          |          |          |
    """))
  }
}
