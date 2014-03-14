package obb.formatters

import obb.engine._

object AsciiBoardFormatter {

  def apply(raw : String) : Board = {
    var table = Map[Coordinate, Element]()
    var sizeX = 0
    var sizeY = 0
    raw.trim.split('\n').zipWithIndex.foreach { case (line, y) =>
      line.trim.split('|').zipWithIndex.foreach { case(rawElement, x) =>
        if( rawElement.trim.size > 0 && rawElement.trim != "|" ) {
          table += ( Coordinate(x, y+1) -> Element(rawElement) )
        }
        sizeX = x
      }
      sizeY = y+1
    }
    Board(sizeX, sizeY, table)
  }

}

class AsciiBoardFormatter(board : Board) {

  override def toString = {
    val builder = new StringBuilder
    for(y <- 1 to board.sizeY ) {
      for(x <- 1 to board.sizeX ) {
        val elementString = board.at(Coordinate(x, y)) match {
          case Some(element) => element.toString
          case None => ""
        }
        val padding = calculatePadding(elementString)
        builder ++= s"| $elementString$padding "
      }
      builder ++= "|"
      builder ++= "\n"
    }
    builder.toString.trim
  }

  def calculatePadding(raw : String) : String = {
    " " * (maxElementSize - raw.size)
  }

  lazy val maxElementSize : Int = {
    var max = 0
    board.eachElement { element =>
      if(element.toString.length > max) {
        max = element.toString.length
      }
    }
    max
  }

  def ==(raw : String) = {
    val processed = raw.split('\n').map(_.trim).mkString("\n").trim
    toString == processed
  }

}
