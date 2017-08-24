//
// DEBUGGER API CORE PROJECT CONFIGURATION
//
lazy val scalaDebuggerApiCore = project
  .in(file("scala-debugger-api-core"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiCore.settings: _*)
  .settings(name := "scala-debugger-api-core")
  .dependsOn(scalaDebuggerApiInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(scalaDebuggerApiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(scalaDebuggerApiUtils % "compile->compile;test->compile;it->compile")
  .dependsOn(scalaDebuggerMacros % "compile->compile;test->compile;it->compile")
  .dependsOn(scalaDebuggerTest % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER API INTERFACE PROJECT CONFIGURATION
//
lazy val scalaDebuggerApiInterfaces = project
  .in(file("scala-debugger-api-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiInterfaces.settings: _*)
  .settings(name := "scala-debugger-api-interfaces")
  .dependsOn(scalaDebuggerMacros % "compile->compile;test->compile;it->compile")
  .dependsOn(scalaDebuggerApiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(scalaDebuggerTest % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER API UTILS PROJECT CONFIGURATION
//
lazy val scalaDebuggerApiUtils = project
  .in(file("scala-debugger-api-utils"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiUtils.settings: _*)
  .settings(name := "scala-debugger-api-utils")
  .dependsOn(scalaDebuggerTest % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER API UTILS PROJECT CONFIGURATION
//
lazy val scalaDebuggerApiPipelines = project
  .in(file("scala-debugger-api-pipelines"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiPipelines.settings: _*)
  .settings(name := "scala-debugger-api-pipelines")
  .dependsOn(scalaDebuggerTest % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER TEST CODE PROJECT CONFIGURATION
//
lazy val scalaDebuggerTest = project
  .in(file("scala-debugger-test"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(DebuggerTest.settings: _*)
  .settings(
    // Do not publish the test project
    publishArtifact := false,
    publishLocal := {}
  )

//
// DEBUGGER MACRO PROJECT CONFIGURATION
//
lazy val scalaDebuggerMacros = project
  .in(file("scala-debugger-macros"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Macros.settings: _*)
  .settings(name := "scala-debugger-macros")

//
// DEBUGGER DOC PROJECT CONFIGURATION
//
lazy val scalaDebuggerDocs = project
  .in(file("scala-debugger-docs"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Docs.settings: _*)
  .settings(name := "scala-debugger-docs")

//
// LANGUAGE PROJECT CONFIGURATION
//
lazy val scalaDebuggerLanguage = project
  .in(file("scala-debugger-language"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Language.settings: _*)
  .settings(Macros.settings: _*)
  .settings(name := "scala-debugger-language")
  .dependsOn(scalaDebuggerApiCore % "compile->compile;test->compile;it->compile")

//
// DEBUGGER TOOL PROJECT CONFIGURATION
//
lazy val scalaDebuggerTool = project
  .in(file("scala-debugger-tool"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Tool.settings: _*)
  .settings(name := "scala-debugger-tool")
  .dependsOn(scalaDebuggerApiCore % "compile->compile;test->compile;it->compile")
  .dependsOn(scalaDebuggerLanguage % "compile->compile;test->compile;it->compile")
  .dependsOn(scalaDebuggerTest % "test->compile;it->compile;test->test;it->test")

//
// SBT SCALA DEBUGGER PLUGIN
//
lazy val sbtScalaDebuggerPlugin = project
  .in(file("sbt-scala-debugger-plugin"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(SbtPlugin.settings: _*)
  .settings(name := "sbt-scala-debugger")
  .enablePlugins(CrossPerProjectPlugin)

//
// MAIN PROJECT CONFIGURATION
//
lazy val root = project
  .in(file("."))
  .settings(Common.settings: _*)
  .settings(
    name := "scala-debugger",
    // Do not publish the aggregation project
    publishArtifact := false,
    publishLocal := {}
  ).aggregate(
    scalaDebuggerApiCore,
    scalaDebuggerApiInterfaces,
    scalaDebuggerApiPipelines,
    scalaDebuggerApiUtils,
    scalaDebuggerDocs,
    scalaDebuggerTest,
    scalaDebuggerMacros,
    scalaDebuggerLanguage,
    scalaDebuggerTool,
    sbtScalaDebuggerPlugin
  ).enablePlugins(CrossPerProjectPlugin)

