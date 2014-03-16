package obb.engine

object Direction {
  val north = Direction("N", 0, -1)
  val south = Direction("S", 0, 1)
  val east = Direction("E", 1, 0)
  val west = Direction("W", -1, 0)

  def apply(raw : String) : Direction = {
    raw match {
      case "N" => north
      case "S" => south
      case "W" => west
      case "E" => east
      case _ => throw new Exception(s"Don't know direction '$raw'")
    }
  }
}

case class Direction( code : String, x : Int, y : Int ) {

  override def toString = code

}
