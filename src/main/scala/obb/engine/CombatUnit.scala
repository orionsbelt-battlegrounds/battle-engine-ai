package obb.engine

import obb.engine.actions._

object CombatUnit {

  def apply(raw : String) : CombatUnit = {
    dummy
  }

  lazy val dummy = {
    CombatUnit("dummy", "~", 100, 100)
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
