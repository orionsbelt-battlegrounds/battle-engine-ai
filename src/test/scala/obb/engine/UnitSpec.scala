package obb.tests

import org.scalatest._
import obb.engine._

class CombatUnitSpec extends UnitSpec {

  describe("Unit") {

    describe("dummy") {

      it("is available") {
        assert(Unit.dummy.name == "dummy")
      }

      it("is findable by name") {
        assert(Unit(Unit.dummy.name) == Unit.dummy)
      }

      it("is findable by code") {
        assert(Unit(Unit.dummy.code) == Unit.dummy)
      }

    }

  }

}
