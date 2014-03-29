package obb.engine

import obb.engine.actions._

object CombatUnit {

  def apply(raw : String) : CombatUnit = {
    if(raw.trim == mediumDummy.code) {
      return mediumDummy
    }
    dummy
  }

  lazy val dummy = {
    CombatUnit("dummy", "~", 100, 1, 100)
  }

  lazy val mediumDummy = {
    CombatUnit("dummy", "^", 1000, 3, 1000, 2)
  }

}

case class CombatUnit(
  name : String,
  code : String,
  attack : Int,
  range : Int = 1,
  defense : Int,
  movementCost : Int = 1,
  movementType : MovementAction = MovementType.all
)
