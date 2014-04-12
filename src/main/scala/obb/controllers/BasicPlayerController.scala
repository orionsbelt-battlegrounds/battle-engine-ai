
package obb.controllers

import obb.engine._
import obb.generators._

class BasicPlayerController(val player : Player) extends PlayerController {

  def play(board : Board) : PlayerTurn = {
    val generator = new TurnGenerator(board, player)
    generator.run
    /*
    println("--")
    println(s"TotalChoices: ${generator.totalChoices}")
    println(s"Processed: ${generator.processedChoices}")
    println(s"Cache: ${generator.cacheHits}")
    */
    generator.bestOption.getOrElse{ PlayerTurn(board) }
  }

}
