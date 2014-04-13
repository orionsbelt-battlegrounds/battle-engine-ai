
package obb

import obb.engine._
import obb.controllers._

object Main extends App {

  println("OBB::AI::CLI")

  val board = Board("""
   | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S |
   | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S |
   |           |           |           |           |           |           |           |           |
   |           |           |           |           |           |           |           |           |
   |           |           |           |           |           |           |           |           |
   |           |           |           |           |           |           |           |           |
   | 2:100:~:N | 2:100:~:N | 2:100:~:N | 2:100:~:N | 2:100:~:N | 2:100:~:N | 2:100:~:N | 2:100:~:N |
   | 2:100:^:N | 2:100:^:N | 2:100:^:N | 2:100:^:N | 2:100:~:N | 2:100:~:N | 2:100:~:N | 2:100:~:N |
  """)

  //val c1 = new BasicPlayerController(Player.p1)
  val c1 = new CLIController(Player.p1)
  val c2 = new CLIController(Player.p2)
  val simulation = new Simulation(board)
  simulation.play(c1, c2)
}
