import sbt._


object Versions {

  val scalatest = "3.2.5"
  val postgresql = "42.2.19"
  val playSlick = "5.0.0"
  val slickPg = "0.19.5"
  val playWs = "2.1.3"
  val playJson = "2.9.2"

}


object Dependencies {

  val scalactic = "org.scalactic" %% "scalactic" % Versions.scalatest
  val scalatest = "org.scalatest" %% "scalatest" % Versions.scalatest

  val postgresql = "org.postgresql" % "postgresql" % Versions.postgresql
  val playSlick = "com.typesafe.play" %% "play-slick" % Versions.playSlick
  val playSlickEvolutions = "com.typesafe.play" %% "play-slick-evolutions" % Versions.playSlick
  val slickPg = "com.github.tminglei" %% "slick-pg" % Versions.slickPg
  val slickPgPlayJson = "com.github.tminglei" %% "slick-pg_play-json" % Versions.slickPg

  val playWs = "com.typesafe.play" %% "play-ahc-ws-standalone" % Versions.playWs
  val playWsJson = "com.typesafe.play" %% "play-ws-standalone-json" % Versions.playWs

  val playJson = "com.typesafe.play" %% "play-json" % Versions.playJson

}
