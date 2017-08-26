package org.scaladebugger.api.debuggers.jvm

import java.util.concurrent.atomic.AtomicBoolean

import org.scaladebugger.it.utils.ApiTestUtilities
import org.scaladebugger.test.utils.ParallelMockFunSpec

class LaunchingDebuggerIntegrationSpec extends ParallelMockFunSpec
   with ApiTestUtilities with Logging
{
  describe("LaunchingDebugger") {
    it("should be able to start a JVM and connect to it") {
      val launchedJvmConnected = new AtomicBoolean(false)

      val className = "org.scaladebugger.test.misc.LaunchingMain"
      val classpath = JDITools.jvmClassPath
      val jvmOptions = Seq("-classpath", classpath)
      val launchingDebugger = LaunchingDebugger(
        className = className,
        jvmOptions = jvmOptions,
        suspend = false
      )
      launchingDebugger.start { _ => launchedJvmConnected.set(true) }

      // Keep checking back until the launched JVM has been connected
      eventually {
        launchedJvmConnected.get() should be (true)
      }
    }
  }
}
