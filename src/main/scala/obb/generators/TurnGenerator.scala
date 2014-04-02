package obb.generators

import scala.collection.SortedSet
import obb.engine._
import obb.engine.actions._
import obb.evaluators._

class TurnGenerator(
  board : Board,
  player : Player,
  evaluator : BoardEvaluator = new SimpleBoardEvaluator()) {

  val orderByBoardValue = Ordering[Int].on[Board]( board => evaluator.evaluate(board, player) )
  var index : SortedSet[Board] = SortedSet[Board]()(orderByBoardValue)

  def run : Option[PlayerTurn] = {
    val turn = PlayerTurn(board)
    generatePly(turn)
    if(possible == Nil) {
      None
    } else {
      Some(best.head)
    }
  }

  def best : List[PlayerTurn] = {
    index.map( options.get(_).get ).toList
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
      if( options.get(playerTurn.board) == None ) {
        pushTurn(playerTurn)
        generatePly(playerTurn)
      }
    }

    ply.size
  }

  def pushTurn(turn : PlayerTurn) {
    options += (turn.board -> turn)
    index += turn.board
    index = index.take(10)
  }

}
