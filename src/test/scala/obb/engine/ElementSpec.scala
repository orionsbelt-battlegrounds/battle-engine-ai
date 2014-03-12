package obb.tests

import org.scalatest._
import obb.engine._

class ElementSpec extends UnitSpec {

  it("is available") {
    val element = Element(Player.p1, Unit.dummy, 1000, Direction.north)
    assert(element.unit == Unit.dummy)
  }

  it("is exportable to string") {
    val element = Element(Player.p1, Unit.dummy, 1000, Direction.north)
    assert(element.toString == "1:1000:~:N")
  }

  it("loads from a string") {
    val element = Element("1:100:~:W")
    assert(element.unit == Unit.dummy)
    assert(element.quantity == 100)
    assert(element.player == Player(1))
    assert(element.direction == Direction.west)
  }

  it("fails parsing invalid string") {
    intercept[ParseElementException] {
      Element("hello")
    }
  }
}
