
package obb.engine.actions

import obb.engine._

object Attack {

  def run( board : Board, attacker : Coordinate, target : Coordinate) = {
    new Attack().run(new ActionArgs(board, attacker, target))
  }

}

class Attack extends TurnAction {

  def process(args : ActionArgs, element : Element) : ActionResult = {
    args.board.at(args.to) match {
      case Some(target) => 
        attackRestrictions(args, element, target).getOrElse {
          processAttack(args, element, target)
        }
      case _ => ActionResult(true, args.board, 0)
    }
  }

  def attackRestrictions(args: ActionArgs, attacker : Element, target : Element) : Option[ActionResult] = {
    for( i <- 1 to attacker.unit.range ) {
      val x = args.from.x + i * attacker.direction.x
      val y = args.from.y + i * attacker.direction.y
      val currentCoordinate = Coordinate(x, y)
      val currentElement = args.board.at(currentCoordinate)

      if( currentElement != None ) {
        if( currentCoordinate == args.to) {
          return None
        } else {
          return Some(ActionResult(false, args.board, 0, Some(s"InvalidAttack:Obstacle")))
        }
      }
    }

    Some(ActionResult(false, args.board, 0, Some(s"InvalidAttack:NoTargetReached")))
  }

  def processAttack(args: ActionArgs, attacker : Element, target : Element) : ActionResult = {
    var table = args.board.table
    val totalDamage = getTotalDamage(args, attacker, target)
    val defensePower = target.unit.defense * target.quantity
    val distancePenalty = getDistancePenalty(args.from, args.to)

    val destroyed = resolveAttack(totalDamage, defensePower, target)
    val remaining = target.quantity - destroyed

    if( remaining <= 0) {
      table -= args.to
    } else if(remaining < target.quantity) {
      table += ( args.to -> target.forQuantity(remaining) )
    }

    ActionResult(true, Board(args.board.sizeX, args.board.sizeY, table), 1)
  }

  def resolveAttack(totalDamage : Int, defense : Int, target : Element) = {
    totalDamage / defense
  }

  def getDistancePenalty(from : Coordinate, to : Coordinate) : Int = {
    val distance = from ~ to
    0
  }

  def getTotalDamage(args: ActionArgs, attacker : Element, target : Element) : Int = {
    attacker.unit.attack * attacker.quantity
  }

}
