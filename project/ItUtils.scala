import sbt.Keys._
import sbt._

object ItUtils {
  /** Integration test specific-specific project settings. */
  val settings = Seq(
    // NOTE: Fork needed to avoid mixing in sbt classloader, which is causing
    //       LinkageError to be thrown for JDI-based classes
    fork in Test := true,
    fork in IntegrationTest := true,

    libraryDependencies ++= Seq(
      "org.slf4j" % "slf4j-api" % "1.7.5",
      "org.slf4j" % "slf4j-log4j12" % "1.7.5" % "compile,test,it",
      "log4j" % "log4j" % "1.2.17" % "compile,test,it",
      "org.scalatest" %% "scalatest" % "3.0.0" % "compile,test,it",
      "org.scalamock" %% "scalamock-scalatest-support" % "3.4.2" % "compile,test,it"
    )
  )
}
