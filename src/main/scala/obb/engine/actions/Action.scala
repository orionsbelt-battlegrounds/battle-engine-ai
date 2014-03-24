
package obb.engine.actions

case class Action( processor : TurnAction, args : ActionArgs ) {

  def run = processor.run(args)

}
