
package obb.tests

import org.scalatest._
import obb.engine._
import obb.controllers._

class BasicPlayerSplitterControllerSpec extends UnitSpec {

  it("provides a play for a game") {
    val board = Board("""
     |           | 1:100:~:S |           |
     |           | 2:100:~:N |           |
     |           |           |           |
    """)

    val controller = new BasicPlayerSplitterController(Player.p1)
    val turn = controller.play(board)

    assert(turn.board.elementCount(Player.p2) == 0)


  }

}
