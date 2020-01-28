name := "RestAPI_MYSQL_Client"

version := "0.1"

scalaVersion := "2.12.6"

val circeVersion = "0.12.3"
val akkaHttpVersion = "10.1.1"
val akkaVersion = "2.5.11"

libraryDependencies ++= Seq(
      "mysql" % "mysql-connector-java" % "5.1.46",
      "net.liftweb" %% "lift-json" % "3.4.0",
      "org.yaml" % "snakeyaml" % "1.25",
      "com.typesafe.scala-logging" %% "scala-logging" % "3.9.0",
      "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-slf4j" % akkaVersion,
      "ch.qos.logback" % "logback-classic" % "1.0.0" % "runtime",
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "com.typesafe.akka" %% "akka-testkit" % akkaVersion % Test,
      "com.typesafe.akka" %% "akka-stream-testkit" % akkaVersion % Test,
      "com.typesafe.akka" %% "akka-http-caching" % akkaHttpVersion,
      "org.scalatest" %% "scalatest" % "3.0.1" % Test,
      "io.circe" %% "circe-core" % circeVersion,
      "io.circe" %% "circe-generic" % circeVersion,
      "io.circe" %% "circe-parser" % circeVersion
    )
