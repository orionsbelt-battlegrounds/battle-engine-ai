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

  def distinctFrom( dir : Direction )( f : (Direction) => Unit ) {
    if(dir != north) { f(north) }
    if(dir != south) { f(south) }
    if(dir != east) { f(east) }
    if(dir != west) { f(west) }
  }
}

case class Direction( code : String, x : Int, y : Int ) {

  override def toString = code

}
