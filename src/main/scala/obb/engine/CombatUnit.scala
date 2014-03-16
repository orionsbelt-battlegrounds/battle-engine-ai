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
    CombatUnit("dummy", "~", 100, 100)
  }

  lazy val mediumDummy = {
    CombatUnit("dummy", "^", 1000, 1000, 2)
  }

}

case class CombatUnit(
  name : String,
  code : String,
  attack : Int,
  defense : Int,
  movementCost : Int = 1,
  movementType : TurnAction = MovementType.all
)
