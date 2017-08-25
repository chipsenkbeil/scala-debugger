import sbt.Keys._
import sbt._

object TestUtils {
  /** Test-specific project settings. */
  val settings = Seq(
    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.5",
      "org.slf4j" % "slf4j-log4j12" % "1.7.5",
      "log4j" % "log4j" % "1.2.17",
      "org.scalatest" %% "scalatest" % "3.0.0",
      "org.scalamock" %% "scalamock-scalatest-support" % "3.4.2"
    )
  )
}
