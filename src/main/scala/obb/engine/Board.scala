
package obb.engine


object Board {

  def empty = {
    new Board(Map())
  }

}

case class Board(table : Map[Coordinate, Element]) {
  val numberOfPlayers : Int = 2
  val sizeX : Int = 8
  val sizeY : Int = 8
  val occupiedSlots = 0
  val coordinates = table.keys
  val elements = table.values

}
