package obb.tests

import obb.engine._
import obb.generators.AttackGenerator

class AttackGeneratorSpec extends UnitSpec {

  it("returns empty if no movement poits are available") {
    val board = Board("""
     |           | 2:100:^:N |           |
     |           | 1:100:^:N |           |
    """)

    val turn = PlayerTurn(board, 6)
    val choices = AttackGenerator.run(turn, Coordinate(2, 2))
    assert(choices == Nil)
  }

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
    assert(processed.board =~ Board("""
     |           |           |           |
     |           | 1:100:^:N |           |
    """))
  }

  it("changes direction to attack") {
    val board = Board("""
     |           | 1:100:^:N | 2:100:^:N |
    """)

    val turn = PlayerTurn(board)
    val choices = AttackGenerator.run(turn, Coordinate(2, 1))
    assert(choices.size == 1)

    val processed = choices.head
    assert(processed.valid == true)
    assert(processed.totalCost == 2)
    assert(processed.board =~ Board("""
     |           | 1:100:^:E |           |
    """))
  }

  it("provides direct attack and rotated attack") {
    val board = Board("""
     |           | 2:100:^:N |           |
     |           | 1:100:^:N | 2:100:^:N |
    """)

    val turn = PlayerTurn(board)
    val choices = AttackGenerator.run(turn, Coordinate(2, 2))
    assert(choices.size == 2)

    val direct = choices(1)
    assert(direct.valid == true)
    assert(direct.totalCost == 1)
    assert(direct.board =~ Board("""
     |           |           |           |
     |           | 1:100:^:N | 2:100:^:N |
    """))

    val rotated = choices(0)
    assert(rotated.valid == true)
    assert(rotated.totalCost == 2)
    assert(rotated.board =~ Board("""
     |           | 2:100:^:N |           |
     |           | 1:100:^:E |           |
    """))
  }

  it("provides direct attack and rejects rotate because of movement points") {
    val board = Board("""
     |           | 2:100:^:N |           |
     |           | 1:100:^:N | 2:100:^:N |
    """)

    val turn = PlayerTurn(board, 5)
    val choices = AttackGenerator.run(turn, Coordinate(2, 2))
    assert(choices.size == 1)

    val direct = choices.head
    assert(direct.valid == true)
    assert(direct.totalCost == 6)
    assert(direct.board =~ Board("""
     |           |           |           |
     |           | 1:100:^:N | 2:100:^:N |
    """))
  }

}
