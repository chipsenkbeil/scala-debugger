package org.scaladebugger.api.lowlevel.classes

import java.util.concurrent.atomic.AtomicBoolean

import com.sun.jdi.event.ClassPrepareEvent
import org.scaladebugger.api.lowlevel.events.EventType._
import org.scaladebugger.api.virtualmachines.DummyScalaVirtualMachine
import org.scaladebugger.test.helpers.ParallelMockFunSpec
import test.{ApiTestUtilities, VirtualMachineFixtures}

class StandardClassPrepareManagerIntegrationSpec extends ParallelMockFunSpec
  with VirtualMachineFixtures
  with ApiTestUtilities
{
  describe("StandardClassPrepareManager") {
    it("should trigger when a class is loaded") {
      val testClass = "org.scaladebugger.test.classes.ClassPrepare"

      val expectedClassName = "org.scaladebugger.test.classes.CustomClass"
      val detectedPrepare = new AtomicBoolean(false)

      val s = DummyScalaVirtualMachine.newInstance()

      import s.lowlevel._

      // Mark that we want to receive class prepare events and watch for one
      classPrepareManager.createClassPrepareRequest()
      eventManager.addResumingEventHandler(ClassPrepareEventType, e => {
        val classPrepareEvent = e.asInstanceOf[ClassPrepareEvent]
        val className = classPrepareEvent.referenceType().name()

        logger.debug("New class loaded: " + className)
        if (className == expectedClassName) detectedPrepare.set(true)
      })

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        // Eventually, we should receive the class prepare event
        logTimeTaken(eventually {
          // NOTE: Using asserts to provide more helpful failure messages
          assert(detectedPrepare.get(), s"$expectedClassName was not loaded!")
        })
      }
    }
  }
}
