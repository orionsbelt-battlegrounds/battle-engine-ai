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

    describe("heavyDummy") {

      it("is available") {
        assert(CombatUnit.heavyDummy.name == "heavyDummy")
      }

      it("is findable by name") {
        assert(CombatUnit(CombatUnit.heavyDummy.name) == CombatUnit.heavyDummy)
      }

      it("is findable by code") {
        assert(CombatUnit(CombatUnit.heavyDummy.code) == CombatUnit.heavyDummy)
      }
    }

    describe("rain") {

      it("is findable by name") {
        assert(CombatUnit("rain").name == "rain")
      }

      it("is findable by code") {
        assert(CombatUnit("r").name == "rain")
      }
    }
  }

}
