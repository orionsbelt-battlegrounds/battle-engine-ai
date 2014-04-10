package obb.tests

import obb.engine._
import obb.generators.MovementGenerator

class MovementGeneratorSpec extends UnitSpec {

  it("returns empty if no movement is possible") {
    val board = Board("""
      | 1:100:^:N |
    """)

    val turn = PlayerTurn(board)
    val choices = MovementGenerator.run(turn, Coordinate(1, 1))
    assert(choices == Nil)
  }

  it("checks borders and other combat units") {
    val board = Board("""
      | 1:100:^:S |           |
      |           | 1:100:~:N |
    """)

    val turn = PlayerTurn(board)
    val choices = MovementGenerator.run(turn, Coordinate(1, 1))
    assert(choices.size == 2)

    val t1 = choices(1)
    assert(t1.valid == true)
    assert(t1.totalCost == CombatUnit("^").movementCost)
    assert(t1.board == Board("""
      |           | 1:100:^:S |
      |           | 1:100:~:N |
    """))

    val t2 = choices(0)
    assert(t2.valid == true)
    assert(t2.totalCost == CombatUnit("^").movementCost)
    assert(t2.board == Board("""
      |           |           |
      | 1:100:^:S | 1:100:~:N |
    """))
  }

  it("adds split options ") {
    val board = Board("""
      | 1:101:^:S |           |
      |           | 1:100:~:N |
    """)

    val turn = PlayerTurn(board)
    val choices = MovementGenerator.run(turn, Coordinate(1, 1), true)
    assert(choices.size == 4)
  }
}
