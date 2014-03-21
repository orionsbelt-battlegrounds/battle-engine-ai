package obb.engine

case class CoordinateParseException(message : String) extends Exception(message)

object Coordinate {

  def apply(raw : String) : Coordinate = {
    raw.trim.split("""_|-|:""") match {
      case Array(x, y) => Coordinate(x.toInt, y.toInt)
      case _ => throw new CoordinateParseException(s"Can't parse '$raw' into Coordinate")
    }
  }

  def distance(c1 : Coordinate, c2 : Coordinate) : Int = {
    if(c1.x == c2.x) {
      Math.abs(c1.y - c2.y)
    } else {
      Math.abs(c1.x - c2.x)
    }
  }

}

case class Coordinate(x : Int, y : Int) {

  def ~(other : Coordinate) : Int = {
    Coordinate.distance(this, other)
  }
}
