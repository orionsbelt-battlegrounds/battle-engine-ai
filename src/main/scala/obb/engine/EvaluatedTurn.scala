
package obb.engine

case class EvaluatedTurn(turn : PlayerTurn, value : Float) {

  def valid = turn.valid

  def +(et : EvaluatedTurn) = EvaluatedTurn(turn + et.turn, value + et.value)

}

