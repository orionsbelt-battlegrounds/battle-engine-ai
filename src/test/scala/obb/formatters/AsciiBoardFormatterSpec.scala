package obb.tests

import obb.engine._
import obb.formatters._

class AsciiBoardFormatterSpec extends UnitSpec {

  it("displays empty 8x8 board") {
    val board = Board.empty
    val formatter = new AsciiBoardFormatter(board)
    assert(formatter == """
     | | | | | | | | |
     | | | | | | | | |
     | | | | | | | | |
     | | | | | | | | |
     | | | | | | | | |
     | | | | | | | | |
     | | | | | | | | |
     | | | | | | | | |
    """ )
  }

  it("displays empty 3x3 board") {
    val board = Board(3, 3)
    val formatter = new AsciiBoardFormatter(board)
    assert(formatter == """
     | | | |
     | | | |
     | | | |
    """ )
  }
}
