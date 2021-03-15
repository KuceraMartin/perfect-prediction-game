import Dependencies._


libraryDependencies ++= Seq(

  akkaActorTyped,
  akkaHttp,
  akkaHttpSprayJson,
  akkaStream,

  scalactic,
  scalatest % "test",

  slf4jApi,
  slf4jSimple,

)
