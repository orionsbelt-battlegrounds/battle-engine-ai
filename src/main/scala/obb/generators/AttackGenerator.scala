package obb.generators

import obb.engine._

object AttackGenerator {

  def run(turn : PlayerTurn, coordinate : Coordinate) = {
    new AttackGenerator().run(turn, coordinate)
  }

}

class AttackGenerator extends MoveGenerator {

  def process(turn : PlayerTurn, coordinate : Coordinate, element : Element) : List[PlayerTurn] = {
    Nil
  }

}
