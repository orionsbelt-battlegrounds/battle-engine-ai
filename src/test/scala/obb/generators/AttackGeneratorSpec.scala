package obb.tests

import obb.engine._
import obb.generators.AttackGenerator

class AttackGeneratorSpec extends UnitSpec {

  it("returns empty if no attack is possible") {
    val board = Board("""
     |           |           |           |
     |           | 1:100:^:N |           |
    """)

    val turn = PlayerTurn(board)
    val choices = AttackGenerator.run(turn, Coordinate(2, 2))
    assert(choices == Nil)
  }

  it("returns an attack if possible") {
    val board = Board("""
     |           | 2:100:^:N |           |
     |           | 1:100:^:N |           |
    """)

    val turn = PlayerTurn(board)
    val choices = AttackGenerator.run(turn, Coordinate(2, 2))
    assert(choices.size == 1)

    val processed = choices.head
    assert(processed.valid == true)
    assert(processed.totalCost == 1)
    assert(processed.board == Board("""
     |           |           |           |
     |           | 1:100:^:N |           |
    """))
  }

}
