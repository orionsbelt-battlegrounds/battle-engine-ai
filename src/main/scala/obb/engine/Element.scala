package obb.engine

case class ParseElementException(msg : String) extends Exception(msg)

object Element {

  def apply(raw : String) : Element = {
    raw.trim.split(":") match {
      case Array(playerCode, quantity, unitCode, directionCode) =>
        Element(Player(playerCode.toInt), CombatUnit(unitCode), quantity.toInt, Direction(directionCode))
      case _ =>
        throw new ParseElementException(s"Can't parse to element '$raw'")
    }

  }

}

case class Element(
  player: Player,
  unit : CombatUnit,
  quantity : Int,
  direction : Direction,
  froozen : Boolean = false) {

  override def toString = {
    s"${player.code}:$quantity:${unit.code}:$direction"
  }

  def forQuantity( newQuantity : Int ) : Element = {
    Element(player, unit, newQuantity, direction)
  }

  def forDirection( newDirection : Direction ) : Element = {
    Element(player, unit, quantity, newDirection)
  }

  def freeze = {
    Element(player, unit, quantity, direction, true)
  }

}
