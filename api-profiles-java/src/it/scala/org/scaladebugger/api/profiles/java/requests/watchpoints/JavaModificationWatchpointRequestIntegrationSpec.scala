package org.scaladebugger.api.profiles.java.requests.watchpoints

import java.util.concurrent.atomic.AtomicBoolean

import org.scaladebugger.api.utils.JDITools
import org.scaladebugger.api.virtualmachines.DummyScalaVirtualMachine
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import test.{ApiTestUtilities, VirtualMachineFixtures}

class JavaModificationWatchpointRequestIntegrationSpec extends ParallelMockFunSpec
  with VirtualMachineFixtures
  with ApiTestUtilities
{
  describe("JavaModificationWatchpointRequest") {
    it("should be able to detect modification to a field") {
      val testClass = "org.scaladebugger.test.watchpoints.ModificationWatchpoint"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      val className = "org.scaladebugger.test.watchpoints.SomeModificationClass"
      val fieldName = "field"

      val detectedModificationWatchpoint = new AtomicBoolean(false)

      val s = DummyScalaVirtualMachine.newInstance()

      // Listen for modification watchpoint events for specific variable
      s.withProfile(JavaDebugProfile.Name)
        .getOrCreateModificationWatchpointRequest(className, fieldName)
        .filter(_.field.declaringType.name == className)
        .filter(_.field.name == fieldName)
        .foreach(_ => detectedModificationWatchpoint.set(true))

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          assert(detectedModificationWatchpoint.get(), s"$fieldName never modified!")
        })
      }
    }
  }
}
