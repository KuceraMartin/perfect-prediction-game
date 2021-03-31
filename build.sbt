name := "perfect-prediction-game"

ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.13.5"


lazy val core = project

lazy val structures = project

lazy val console = project
  .dependsOn(structures)

lazy val web = project
  .dependsOn(core)
  .dependsOn(structures)
  .enablePlugins(PlayScala)
