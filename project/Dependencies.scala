import sbt._


object Versions {

  val akka = "2.6.13"
  val akkaHttp = "10.2.4"
  val scalatest = "3.2.5"
  val slf4j = "1.7.5"

}


object Dependencies {

  val akkaActorTyped = "com.typesafe.akka" %% "akka-actor-typed" % Versions.akka
  val akkaHttp = "com.typesafe.akka" %% "akka-http" % Versions.akkaHttp
  val akkaHttpSprayJson = "com.typesafe.akka" %% "akka-http-spray-json" % Versions.akkaHttp
  val akkaStream = "com.typesafe.akka" %% "akka-stream" % Versions.akka

  val scalactic = "org.scalactic" %% "scalactic" % Versions.scalatest
  val scalatest = "org.scalatest" %% "scalatest" % Versions.scalatest

  val slf4jApi = "org.slf4j" % "slf4j-api" % Versions.slf4j
  val slf4jSimple = "org.slf4j" % "slf4j-simple" % Versions.slf4j

}
