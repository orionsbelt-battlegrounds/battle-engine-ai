package obb.tests

import obb.engine._
import obb.formatters._

class AsciiBoardFormatterSpec extends UnitSpec {

  it("displays empty board") {
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

}
