//
// DEBUGGER API ALL IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiAll = project
  .in(file("api-all"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(name := "api-all")
  .dependsOn(apiProfilesScala210 % "compile->compile;test->compile;it->compile")
  .dependsOn(apiProfilesJava % "compile->compile;test->compile;it->compile")
  .dependsOn(apiProfilesSwappable % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER API SCALA 2.10 IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiProfilesScala210 = project
  .in(file("api-profiles-scala210"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiProfilesScala210.settings: _*)
  .settings(name := "api-profiles-scala210")
  .dependsOn(apiProfilesInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(apiProfilesJava % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER API PROFILE JAVA IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiProfilesJava = project
  .in(file("api-profiles-java"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiProfilesJava.settings: _*)
  .settings(name := "api-profiles-java")
  .dependsOn(apiProfilesInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER API PROFILE SWAPPABLE IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiProfilesSwappable = project
  .in(file("api-profiles-swappable"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiProfilesSwappable.settings: _*)
  .settings(name := "api-profiles-swappable")
  .dependsOn(apiProfilesInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER API LOWLEVEL IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiLowlevelJVM = project
  .in(file("api-lowlevel-jvm"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiLowlevelJVM.settings: _*)
  .settings(name := "api-lowlevel-jvm")
  .dependsOn(apiLowlevelInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER API ALL INTERFACES PROJECT CONFIGURATION
//
lazy val apiAllInterfaces = project
  .in(file("api-all-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(name := "api-all-interfaces")
  .dependsOn(apiProfilesInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER API PROFILE INTERFACES PROJECT CONFIGURATION
//
lazy val apiProfilesInterfaces = project
  .in(file("api-profiles-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiProfilesInterfaces.settings: _*)
  .settings(name := "api-profiles-interfaces")
  .dependsOn(apiLowlevelInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER API LOWLEVEL INTERFACES PROJECT CONFIGURATION
//
lazy val apiLowlevelInterfaces = project
  .in(file("api-lowlevel-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiLowlevelInterfaces.settings: _*)
  .settings(name := "api-lowlevel-interfaces")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER API UTILS PROJECT CONFIGURATION
//
lazy val apiUtils = project
  .in(file("api-utils"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiUtils.settings: _*)
  .settings(name := "api-utils")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER API UTILS PROJECT CONFIGURATION
//
lazy val apiPipelines = project
  .in(file("api-pipelines"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiPipelines.settings: _*)
  .settings(name := "api-pipelines")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// EXTERNAL TEST CODE PROJECT CONFIGURATION
//
lazy val testExternal = project
  .in(file("test-external"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(TestExternal.settings: _*)
  .settings(
    // Do not publish the test project
    publishArtifact := false,
    publishLocal := {}
  )

//
// TEST UTILS PROJECT CONFIGURATION
//
lazy val testUtils = project
  .in(file("test-utils"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(TestUtils.settings: _*)
  .settings(
    // Do not publish the test project
    publishArtifact := false,
    publishLocal := {}
  )

//
// DEBUGGER MACRO PROJECT CONFIGURATION
//
lazy val macros = project
  .in(file("macros"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Macros.settings: _*)
  .settings(name := "macros")

//
// DEBUGGER DOC PROJECT CONFIGURATION
//
lazy val docs = project
  .in(file("docs"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Docs.settings: _*)
  .settings(name := "docs")

//
// LANGUAGE PROJECT CONFIGURATION
//
lazy val sidl = project
  .in(file("sidl"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Sidl.settings: _*)
  .settings(name := "sidl")
  .dependsOn(apiAll % "compile->compile;test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// DEBUGGER TOOL PROJECT CONFIGURATION
//
lazy val cliTool = project
  .in(file("cli-tool"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(CliTool.settings: _*)
  .settings(name := "cli-tool")
  .dependsOn(apiAll % "compile->compile;test->compile;it->compile")
  .dependsOn(sidl % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testUtils % "test->compile;it->compile")

//
// SBT SCALA DEBUGGER PLUGIN
//
lazy val sbtPlugin = project
  .in(file("sbt-plugin"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(SbtPlugin.settings: _*)
  .settings(name := "sbt-debugger")
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
    apiAll,
    apiAllInterfaces,
    apiProfilesScala210,
    apiProfilesJava,
    apiProfilesSwappable,
    apiProfilesInterfaces,
    apiLowlevelJVM,
    apiLowlevelInterfaces,
    apiPipelines,
    apiUtils,
    docs,
    testExternal,
    macros,
    sidl,
    cliTool,
    sbtPlugin
  ).enablePlugins(CrossPerProjectPlugin)

