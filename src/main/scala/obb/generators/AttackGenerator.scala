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
    for( i <- 1 to element.unit.range ) {
      val x = coordinate.x + i * element.direction.x
      val y = coordinate.y + i * element.direction.y
      val targetElement = turn.board.at(x, y)
      if( targetElement != None ) {
        val currentElement = targetElement.get
        if( currentElement.player != element.player ) {
          val newTurn = turn ~ Attack.action(turn.board, coordinate, Coordinate(x, y))
          return List(newTurn)
        }
      }
    }

    Nil
  }

}
