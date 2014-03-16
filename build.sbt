name := "obb.batleEngine"

version := "0.1"

scalaVersion := "2.10.3"

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

ScoverageSbtPlugin.instrumentSettings

CoverallsPlugin.coverallsSettings

import com.github.theon.coveralls.CoverallsPlugin.CoverallsKeys._

coverallsToken := "8hDJ3sKLmyxXJ2bzwmxZJ2wZG7DTJFcnF"
