package obb.engine

import obb.engine.actions._

object CombatUnit {

  def apply(raw : String) : CombatUnit = raw match {
    case mediumDummy.name => mediumDummy
    case mediumDummy.code => mediumDummy
    case heavyDummy.name => heavyDummy
    case heavyDummy.code => heavyDummy
    case _ => dummy
  }

  lazy val dummy = {
    CombatUnit("dummy", "~", 1, 100, 1, 100)
  }

  lazy val mediumDummy = {
    CombatUnit("mediumDummy", "^", 10, 1000, 3, 1000, 2)
  }

  lazy val heavyDummy = {
    CombatUnit("heavyDummy", "*", 20, 2000, 6, 2000, 4)
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
