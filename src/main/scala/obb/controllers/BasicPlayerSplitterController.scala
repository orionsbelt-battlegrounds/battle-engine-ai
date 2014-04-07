
package obb.controllers

import obb.engine._
import obb.generators._

class BasicPlayerSplitterController extends PlayerController {

  def play(board : Board, player : Player) : PlayerTurn = {
    val generator = new TurnGenerator(board, player, true)
    generator.run
    generator.best
  }

}
