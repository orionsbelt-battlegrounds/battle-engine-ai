package obb.tests

import obb.engine._
import obb.formatters._

class AsciiBoardFormatterSpec extends UnitSpec {

  it("displays empty 8x8 board") {
    val board = Board.empty
    val formatter = new AsciiBoardFormatter(board)
    assert(formatter == """
     |  |  |  |  |  |  |  |  |
     |  |  |  |  |  |  |  |  |
     |  |  |  |  |  |  |  |  |
     |  |  |  |  |  |  |  |  |
     |  |  |  |  |  |  |  |  |
     |  |  |  |  |  |  |  |  |
     |  |  |  |  |  |  |  |  |
     |  |  |  |  |  |  |  |  |
    """ )
  }

  it("displays empty 3x3 board") {
    val board = Board(3, 3)
    val formatter = new AsciiBoardFormatter(board)
    assert(formatter == """
     |  |  |  |
     |  |  |  |
     |  |  |  |
    """ )
  }

  it("displays a board with an element") {
    val elements = Map[Coordinate, Element](
        Coordinate(1, 1) -> Element("1:1:~:S"),
        Coordinate(3, 3) -> Element("2:100:~:N")
      )
    val board = Board(3, 3, 2, elements)
    val formatter = new AsciiBoardFormatter(board)
    assert(formatter.maxElementSize == 9)
    assert(formatter == """
     | 1:1:~:S   |           |           |
     |           |           |           |
     |           |           | 2:100:~:N |
    """ )
  }
}
