name := """scala-common-nlp-japanese-web"""
organization := "com.ideal.linked"

version := "0.1-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.12"

libraryDependencies += guice
libraryDependencies += "com.ideal.linked" %% "toposoid-knowledgebase-model" % "0.1-SNAPSHOT"
libraryDependencies += "com.ideal.linked" %% "scala-common-nlp-japanese" % "0.1.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "5.0.0" % Test
libraryDependencies += "com.ideal.linked" %% "toposoid-sentence-transformer-neo4j" % "0.1.1" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.ideal.linked.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.ideal.linked.binders._"
