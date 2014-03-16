package obb.engine.actions

import obb.engine._

case class ActionArgs( board : Board, from : Coordinate, to : Coordinate, quantity : Int = -1 )
