
package obb.generators

import obb.engine._
import obb.engine.actions._

object MovementGenerator {

  def run(turn : PlayerTurn, coordinate : Coordinate, split : Boolean = false) = {
    new MovementGenerator(split).run(turn, coordinate)
  }

}

class MovementGenerator(split: Boolean = false) extends MoveGenerator {

  def process(turn : PlayerTurn, coordinate : Coordinate, element : Element) : List[PlayerTurn] = {
    val movementType = element.unit.movementType
    var movs = List[PlayerTurn]()
    for( x <- -1 to 1)  {
      for(y <- -1 to 1) {
        val maybe = Coordinate(coordinate.x + x, coordinate.y + y)
        if( maybe != coordinate ) {
          if(movementType.movementPossible(turn.board, coordinate, maybe)) {
            eachSplitOption(element) { quantity =>
              val action = MovementType.action(turn.board, coordinate, maybe, quantity)
              val playerTurn = turn ~ action
              if(playerTurn.valid) {
                movs ::= playerTurn
              }
            }
          }
        }
      }
    }

    movs
  }

  def eachSplitOption(element : Element)(f : (Int) => Unit ) {
    f(element.quantity)
    if(split && element.quantity > 1) {
      f(element.quantity / 2)
    }
  }

}
