
package obb.tests

import org.scalatest._
import obb.engine._
import obb.controllers._

class SimulationSpec extends UnitSpec {

  it("simulates a simple game") {
    val board = Board("""
     |           | 1:100:~:N | 1:100:~:N |           |
     |           |           | 2:100:~:N |           |
     |           | 2:100:~:N |           |           |
     |           |           | 2:100:~:N |           |
    """)

    val c1 = new BasicPlayerController(Player.p1)
    val c2 = new BasicPlayerController(Player.p2)

    val simulation = new Simulation(board)
    simulation.play(c1, c2)
    assert(simulation.plays.size != 1)
  }

  it("simulates a small game") {
    val board = Board("""
     | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S |
     | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S | 1:100:~:S |
     |           |           |           |           |           |
     |           |           |           |           |           |
     |           |           |           |           |           |
     | 2:100:~:N | 2:100:~:N | 2:100:~:N | 2:100:~:N | 2:100:~:N |
     | 2:100:^:N | 2:100:^:N | 2:100:^:N | 2:100:^:N | 2:100:~:N |
    """)

    val c1 = new BasicPlayerController(Player.p1)
    val c2 = new BasicPlayerController(Player.p2)

    val simulation = new Simulation(board)
    //simulation.play(c1, c2)
  }
}

