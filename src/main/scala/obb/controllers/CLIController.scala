
package obb.controllers

import obb.engine._
import obb.engine.actions._
import obb.generators._

class CLIController(val player : Player) extends PlayerController {

  def getTurn(first : Option[PlayerTurn]) : Option[PlayerTurn] = {
    try {
      var turn = first.get
      println(s"Your turn $player")
      val raw = readLine()
      val moves = raw.split(";")
      moves.foreach { rawAction =>
        turn = turn ~ Action.parse(raw, turn)
      }
      if(turn.valid) {
        Some(turn)
      } else {
        println("Invalid turn")
        None
      }
    } catch {
      case e: Exception => {
        println(e)
        None
      }
    }
  }

  def play(board : Board) : PlayerTurn = {
    println(board)
    println("")
    val source = Some(PlayerTurn(board))
    var turn : Option[PlayerTurn] = source
    do {
      turn = getTurn(source)
    } while( turn == None )
    println("Processing...")
    turn.get
  }

}
