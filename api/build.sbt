import Dependencies._


libraryDependencies ++= Seq(
  "com.google.inject" % "guice" % "5.0.1",
  guice,
  jdbc,
  postgresql,
  playSlick,
  slickPg,
  slickPgPlayJson,
  evolutions,
)
