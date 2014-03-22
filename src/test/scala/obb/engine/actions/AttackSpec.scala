
package obb.tests

import org.scalatest._
import obb.engine._
import obb.engine.actions._

class AttackSpec extends UnitSpec {

  describe("basic attack") {

    it("verifies that it isn't the same player") {
      val board = Board("""
       |           | 1:1:~:N   |           |
       |           |           |           |
       |           | 1:100:^:N |           |
      """)

      val result = Attack.run(board, Coordinate(2,3), Coordinate(2, 1))
      assert(result.msg == Some("InvalidAttack:SamePlayer"))
      assert(result.success == false)
      assert(result.board == board)
    }

    it("verifies if is something in the way") {
      val board = Board("""
       |           | 2:1:~:N   |           |
       |           | 2:1:~:N   |           |
       |           |           |           |
       |           | 1:100:^:N |           |
      """)

      val result = Attack.run(board, Coordinate(2,4), Coordinate(2, 1))
      assert(result.success == false)
      assert(result.msg == Some("InvalidAttack:Obstacle"))
      assert(result.board == board)
    }

    it("verifies unit range") {
      val board = Board("""
       |           | 2:1:~:N   |           |
       |           |           |           |
       |           |           |           |
       |           |           |           |
       |           | 1:100:^:N |           |
      """)

      val result = Attack.run(board, Coordinate(2,5), Coordinate(2, 1))
      assert(result.success == false)
      assert(result.msg == Some("InvalidAttack:NoTargetReached"))
      assert(result.board == board)
    }

    it("can perform a simple north attack") {
      val board = Board("""
       |           | 2:1:~:N   |           |
       |           |           |           |
       |           | 1:100:^:N |           |
      """)

      val result = Attack.run(board, Coordinate(2,3), Coordinate(2, 1))
      assert(result.success == true)
      assert(result.cost == 1)
      assert(result.board.at(2, 1) == None)
    }

    it("can perform a simple south attack") {
      val board = Board("""
       |           | 1:100:^:S |           |
       |           |           |           |
       |           | 2:1:~:N   |           |
      """)

      val result = Attack.run(board, Coordinate(2,1), Coordinate(2, 3))
      assert(result.success == true)
      assert(result.cost == 1)
      assert(result.board.at(2, 3) == None)
    }

    it("can perform a simple east attack") {
      val board = Board("""
       |           | 1:100:^:E | 2:1:~:S   |
       |           |           |           |
       |           | 2:1:~:N   |           |
      """)

      val result = Attack.run(board, Coordinate(2,1), Coordinate(3, 1))
      assert(result.success == true)
      assert(result.cost == 1)
      assert(result.board.at(3, 1) == None)
    }

    it("can perform a simple west attack") {
      val board = Board("""
       | 2:1:~:S   | 1:100:^:W |           |
       |           |           |           |
       |           | 2:1:~:N   |           |
      """)

      val result = Attack.run(board, Coordinate(2,1), Coordinate(1, 1))
      assert(result.success == true)
      assert(result.cost == 1)
      assert(result.board.at(1, 1) == None)
    }

    it("can only attack on current direction") {
      val board = Board("""
       |           | 1:100:^:W |           |
       |           |           |           |
       |           | 2:1:~:N   |           |
      """)

      val result = Attack.run(board, Coordinate(2,1), Coordinate(2, 3))
      assert(result.success == false)
      assert(result.msg == Some("InvalidAttack:NoTargetReached"))
      assert(result.board == board)
    }

    it("attack an empty space does nothing") {
      val board = Board("""
       |           |           |           |
       |           |           |           |
       |           | 1:100:^:N |           |
      """)

      val result = Attack.run(board, Coordinate(2,3), Coordinate(2, 1))
      assert(result.success == true)
      assert(result.cost == 0)
      assert(result.board == board)
    }

  }

  describe(".getDistanceFactor") {

    it("properly calculates factor") {
      val attack = new Attack()

      assert( attack.getDistanceFactor( Coordinate(1, 1), Coordinate(2, 1) ) == 1)
      assert( attack.getDistanceFactor( Coordinate(1, 1), Coordinate(3, 1) ) == 1)
      assert( attack.getDistanceFactor( Coordinate(1, 1), Coordinate(4, 1) ) == 1)

      assert( attack.getDistanceFactor( Coordinate(1, 1), Coordinate(5, 1) ) == 0.75)
      assert( attack.getDistanceFactor( Coordinate(1, 1), Coordinate(6, 1) ) == 0.50)
      assert( attack.getDistanceFactor( Coordinate(1, 1), Coordinate(7, 1) ) == 0.25)

      assert( attack.getDistanceFactor( Coordinate(1, 1), Coordinate(8, 1) ) == 0)
    }

  }

  describe("partial damage attack") {

    it("destroys part of the target") {
      val board = Board("""
       |           | 2:200:^:S |           |
       |           |           |           |
       |           | 1:100:^:N |           |
      """)

      val result = Attack.run(board, Coordinate(2,3), Coordinate(2, 1))

      assert(result.board == Board("""
       |           | 2:100:^:S |           |
       |           |           |           |
       |           | 1:100:^:N |           |
      """))
    }

  }
}
