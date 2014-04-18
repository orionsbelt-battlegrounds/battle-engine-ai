
package obb.generators

import scala.collection.SortedSet
import obb.engine._
import obb.engine.actions._
import obb.evaluators._

class MaxValueTurnGenerator(board: Board, player : Player) extends TurnGenerator {

  case class EvaluatedTurn(turn : PlayerTurn, value : Float)
  val evaluator : BoardEvaluator = new SimpleBoardEvaluator()

  val originalPlayerTurn = PlayerTurn(board)

  def playerElements(turn : PlayerTurn) = turn.board.elementsFor(player)

  def generatePly(turn : PlayerTurn, coord : Coordinate) : List[PlayerTurn] = {
    MovementGenerator.run(turn, coord) ::: AttackGenerator.run(turn, coord)
  }

  def evaluate(turn : PlayerTurn) : EvaluatedTurn = {
    EvaluatedTurn(turn, evaluator.evaluate(turn.board, player))
  }

  def possibleBest(turn : PlayerTurn)(ce : CoordinateElement) : List[EvaluatedTurn] = {
    val ply = generatePly(turn, ce.coordinate) map evaluate
    ply
  }

  def run : Option[PlayerTurn] = {
    val turn = originalPlayerTurn
    val best = playerElements(turn) map possibleBest(turn) flatten
    val bestOption = best.headOption
    if(bestOption.isDefined) {
      Some(bestOption.get.turn)
    } else {
      None
    }
  }

}
