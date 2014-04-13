name := "obb.batleEngine"

version := "0.1"

scalaVersion := "2.10.3"

mainClass := Some("obb.Main")

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "2.0" % "test"

ScoverageSbtPlugin.instrumentSettings

CoverallsPlugin.coverallsSettings
