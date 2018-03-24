val Http4sVersion = "0.18.4"
val circeVersion = "0.9.1"
val typedApiVersion = "0.1.0-RC2"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.3",
      version := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    scalacOptions ++= Seq("-Ypartial-unification"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "3.0.3",
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "org.http4s" %% "http4s-blaze-server" % Http4sVersion,
      "org.http4s" %% "http4s-circe" % Http4sVersion,
      "org.http4s" %% "http4s-dsl" % Http4sVersion,
      "ch.qos.logback" % "logback-classic" % "1.1.3",
      "com.github.pheymann" %% "typedapi-client" % typedApiVersion,
      "com.github.pheymann" %% "typedapi-server" % typedApiVersion,
      "com.github.pheymann" %% "typedapi-http4s-client" % typedApiVersion,
      "com.github.pheymann" %% "typedapi-http4s-server" % typedApiVersion)
  )


addCompilerPlugin(
  "org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full
)
