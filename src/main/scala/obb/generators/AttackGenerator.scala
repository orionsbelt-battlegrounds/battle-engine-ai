package obb.generators

import obb.engine._
import obb.engine.actions._

object AttackGenerator {

  def run(turn : PlayerTurn, coordinate : Coordinate) = {
    new AttackGenerator().run(turn, coordinate)
  }

}

class AttackGenerator extends MoveGenerator {

  def process(turn : PlayerTurn, coordinate : Coordinate, element : Element) : List[PlayerTurn] = {
    var attacks = List[PlayerTurn]()

    resolveByDirection(turn, coordinate, element, element.direction) {  newTurn =>
      attacks ::= newTurn
    }

    if( ! turn.availableCost(2) ) {
      return attacks
    }

    Direction.distinctFrom(element.direction) { dir =>
      val rotatedTurn = turn ~ ChangeDirection.action(turn.board, coordinate, dir)
      resolveByDirection(rotatedTurn, coordinate, element, dir) {  newTurn =>
        attacks ::= newTurn
      }
    }

    attacks
  }

  def resolveByDirection( turn:PlayerTurn, coordinate : Coordinate, element : Element, dir : Direction)( f : (PlayerTurn) => Unit ) {
    for( i <- 1 to element.unit.range ) {
      val x = coordinate.x + i * dir.x
      val y = coordinate.y + i * dir.y
      val targetElement = turn.board.at(x, y)
      if( targetElement != None ) {
        val currentElement = targetElement.get
        if( currentElement.player != element.player ) {
          val newTurn = turn ~ Attack.action(turn.board, coordinate, Coordinate(x, y))
          f(newTurn)
          return
        }
      }
    }
  }

}
