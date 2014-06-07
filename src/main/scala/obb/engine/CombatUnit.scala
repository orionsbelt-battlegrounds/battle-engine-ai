package obb.engine

import obb.engine.actions._

object CombatUnit {

  def apply(raw : String) : CombatUnit = all(raw)

  val all = loadCombatUnits

  lazy val dummy = apply("dummy")
  lazy val mediumDummy = apply("mediumDummy")
  lazy val heavyDummy = apply("heavyDummy")

  def loadCombatUnits : Map[String, CombatUnit] = {

    var map : Map[String, CombatUnit] = Map()

    // dumies
    map = add(map, CombatUnit("dummy", "~", 1, 100, 1, 100))
    map = add(map, CombatUnit("mediumDummy", "^", 100, 1000, 3, 1000, 2))
    map = add(map, CombatUnit("heavyDummy", "#", 1000, 2000, 6, 2000, 4))

    // lights
    map = add(map, CombatUnit("rain", "r", 4, 120, 1, 70, 1))

    map

  }

  def add(map : Map[String, CombatUnit], unit : CombatUnit) = {
    map + (unit.code -> unit) + (unit.name -> unit)
  }

}

case class CombatUnit(
  name : String,
  code : String,
  value : Int,
  attack : Int,
  range : Int = 1,
  defense : Int,
  movementCost : Int = 1,
  movementType : MovementAction = MovementType.all
)
