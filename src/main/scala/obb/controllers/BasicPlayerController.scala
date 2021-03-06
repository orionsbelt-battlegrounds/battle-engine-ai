
package obb.controllers

import obb.engine._
import obb.generators._

class BasicPlayerController(val player : Player) extends PlayerController {

  def play(board : Board) : PlayerTurn = {
    TurnGenerator(board, player).run.getOrElse{ PlayerTurn(board) }
  }

}
