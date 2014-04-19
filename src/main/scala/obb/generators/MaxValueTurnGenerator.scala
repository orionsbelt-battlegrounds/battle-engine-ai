
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
    val boardValue = evaluator.evaluate(turn.board, player)
    val value = boardValue + 0.6.toFloat - 0.1.toFloat * turn.totalCost
    EvaluatedTurn(turn, value)
  }

  def possibleBest(turn : PlayerTurn)(ce : CoordinateElement) : List[EvaluatedTurn] = {
    val all = possibleBestWorker(turn, ce)

    all.foldLeft(List[EvaluatedTurn]()) { (list, et) =>
      if(list.exists(_.turn.board == et.turn.board || et.turn.board == originalPlayerTurn.board)) {
        list
      } else {
        et :: list
      }
    }
  }

  def possibleBestWorker(turn : PlayerTurn, ce : CoordinateElement) : List[EvaluatedTurn] = {
    val ply = generatePly(turn, ce.coordinate) map evaluate
    ply.foldLeft(ply) { (list, et) =>
      list ::: possibleBestChildren(et)
    }
  }
  def possibleBestChildren(et : EvaluatedTurn) : List[EvaluatedTurn] = {
    val lastAction = et.turn.lastAction

    if(et.turn.valid && lastAction.processor.isInstanceOf[MovementAction]) {
      val coord = lastAction.args.to
      val elementOption = et.turn.board.at(coord)
      if(elementOption.isDefined) {
        val coordElem = CoordinateElement(coord, elementOption.get)
        return possibleBestWorker(et.turn, coordElem)
      }
    }

    Nil
  }

  def run : Option[PlayerTurn] = {
    val turn = originalPlayerTurn
    val possible : List[EvaluatedTurn] = playerElements(turn) map possibleBest(turn) flatten
    val best = possible.sortBy(-_.value)
    val bestOption = best.headOption
    if(bestOption.isDefined) {
      Some(bestOption.get.turn)
    } else {
      None
    }
  }

}
