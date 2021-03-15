import sbt._


object Versions {

  val scalatest = "3.2.5"

}


object Dependencies {

  val scalactic = "org.scalactic" %% "scalactic" % Versions.scalatest
  val scalatest = "org.scalatest" %% "scalatest" % Versions.scalatest

}
