package org.scaladebugger.api.virtualmachines.jvm

import org.scaladebugger.it.utils.{ApiTestUtilities, VirtualMachineFixtures}
import org.scaladebugger.test.utils.{ParallelMockFunSpec, VirtualMachineFixtures}

class StandardScalaVirtualMachineIntegrationSpec extends ParallelMockFunSpec
  with VirtualMachineFixtures
  with ApiTestUtilities
{
  describe("StandardScalaVirtualMachine") {
    it("should indicate that it has started upon receiving the start event") {
      val testClass = "org.scaladebugger.test.misc.MainUsingMethod"

      withVirtualMachine(testClass) { (s) =>
        eventually {
          assert(s.isStarted, "ScalaVirtualMachine not started!")
        }
      }
    }
  }
}
