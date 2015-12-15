package org.senkbeil.debugger.api.lowlevel.watchpoints

import java.util.concurrent.atomic.AtomicBoolean

import com.sun.jdi.event.{ClassPrepareEvent, BreakpointEvent, AccessWatchpointEvent}
import org.scalatest.concurrent.Eventually
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}
import org.senkbeil.debugger.api.lowlevel.events.EventType._
import org.senkbeil.debugger.api.lowlevel.events.filters.MethodNameFilter
import org.senkbeil.debugger.api.virtualmachines.DummyScalaVirtualMachine
import test.{TestUtilities, VirtualMachineFixtures}

class StandardAccessWatchpointManagerIntegrationSpec extends FunSpec with Matchers
  with ParallelTestExecution with VirtualMachineFixtures
  with TestUtilities with Eventually
{
  implicit override val patienceConfig = PatienceConfig(
    timeout = scaled(test.Constants.EventuallyTimeout),
    interval = scaled(test.Constants.EventuallyInterval)
  )

  describe("StandardAccessWatchpointManager") {
    it("should be able to detect access to a field") {
      val testClass = "org.senkbeil.debugger.test.watchpoints.AccessWatchpoint"
      val testFile = scalaClassStringToFileString(testClass)

      val className = "org.senkbeil.debugger.test.watchpoints.SomeAccessClass"
      val fieldName = "field"

      val detectedAccessWatchpoint = new AtomicBoolean(false)

      val s = DummyScalaVirtualMachine.newInstance()
      import s.lowlevel._

      accessWatchpointManager.createAccessWatchpointRequest(
        className,
        fieldName
      )

      // Listen for access watchpoint events for specific variable
      eventManager.addResumingEventHandler(AccessWatchpointEventType, e => {
        val accessWatchpointEvent = e.asInstanceOf[AccessWatchpointEvent]
        val name = accessWatchpointEvent.field().name()

        // If we detected access for our variable, mark our flag
        if (name == fieldName) detectedAccessWatchpoint.set(true)
      })

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          assert(detectedAccessWatchpoint.get(), s"$fieldName never accessed!")
        })
      }
    }
  }
}
