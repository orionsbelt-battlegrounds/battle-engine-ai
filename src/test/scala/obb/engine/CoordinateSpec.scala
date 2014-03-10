
package obb.tests

import org.scalatest._
import obb.engine._

class CoordinateSpec extends UnitSpec {

  describe("#ctor") {

    it("builds from x,y") {
      val coordinate = Coordinate(1, 2)
      assert(coordinate.x == 1)
      assert(coordinate.y == 2)
    }

    it("builds from 'x_y'") {
      val coordinate = Coordinate("3_4")
      assert(coordinate == Coordinate(3, 4))
    }

    it("builds from 'x_y' with spaces") {
      val coordinate = Coordinate(" 3_4 ")
      assert(coordinate == Coordinate(3, 4))
    }

    it("builds from 'x-y'") {
      val coordinate = Coordinate("3-4")
      assert(coordinate == Coordinate(3, 4))
    }

    it("builds from 'x:y'") {
      val coordinate = Coordinate("3:4")
      assert(coordinate == Coordinate(3, 4))
    }

    it("fails if invalid format") {
      intercept[CoordinateParseException] {
        Coordinate("hello")
      }
    }
  }
}
