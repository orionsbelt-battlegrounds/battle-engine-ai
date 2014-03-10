
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
  lazy val coordinates = table.keys
  lazy val elements = table.values
  val occupiedSlots = coordinates.size
}
