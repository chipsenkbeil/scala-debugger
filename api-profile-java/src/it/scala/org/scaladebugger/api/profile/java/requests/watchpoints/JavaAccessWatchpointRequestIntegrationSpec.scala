package org.scaladebugger.api.profiles.java.requests.watchpoints

import java.util.concurrent.atomic.AtomicBoolean

import org.scaladebugger.api.utils.JDITools
import org.scaladebugger.api.virtualmachines.DummyScalaVirtualMachine
import org.scaladebugger.test.utils.ParallelMockFunSpec
import test.{ApiTestUtilities, VirtualMachineFixtures}

class JavaAccessWatchpointRequestIntegrationSpec extends ParallelMockFunSpec
  with VirtualMachineFixtures
  with ApiTestUtilities
{
  describe("JavaAccessWatchpointRequest") {
    it("should be able to detect access to a field") {
      val testClass = "org.scaladebugger.test.watchpoints.AccessWatchpoint"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      val className = "org.scaladebugger.test.watchpoints.SomeAccessClass"
      val fieldName = "field"

      val detectedAccessWatchpoint = new AtomicBoolean(false)

      val s = DummyScalaVirtualMachine.newInstance()

      // Listen for access watchpoint events for specific variable
      s.withProfile(JavaDebugProfile.Name)
        .getOrCreateAccessWatchpointRequest(className, fieldName)
        .filter(_.field.declaringType.name == className)
        .filter(_.field.name == fieldName)
        .foreach(_ => detectedAccessWatchpoint.set(true))

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          assert(detectedAccessWatchpoint.get(), s"$fieldName never accessed!")
        })
      }
    }
  }
}
