package org.scaladebugger.api.profiles.java.requests.vm

import java.util.concurrent.atomic.AtomicBoolean

import org.scaladebugger.api.profiles.java.JavaDebugProfile
import org.scaladebugger.api.virtualmachines.DummyScalaVirtualMachine
import org.scaladebugger.test.helpers.ParallelMockFunSpec
import test.{ApiTestUtilities, VirtualMachineFixtures}

class JavaVMDisconnectRequestIntegrationSpec extends ParallelMockFunSpec
  with VirtualMachineFixtures
  with ApiTestUtilities
{
  describe("JavaVMDisconnectRequest") {
    it("should trigger when a virtual machine disconnects") {
      val testClass = "org.scaladebugger.test.misc.MainUsingApp"

      val detectedDisconnect = new AtomicBoolean(false)

      val s = DummyScalaVirtualMachine.newInstance()

      s.withProfile(JavaDebugProfile.Name)
        .getOrCreateVMDisconnectRequest()
        .foreach(_ => detectedDisconnect.set(true))

      // Start our VM and listen for the disconnect event
      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        // Kill the JVM process so we get a disconnect event
        s.underlyingVirtualMachine.process().destroy()

        // Eventually, we should receive the disconnect event
        logTimeTaken(eventually {
          detectedDisconnect.get() should be (true)
        })
      }
    }
  }
}
