
package obb.generators

import obb.engine._
import obb.engine.actions._

object MovementGenerator {

  def run(turn : PlayerTurn, coordinate : Coordinate) = {
    new MovementGenerator().run(turn, coordinate)
  }

}

class MovementGenerator extends MoveGenerator {

  def process(turn : PlayerTurn, coordinate : Coordinate, element : Element) : List[PlayerTurn] = {
    val movementType = element.unit.movementType
    var movs = List[PlayerTurn]()
    for( x <- -1 to 1)  {
      for(y <- -1 to 1) {
        val maybe = Coordinate(coordinate.x + x, coordinate.y + y)
        if( maybe != coordinate ) {
          if(movementType.movementPossible(turn.board, coordinate, maybe)) {
            val action = MovementType.action(turn.board, coordinate, maybe, element.quantity)
            movs ::= turn ~ action
          }
        }
      }
    }

    movs
  }

}
