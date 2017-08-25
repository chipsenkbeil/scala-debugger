//
// DEBUGGER API ALL IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiAll = project
  .in(file("api-all"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(name := "api-all")
  .dependsOn(apiProfileScala210 % "compile->compile;test->compile;it->compile")
  .dependsOn(apiProfileJava % "compile->compile;test->compile;it->compile")
  .dependsOn(apiProfileSwappable % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER API SCALA 2.10 IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiProfileScala210 = project
  .in(file("api-profile-scala210"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiProfileScala210.settings: _*)
  .settings(name := "api-profile-scala210")
  .dependsOn(apiProfileJava % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER API PROFILE JAVA IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiProfileJava = project
  .in(file("api-profile-java"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiProfileJava.settings: _*)
  .settings(name := "api-profile-java")
  .dependsOn(apiLowlevelJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER API PROFILE SWAPPABLE IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiProfileSwappable = project
  .in(file("api-profile-swappable"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiProfileSwappable.settings: _*)
  .settings(name := "api-profile-swappable")
  .dependsOn(apiLowlevelJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

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
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER API ALL INTERFACES PROJECT CONFIGURATION
//
lazy val apiAllInterfaces = project
  .in(file("api-all-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(name := "api-all-interfaces")
  .dependsOn(apiProfileInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER API PROFILE INTERFACES PROJECT CONFIGURATION
//
lazy val apiProfileInterfaces = project
  .in(file("api-profile-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiProfileInterfaces.settings: _*)
  .settings(name := "api-profile-interfaces")
  .dependsOn(apiLowlevelInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

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
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

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
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

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
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

//
// DEBUGGER TEST CODE PROJECT CONFIGURATION
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
  .dependsOn(testExternal % "test->compile;it->compile;test->test;it->test")

//
// SBT SCALA DEBUGGER PLUGIN
//
lazy val sbtPlugin = project
  .in(file("sbt-plugin"))
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
    apiAll,
    apiAllInterfaces,
    apiProfileScala210,
    apiProfileJava,
    apiProfileSwappable,
    apiProfileInterfaces,
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

