
package obb.controllers

import obb.engine._
import obb.generators._

class BasicPlayerSplitterController(val player : Player) extends PlayerController {

  def play(board : Board) : PlayerTurn = {
    val generator = new TurnGenerator(board, player, true)
    generator.run
    generator.bestOption.getOrElse{ PlayerTurn(board) }
  }

}
