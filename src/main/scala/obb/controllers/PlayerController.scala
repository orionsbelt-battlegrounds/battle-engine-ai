
package obb.controllers

import obb.engine._

trait PlayerController {

  def play(board : Board, player : Player) : PlayerTurn

}
