package org.scaladebugger.api.virtualmachines.jvm

import org.scaladebugger.it.utils.VirtualMachineFixtures
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import org.scaladebugger.test.it.utils.{ApiTestUtilities, VirtualMachineFixtures}

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
