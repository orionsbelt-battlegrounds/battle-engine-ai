
package obb.engine

import obb.formatters._

object Board {

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

  def ~(raw : String) : PlayerTurn = PlayerTurn(this).push(raw)

  override def toString = new AsciiBoardFormatter(this).toString

  def =~(other : Board) : Boolean = this.toString == other.toString

  def freeze(coordinate : Coordinate) : Board = {
    val element = table.get(coordinate)

    if(element.isDefined) {
      Board(sizeX, sizeY, table + ( coordinate -> element.get.freeze ))
    } else {
      this
    }
  }

  def elementsFor(player : Player)( f : (Coordinate, Element) => Unit ) {
    table.foreach { case (coordinate, element) =>
      if(element.player == player) {
        f(coordinate, element)
      }
    }
  }

  def eachElement( f : (Element) => Unit ) {
    table.values.foreach(f)
  }

  def elementCount(player : Player) : Int = table.values.count(_.player == player)

  def outOfBounds( coord: Coordinate ) : Boolean = {
    coord.x < 1 || coord.y < 1 || coord.x > sizeX || coord.y > sizeY
  }

  def adjacent( reference: Coordinate, other : Coordinate ) : Boolean = {
    math.abs(reference.x - other.x) <= 1 && math.abs(reference.y - other.y) <= 1
  }

}
