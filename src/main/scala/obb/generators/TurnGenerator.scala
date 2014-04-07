package obb.generators

import scala.collection.SortedSet
import obb.engine._
import obb.engine.actions._
import obb.evaluators._

class TurnGenerator(
  board : Board,
  player : Player,
  evaluator : BoardEvaluator = new SimpleBoardEvaluator()) {

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
      Some(best)
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

  def generatePly(turn : PlayerTurn) : Int = {
    var ply = List[PlayerTurn]()

    turn.board.elementsFor(player) { (coordinate, element) =>
      ply ++= MovementGenerator.run(turn, coordinate)
      ply ++= AttackGenerator.run(turn, coordinate)
    }

    ply.foreach { playerTurn =>
      val cached = options.get(playerTurn.board) 
      if( cached == None || cached.get.totalCost > playerTurn.totalCost  ) {
        pushTurn(playerTurn)
        generatePly(playerTurn)
      }
    }

    ply.size
  }

  def pushTurn(turn : PlayerTurn) {
    options += (turn.board -> turn)
    index += turn
    index = index.take(10)

    //println(s"${eval(turn)} ${turn.historyToString()}")
  }

}
