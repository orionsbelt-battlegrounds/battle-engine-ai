package obb.generators

import obb.engine._

trait MoveGenerator {

  def run(turn : PlayerTurn, coordinate : Coordinate) : List[PlayerTurn] = {
    if( ! turn.availableCost(1) ) {
      return Nil
    }
    process(turn, coordinate, turn.board.at(coordinate).get)
  }

  def process(turn : PlayerTurn, coordinate : Coordinate, element : Element) : List[PlayerTurn]

}
