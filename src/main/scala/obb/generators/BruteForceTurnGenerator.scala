
package obb.generators

import scala.collection.SortedSet
import obb.engine._
import obb.engine.actions._
import obb.evaluators._

class BruteForceTurnGenerator(
  board : Board,
  player : Player,
  splitMovement : Boolean = false,
  evaluator : BoardEvaluator = new SimpleBoardEvaluator()) extends TurnGenerator {

  var totalChoices = 0
  var cacheHits = 0
  var processedChoices = 0

  def eval(turn : PlayerTurn) : Float = {
    evaluator.evaluate(turn.board, player) + 0.6.toFloat - 0.1.toFloat * turn.totalCost
  }

  val orderByBoardValue = Ordering[Float].on[PlayerTurn](-eval(_))
  var index : SortedSet[PlayerTurn] = SortedSet[PlayerTurn]()(orderByBoardValue)

  def run : Option[PlayerTurn] = {
    val turn = PlayerTurn(board)
    generatePly(turn)
    if(possible == Nil) {
      None
    } else {
      bestOption
    }
  }

  def best = top.head
  def bestOption = top.headOption

  def top : List[PlayerTurn] = {
    index.toList
  }

  def possible : List[PlayerTurn] = {
    options.values.toList
  }

  var options : Map[Board, PlayerTurn] = Map[Board, PlayerTurn]()

  def elementOrder(ce : CoordinateElement) = -ce.element.unit.value

  def playerElements(turn : PlayerTurn) = turn.board.elementsFor(player) sortBy(elementOrder)

  def generatePly(turn : PlayerTurn) : Int = {
    totalChoices += 1

    var ply = List[PlayerTurn]()

    playerElements(turn) foreach { (ce) =>
      ply ++= MovementGenerator.run(turn, ce.coordinate, splitMovement)
      ply ++= AttackGenerator.run(turn, ce.coordinate)
    }

    ply.foreach { playerTurn =>
      val cached = options.get(playerTurn.board)
      if( cached == None || cached.get.totalCost > playerTurn.totalCost  ) {
        pushTurn(playerTurn)
        generatePly(playerTurn)
      } else {
        cacheHits += 1
      }
    }

    ply.size
  }

  def pushTurn(turn : PlayerTurn) {
    options += (turn.board -> turn)
    index += turn
    index = index.take(10)
    processedChoices += 1

    //println(s"${eval(turn)} ${turn.historyToString()}")
  }

}
