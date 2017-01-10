import sbt.Keys._
import sbt._
import sbtassembly.AssemblyKeys._

object Visual {
  /** Visual-specific project settings. */
  val settings = Seq(
    // NOTE: Fork needed to avoid mixing in sbt classloader, which is causing
    //       LinkageError to be thrown for JDI-based classes
    fork in Test := true,
    fork in IntegrationTest := true,

    // Force respect (using sbt-doge) of cross scala versions
    scalaVersion := "2.12.1",
    crossScalaVersions := Seq("2.12.1"),

    // Needed for scalafxml
    addCompilerPlugin(
      "org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full
    ),

    libraryDependencies ++= Seq(
      "org.scalafx" %% "scalafx" % "8.0.102-R11",
      "org.scalafx" %% "scalafxml-core-sfx8" % "0.3",
      "org.rogach" %% "scallop" % "2.0.5",
      "org.fxmisc.richtext" % "richtextfx" % "0.7-M3", // BSD 2-clause
      "org.slf4j" % "slf4j-api" % "1.7.5",
      "org.slf4j" % "slf4j-log4j12" % "1.7.5",
      "log4j" % "log4j" % "1.2.17" % "test,it",
      "org.scalatest" %% "scalatest" % "3.0.0" % "test,it",
      "org.scalamock" %% "scalamock-scalatest-support" % "3.4.2" % "test,it",
      "org.testfx" % "testfx-core" % "4.0.5-alpha" % "test,it"
    ),

    // Assembly name following our program name
    assemblyJarName in assembly := "vsdb.jar",

    // Exclude tools.jar (JDI) since not allowed to ship without JDK
    assemblyExcludedJars in assembly := {
      val cp = (fullClasspath in assembly).value
      cp filter {_.data.getName == "tools.jar"}
    }
  )
}
