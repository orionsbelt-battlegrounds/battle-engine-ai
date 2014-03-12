package obb.engine

object Direction {
  val north = Direction("N")
  val south = Direction("S")
  val east = Direction("E")
  val west = Direction("W")
}

case class Direction( code : String ) {

  override def toString = code
}
