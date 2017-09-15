package org.scaladebugger.api.profiles.java.info

import org.scaladebugger.api.interfaces.profiles.info.ThreadInfo
import org.scaladebugger.api.lowlevel.jvm.events.misc.NoResume
import org.scaladebugger.api.profiles.java.JavaDebugProfile
import org.scaladebugger.api.utils.JDITools
import org.scaladebugger.api.virtualmachines.jvm.DummyScalaVirtualMachine
import org.scaladebugger.it.utils.VirtualMachineFixtures
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import org.scaladebugger.test.it.utils.{ApiTestUtilities, VirtualMachineFixtures}
import test.VirtualMachineFixtures

class JavaFrameInfoScala210IntegrationSpec extends ParallelMockFunSpec
  with VirtualMachineFixtures
  with ApiTestUtilities
{
  describe("JavaFrameInfo for 2.10") {
    it("should be able to get variables from a closure") {
      val testClass = "org.scaladebugger.test.info.Variables"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      @volatile var t: Option[ThreadInfo] = None
      val s = DummyScalaVirtualMachine.newInstance()

      // NOTE: Do not resume so we can check the variables at the stack frame
      s.withProfile(JavaDebugProfile.Name)
        .getOrCreateBreakpointRequest(testFile, 41, NoResume)
        .foreach(e => t = Some(e.thread))

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          val variableNames = t.get.topFrame.allVariables.map(_.name)

          // NOTE: As there is no custom logic, this depicts the raw, top-level
          //       variables seen within the closure
          variableNames should contain theSameElementsAs Seq(
            "h$1", "b$1",

            "serialVersionUID"
          )
        })
      }
    }
  }
}
