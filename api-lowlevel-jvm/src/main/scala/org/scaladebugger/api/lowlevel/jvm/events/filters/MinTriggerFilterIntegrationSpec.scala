package org.scaladebugger.api.lowlevel.jvm.events.filters

import com.sun.jdi.event.BreakpointEvent
import org.scaladebugger.api.lowlevel.jvm.events.EventType
import org.scaladebugger.api.lowlevel.jvm.events.EventType._
import org.scaladebugger.api.utils.JDITools
import org.scaladebugger.api.virtualmachines.DummyScalaVirtualMachine
import org.scaladebugger.it.utils.{ApiTestUtilities, VirtualMachineFixtures}
import org.scaladebugger.test.utils.{ParallelMockFunSpec, VirtualMachineFixtures}
import test.VirtualMachineFixtures

class MinTriggerFilterIntegrationSpec extends ParallelMockFunSpec
  with VirtualMachineFixtures
  with ApiTestUtilities
{
  describe("MinTriggerFilter") {
    it("should ignore the first N events for a handler using MinTriggerFilter(N)") {
      val testClass = "org.scaladebugger.test.filters.MinTriggerFilter"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      // The filter to apply (should ignore the first three breakpoints)
      val filter = MinTriggerFilter(count = 3)

      // Mark lines we want to potentially breakpoint
      val breakpointLines = Seq(9, 10, 11, 12, 13)

      // Expected breakpoints to invoke handler
      val expected = Seq(12, 13)

      // Will contain the hit breakpoints
      @volatile var actual = collection.mutable.Seq[Int]()

      val s = DummyScalaVirtualMachine.newInstance()
      import s.lowlevel._

      // Queue up our breakpoints
      breakpointLines.foreach(
        breakpointManager.createBreakpointRequest(testFile, _: Int)
      )

      // Queue up a generic breakpoint event handler that filters events
      eventManager.addResumingEventHandler(BreakpointEventType, e => {
        val breakpointEvent = e.asInstanceOf[BreakpointEvent]
        val location = breakpointEvent.location()
        val fileName = location.sourcePath()
        val lineNumber = location.lineNumber()

        logger.debug(s"Reached breakpoint: $fileName:$lineNumber")
        actual :+= lineNumber
      }, filter)

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          actual should contain theSameElementsInOrderAs (expected)
        })
      }
    }
  }
}
