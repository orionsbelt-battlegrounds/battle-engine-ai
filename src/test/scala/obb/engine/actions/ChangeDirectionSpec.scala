
package obb.tests

import org.scalatest._
import obb.engine._
import obb.engine.actions._

class ChangeDirectionSpec extends UnitSpec {

  val board = Board("""
   |           |           |           |
   |           | 2:100:~:N |           |
   |           |           |           |
  """)

  it("fails if coordinate is invalid") {
    val result = ChangeDirection.run(board, Coordinate(1,1), Direction.south)
    assert(result.msg == Some("EmptyCoordinate:1,1"))
    assert(result.success == false)
  }

  it("changes direction to north") {
    val result = ChangeDirection.run(board, Coordinate(2, 2), Direction.north)
    assert(result.success == true)
    assert(result.board == Board("""
     |           |           |           |
     |           | 2:100:~:N |           |
     |           |           |           |
    """))
  }

  it("changes direction to south") {
    val result = ChangeDirection.run(board, Coordinate(2, 2), Direction.south)
    assert(result.success == true)
    assert(result.board == Board("""
     |           |           |           |
     |           | 2:100:~:S |           |
     |           |           |           |
    """))
  }

  it("changes direction to east") {
    val result = ChangeDirection.run(board, Coordinate(2, 2), Direction.east)
    assert(result.success == true)
    assert(result.board == Board("""
     |           |           |           |
     |           | 2:100:~:E |           |
     |           |           |           |
    """))
  }

  it("changes direction to west") {
    val result = ChangeDirection.run(board, Coordinate(2, 2), Direction.west)
    assert(result.success == true)
    assert(result.board == Board("""
     |           |           |           |
     |           | 2:100:~:W |           |
     |           |           |           |
    """))
  }

  it("costs 1 movement point") {
    val result = ChangeDirection.run(board, Coordinate(2, 2), Direction.west)
    assert(result.cost == 1)
  }

}
