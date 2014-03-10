package obb.formatters

import obb.engine._

class AsciiBoardFormatter(board : Board) {

  override def toString = {
    val builder = new StringBuilder
    for(y <- 1 to board.sizeY ) {
      for(x <- 1 to board.sizeX ) {
        builder ++= "| "
      }
      builder ++= "|"
      builder ++= "\n"
    }
    builder.toString.trim
  }

  def ==(raw : String) = {
    val trimmed = raw.trim
    val processed = """ {2,}""".r.replaceAllIn(trimmed, "")
    toString == processed
  }

}
