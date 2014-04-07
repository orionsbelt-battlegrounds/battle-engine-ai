
package obb.controllers

import obb.engine._
import obb.generators._

class BasicPlayerController extends PlayerController {

  def play(board : Board, player : Player) : PlayerTurn = {
    val generator = new TurnGenerator(board, player)
    generator.run
    generator.best
  }

}
