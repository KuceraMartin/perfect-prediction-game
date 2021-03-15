name := "perfect-prediction-game"

ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.13.5"


lazy val core = project

lazy val console = project
  .dependsOn(core)

lazy val web = project
  .dependsOn(core)
  .enablePlugins(PlayScala)
