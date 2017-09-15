//
// API ALL IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiAll: Project = project
  .in(file("api-all"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(name := "api-all")
  .dependsOn(apiProfilesScala210 % "compile->compile;test->compile;it->compile")
  .dependsOn(apiProfilesJava % "compile->compile;test->compile;it->compile")
  .dependsOn(apiProfilesSwappable % "compile->compile;test->compile;it->compile")
  .dependsOn(apiDebuggersJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(apiVirtualmachinesJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(apiDsl % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelJVM % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(apiUtils % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")
  .dependsOn(testItUtils % "test->compile;it->compile")

//
// API SCALA 2.10 IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiProfilesScala210: Project = project
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
  .dependsOn(testCommonUtils % "test->compile;it->compile")
  .dependsOn(testItUtils % "test->compile;it->compile")

//
// API PROFILE JAVA IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiProfilesJava: Project = project
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
  .dependsOn(testCommonUtils % "test->compile;it->compile")
  .dependsOn(testItUtils % "test->compile;it->compile")

//
// API PROFILE SWAPPABLE IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiProfilesSwappable: Project = project
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
  .dependsOn(testCommonUtils % "test->compile;it->compile")
  .dependsOn(testItUtils % "test->compile;it->compile")

//
// API DEBUGGERS JVM PROJECT CONFIGURATION
//
lazy val apiDebuggersJVM: Project = project
  .in(file("api-debuggers-jvm"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiDebuggersJVM.settings: _*)
  .settings(name := "api-debuggers-jvm")
  .dependsOn(apiDebuggersInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")
  .dependsOn(testItUtils % "test->compile;it->compile")

//
// API DSL PROJECT CONFIGURATION
//
lazy val apiDsl: Project = project
  .in(file("api-dsl"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiDsl.settings: _*)
  .settings(name := "api-dsl")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")
  .dependsOn(testItUtils % "test->compile;it->compile")

//
// API VIRTUALMACHINES IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiVirtualmachinesJVM: Project = project
  .in(file("api-virtualmachines-jvm"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiVirtualmachinesJVM.settings: _*)
  .settings(name := "api-virtualmachines-jvm")
  .dependsOn(apiVirtualmachinesInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")
  .dependsOn(testItUtils % "test->compile;it->compile")

//
// API LOWLEVEL IMPLEMENTATION PROJECT CONFIGURATION
//
lazy val apiLowlevelJVM: Project = project
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
  .dependsOn(testCommonUtils % "test->compile;it->compile")
  .dependsOn(testItUtils % "test->compile;it->compile")

//
// API ALL INTERFACES PROJECT CONFIGURATION
//
lazy val apiAllInterfaces: Project = project
  .in(file("api-all-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(name := "api-all-interfaces")
  .dependsOn(apiProfilesInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(apiLowlevelInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(apiDebuggersInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(apiVirtualmachinesInterfaces % "compile->compile;test->compile;it->compile")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")

//
// API PROFILE INTERFACES PROJECT CONFIGURATION
//
lazy val apiProfilesInterfaces: Project = project
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
  .dependsOn(testCommonUtils % "test->compile;it->compile")

//
// API VIRTUALMACHINES INTERFACES PROJECT CONFIGURATION
//
lazy val apiVirtualmachinesInterfaces: Project = project
  .in(file("api-virtualmachines-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiVirtualmachinesInterfaces.settings: _*)
  .settings(name := "api-virtualmachines-interfaces")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")

//
// API DEBUGGERS INTERFACES PROJECT CONFIGURATION
//
lazy val apiDebuggersInterfaces: Project = project
  .in(file("api-debuggers-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiDebuggersInterfaces.settings: _*)
  .settings(name := "api-debuggers-interfaces")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")

//
// API LOWLEVEL INTERFACES PROJECT CONFIGURATION
//
lazy val apiLowlevelInterfaces: Project = project
  .in(file("api-lowlevel-interfaces"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiLowlevelInterfaces.settings: _*)
  .settings(name := "api-lowlevel-interfaces")
  .dependsOn(macros % "compile->compile;test->compile;it->compile")
  .dependsOn(apiPipelines % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")

//
// API UTILS PROJECT CONFIGURATION
//
lazy val apiUtils: Project = project
  .in(file("api-utils"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiUtils.settings: _*)
  .settings(name := "api-utils")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")

//
// API UTILS PROJECT CONFIGURATION
//
lazy val apiPipelines: Project = project
  .in(file("api-pipelines"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ApiPipelines.settings: _*)
  .settings(name := "api-pipelines")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")

//
// EXTERNAL TEST CODE PROJECT CONFIGURATION
//
lazy val testExternal: Project = project
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
// TEST COMMON UTILS PROJECT CONFIGURATION
//
lazy val testCommonUtils: Project = project
  .in(file("test-common-utils"))
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
// TEST INTEGERATION UTILS PROJECT CONFIGURATION
//
lazy val testItUtils: Project = project
  .in(file("test-it-utils"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(ItUtils.settings: _*)
  .settings(
    // Do not publish the test project
    publishArtifact := false,
    publishLocal := {}
  )
  .dependsOn(testCommonUtils)
  .dependsOn(apiAllInterfaces)

//
// DEBUGGER MACRO PROJECT CONFIGURATION
//
lazy val macros: Project = project
  .in(file("macros"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Macros.settings: _*)
  .settings(name := "macros")

//
// DEBUGGER DOC PROJECT CONFIGURATION
//
lazy val docs: Project = project
  .in(file("docs"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Docs.settings: _*)
  .settings(name := "docs")

//
// LANGUAGE PROJECT CONFIGURATION
//
lazy val sidl: Project = project
  .in(file("sidl"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(Sidl.settings: _*)
  .settings(name := "sidl")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")

//
// DEBUGGER TOOL PROJECT CONFIGURATION
//
lazy val cliTool: Project = project
  .in(file("cli-tool"))
  .configs(IntegrationTest)
  .settings(Common.settings: _*)
  .settings(Defaults.itSettings: _*)
  .settings(CliTool.settings: _*)
  .settings(name := "cli-tool")
  .dependsOn(apiAll % "compile->compile;test->compile;it->compile")
  .dependsOn(sidl % "compile->compile;test->compile;it->compile")
  .dependsOn(testExternal % "test->compile;it->compile")
  .dependsOn(testCommonUtils % "test->compile;it->compile")

//
// SBT SCALA DEBUGGER PLUGIN
//
lazy val sbtPlugin: Project = project
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
lazy val root: Project = project
  .in(file("."))
  .settings(Common.settings: _*)
  .settings(
    name := "scala-debugger",
    // Do not publish the aggregation project
    publishArtifact := false,
    publishLocal := {}
  ).aggregate(
    apiAll,
    apiProfilesScala210,
    apiProfilesJava,
    apiProfilesSwappable,
    apiDebuggersJVM,
    apiVirtualmachinesJVM,
    apiDsl,
    apiLowlevelJVM,
    apiAllInterfaces,
    apiProfilesInterfaces,
    apiLowlevelInterfaces,
    apiDebuggersInterfaces,
    apiVirtualmachinesInterfaces,
    apiPipelines,
    apiUtils,
    docs,
    macros,
    sidl,
    cliTool,
    sbtPlugin,
    testExternal,
    testCommonUtils,
    testItUtils
  ).enablePlugins(CrossPerProjectPlugin)
