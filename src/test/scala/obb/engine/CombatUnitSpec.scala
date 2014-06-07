package obb.tests

import org.scalatest._
import obb.engine._

class CombatUnitSpec extends UnitSpec {

  describe("CombatUnit") {

    describe("dummy") {

      it("is available") {
        assert(CombatUnit.dummy.name == "dummy")
      }

      it("is findable by name") {
        assert(CombatUnit(CombatUnit.dummy.name) == CombatUnit.dummy)
      }

      it("is findable by code") {
        assert(CombatUnit(CombatUnit.dummy.code) == CombatUnit.dummy)
      }

    }

    describe("mediumDummy") {

      it("is available") {
        assert(CombatUnit.mediumDummy.name == "mediumDummy")
      }

      it("is findable by name") {
        assert(CombatUnit(CombatUnit.mediumDummy.name) == CombatUnit.mediumDummy)
      }

      it("is findable by code") {
        assert(CombatUnit(CombatUnit.mediumDummy.code) == CombatUnit.mediumDummy)
      }
    }

  }

}
