
package obb.controllers

import obb.engine._

trait PlayerController {

  val player : Player
  def play(board : Board) : PlayerTurn

}
