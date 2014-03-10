package obb.engine

case class CoordinateParseException(message : String) extends Exception

object Coordinate {

  def apply(raw : String) : Coordinate = {
    raw.trim.split("""_|-|:""") match {
      case Array(x, y) => Coordinate(x.toInt, y.toInt)
      case _ => throw new CoordinateParseException(s"Can't parse '$raw' into Coordinate")
    }
  }

}

case class Coordinate(x : Int, y : Int)
