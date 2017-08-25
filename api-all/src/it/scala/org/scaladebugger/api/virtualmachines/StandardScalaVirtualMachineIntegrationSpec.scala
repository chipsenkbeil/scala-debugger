package org.scaladebugger.api.virtualmachines

import org.scaladebugger.test.utils.{ApiTestUtilities, ParallelMockFunSpec, VirtualMachineFixtures}
import test.VirtualMachineFixtures

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
