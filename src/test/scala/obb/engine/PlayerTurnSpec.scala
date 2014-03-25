
package obb.tests

import org.scalatest._
import obb.engine._
import obb.engine.actions._

class PlayerTurnSpec extends UnitSpec {

  describe(".push") {

    it("pushes movements") {
      val board = Board("""
       |           |           |           |
       |           | 2:100:~:N |           |
       |           |           |           |
      """)
      var turn = new PlayerTurn(board)
      turn = turn.push( AllMovement.action( board, Coordinate(2, 2), Coordinate(2, 1), 100) )
      assert(turn.totalCost == 1)
      assert(turn.board == Board("""
       |           | 2:100:~:N |           |
       |           |           |           |
       |           |           |           |
      """))
    }

    it("pushes movements by code") {
      val turn = Board("""
       |           |           |           |
       |           | 2:100:~:N |           |
       |           |           |           |
      """) ~ "m:2_2-2_1-100"

      assert(turn.totalCost == 1)
      assert(turn.board == Board("""
       |           | 2:100:~:N |           |
       |           |           |           |
       |           |           |           |
      """))
    }
  }

  describe(".valid") {

    it("checks max movements") {
      val board = Board("""
       |           |           |           |
       |           | 2:100:^:N |           |
       |           |           |           |
      """)
      var turn = new PlayerTurn(board)
      turn = turn.push( AllMovement.action( turn.board, Coordinate(2, 2), Coordinate(2, 1), 100) )
      turn = turn.push( AllMovement.action( turn.board, Coordinate(2, 1), Coordinate(2, 2), 100) )
      turn = turn.push( AllMovement.action( turn.board, Coordinate(2, 2), Coordinate(2, 1), 100) )
      turn = turn.push( AllMovement.action( turn.board, Coordinate(2, 2), Coordinate(2, 1), 100) )
      assert(turn.valid == false)
    }

  }

}
