name := "perfect-prediction-game"

ThisBuild / version := "0.1"
ThisBuild / scalaVersion := "2.12.12"


lazy val commonSettings = Seq(
  assemblyMergeStrategy in assembly := {
    case PathList("module-info.class") => MergeStrategy.discard
    case PathList("play", "reference-overrides.conf") => MergeStrategy.last
    case v => MergeStrategy.defaultMergeStrategy(v)
  }
)


lazy val core = project
  .settings(commonSettings)

lazy val structures = project
  .settings(commonSettings)

lazy val console = project
  .dependsOn(structures)
  .settings(commonSettings)

lazy val api = project
  .dependsOn(core)
  .dependsOn(structures)
  .enablePlugins(PlayScala)
  .settings(commonSettings)

lazy val analysis = project
  .dependsOn(core)
