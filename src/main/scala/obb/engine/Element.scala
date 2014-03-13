package obb.engine

case class ParseElementException(msg : String) extends Exception

object Element {

  def apply(raw : String) : Element = {
    raw.trim.split(":") match {
      case Array(playerCode, quantity, unitCode, directionCode) =>
        Element(Player(playerCode.toInt), Unit(unitCode), quantity.toInt, Direction(directionCode))
      case _ =>
        throw new ParseElementException(s"Can't parse to element '$raw'")
    }

  }

}

case class Element(player: Player, unit : Unit, quantity : Int, direction : Direction) {

  override def toString = {
    s"${player.code}:$quantity:${unit.code}:$direction"
  }

}
