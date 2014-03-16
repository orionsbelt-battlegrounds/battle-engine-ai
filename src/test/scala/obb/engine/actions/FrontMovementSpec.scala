
package obb.tests

import org.scalatest._
import obb.engine._
import obb.engine.actions._

class FrontMovementSpec extends UnitSpec {

  val board = Board("""
   |           |           |           |
   |           | 2:100:~:N |           |
   |           |           |           |
  """)

  it("moves element north") {
    val result = FrontMovement.run( board, Coordinate(2, 2), Coordinate(2, 1), 100)
    assert(result.success)
    assert(result.board == Board("""
     |           | 2:100:~:N |           |
     |           |           |           |
     |           |           |           |
    """))
  }

  it("moves element south") {
    val board = Board("""
     |           |           |           |
     |           | 2:100:~:S |           |
     |           |           |           |
    """)

    val result = FrontMovement.run( board, Coordinate(2, 2), Coordinate(2, 3), 100)
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     |           |           |           |
     |           | 2:100:~:S |           |
    """))
  }

  it("moves element east") {
    val board = Board("""
     |           |           |           |
     |           | 2:100:~:E |           |
     |           |           |           |
    """)

    val result = FrontMovement.run( board, Coordinate(2, 2), Coordinate(3, 2), 100)
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     |           |           | 2:100:~:E |
     |           |           |           |
    """))
  }

  it("moves element weast") {
    val board = Board("""
     |           |           |           |
     |           | 2:100:~:W |           |
     |           |           |           |
    """)

    val result = FrontMovement.run( board, Coordinate(2, 2), Coordinate(1, 2), 100)
    assert(result.success)
    assert(result.board == Board("""
     |           |           |           |
     | 2:100:~:W |           |           |
     |           |           |           |
    """))
  }

  it("fails to move element down") {
    val result = FrontMovement.run( board, Coordinate(2, 2), Coordinate(2, 3), 100)
    assert(result.msg == Some("InvalidFrontMovement"))
    assert(result.success == false)
  }

  it("fails to move element left") {
    val result = FrontMovement.run( board, Coordinate(2, 2), Coordinate(1, 2), 100)
    assert(result.msg == Some("InvalidFrontMovement"))
    assert(result.success == false)
  }

  it("fails to move element right") {
    val result = FrontMovement.run(board, Coordinate(2, 2), Coordinate(3, 2))
    assert(result.msg == Some("InvalidFrontMovement"))
    assert(result.success == false)
  }

  it("fails to move element up right") {
    val result = FrontMovement.run(board, Coordinate(2, 2), Coordinate(3, 1))
    assert(result.msg == Some("InvalidFrontMovement"))
    assert(result.success == false)
  }

  it("fails to move element down right") {
    val result = FrontMovement.run(board, Coordinate(2, 2), Coordinate(3, 3))
    assert(result.msg == Some("InvalidFrontMovement"))
    assert(result.success == false)
  }

  it("fails to move element down left") {
    val result = FrontMovement.run(board, Coordinate(2, 2), Coordinate(1, 3))
    assert(result.msg == Some("InvalidFrontMovement"))
    assert(result.success == false)
  }

  it("fails move element top left") {
    val result = FrontMovement.run(board, Coordinate(2, 2), Coordinate(1, 1))
    assert(result.msg == Some("InvalidFrontMovement"))
    assert(result.success == false)
  }

  it("moves element partially") {
    val result = FrontMovement.run(board, Coordinate(2, 2), Coordinate(2, 1), 50)
    assert(result.cost == result.board.at(2, 1).get.unit.movementCost * 2)
    assert(result.success)
    assert(result.board == Board("""
     |          | 2:50:~:N |          |
     |          | 2:50:~:N |          |
     |          |          |          |
    """))
  }

}
