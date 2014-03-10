
package obb.engine


object Board {

  def default = empty
  def empty = {
    new Board(8, 8, 2)
  }

}

case class Board(
  val sizeX : Int = 8,
  val sizeY : Int = 8,
  val numberOfPlayers : Int = 2,
  val table : Map[Coordinate, Element] = Map()
) {
  lazy val coordinates = table.keys
  lazy val elements = table.values
  val occupiedSlots = coordinates.size
}