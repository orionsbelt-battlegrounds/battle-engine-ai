
package obb.engine

import obb.formatters._

object Board {

  def default = empty
  def empty = {
    new Board(8, 8)
  }

  def apply( raw : String ) : Board = AsciiBoardFormatter(raw)

}

case class Board(
  val sizeX : Int = 8,
  val sizeY : Int = 8,
  val table : Map[Coordinate, Element] = Map()
) {
  lazy val coordinates = table.keys
  lazy val elements = table.values
  val occupiedSlots = coordinates.size

  def at(x : Int, y : Int) : Option[Element] = at(Coordinate(x, y))
  def at(coordinate : Coordinate) : Option[Element] = table.get(coordinate)

  override def toString = new AsciiBoardFormatter(this).toString

  def eachElement( f : (Element) => Unit ) {
    table.values.foreach(f)
  }

  def outOfBounds( coord: Coordinate ) : Boolean = {
    coord.x < 1 || coord.y < 1 || coord.x > sizeX || coord.y > sizeY
  }

  def adjacent( reference: Coordinate, other : Coordinate ) : Boolean = {
    math.abs(reference.x - other.x) <= 1 && math.abs(reference.y - other.y) <= 1
  }

}
