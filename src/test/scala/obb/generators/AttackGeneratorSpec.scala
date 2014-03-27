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

}
