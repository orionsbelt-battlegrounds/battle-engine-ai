package obb.generators

import scala.collection.SortedSet
import obb.engine._
import obb.engine.actions._
import obb.evaluators._

object TurnGenerator {

  def apply(board : Board, player : Player) : TurnGenerator = {
    new BruteForceTurnGenerator(board, player)
  }
}

trait TurnGenerator {

  def run : Option[PlayerTurn]

}
