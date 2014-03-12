package obb.engine

object Unit {

  def apply(raw : String) : Unit = {
    dummy
  }

  lazy val dummy = {
    Unit("dummy", "~", 100, 100)
  }

}

case class Unit(name : String, code : String, attack : Int, defense : Int)
