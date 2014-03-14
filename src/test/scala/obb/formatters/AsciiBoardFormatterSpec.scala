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
    val board = Board(3, 3, elements)
    val formatter = new AsciiBoardFormatter(board)
    assert(formatter.maxElementSize == 9)
    assert(formatter == """
     | 1:1:~:S   |           |           |
     |           |           |           |
     |           |           | 2:100:~:N |
    """ )
  }

  it("loads a board given a table in ascii") {
    val board = AsciiBoardFormatter("""
     | 1:1:~:S   |           |           |
     |           | 2:100:~:N |           |
     |           |           | 2:100:~:N |
    """)

    assert(board.at(1,1) == Some(Element("1:1:~:S")))
    assert(board.at(2, 1) == None)
    assert(board.at(3, 1) == None)

    assert(board.at(1, 2) == None)
    assert(board.at(2, 2) == Some(Element("2:100:~:N")))
    assert(board.at(3, 2) == None)

    assert(board.at(1, 3) == None)
    assert(board.at(2, 3) == None)
    assert(board.at(3, 3) == Some(Element("2:100:~:N")))

    assert(board.occupiedSlots == 3)
    assert(board.sizeX == 3)
    assert(board.sizeY == 3)
  }

  it("creates a board directly") {
    val board = Board("""
     | 2:100:~:N |
    """)

    assert(board.at(1, 1) == Some(Element("2:100:~:N")))
    assert(board.occupiedSlots == 1)
    assert(board.sizeX == 1)
    assert(board.sizeY == 1)
  }

  it("creates an empty board") {
    val board = Board("""
     |  |  |  |  |
     |  |  |  |  |
     |  |  |  |  |
    """)

    assert(board.occupiedSlots == 0)
    assert(board.sizeX == 4)
    assert(board.sizeY == 3)
  }

  it("outputs the same board that was loaded") {
    val raw = """
     |  |  |  |  |
     |  |  |  |  |
     |  |  |  |  |
    """

    val board = Board(raw)
    val formatter = new AsciiBoardFormatter(board)

    assert(formatter == board.toString)
  }
}
