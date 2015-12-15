package org.senkbeil.debugger.api.lowlevel.watchpoints

import java.util.concurrent.atomic.AtomicBoolean

import com.sun.jdi.event.{ModificationWatchpointEvent, ClassPrepareEvent}
import org.scalatest.concurrent.Eventually
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}
import org.senkbeil.debugger.api.lowlevel.events.EventType._
import org.senkbeil.debugger.api.virtualmachines.DummyScalaVirtualMachine
import test.{TestUtilities, VirtualMachineFixtures}

class StandardModificationWatchpointManagerIntegrationSpec extends FunSpec with Matchers
  with ParallelTestExecution with VirtualMachineFixtures
  with TestUtilities with Eventually
{
  implicit override val patienceConfig = PatienceConfig(
    timeout = scaled(test.Constants.EventuallyTimeout),
    interval = scaled(test.Constants.EventuallyInterval)
  )

  describe("StandardModificationWatchpointManager") {
    it("should be able to detect modification to a field") {
      val testClass = "org.senkbeil.debugger.test.watchpoints.ModificationWatchpoint"
      val testFile = scalaClassStringToFileString(testClass)

      val className = "org.senkbeil.debugger.test.watchpoints.SomeModificationClass"
      val fieldName = "field"

      val detectedModificationWatchpoint = new AtomicBoolean(false)

      val s = DummyScalaVirtualMachine.newInstance()
      import s.lowlevel._

      modificationWatchpointManager.createModificationWatchpointRequest(
        className,
        fieldName
      )

      // Listen for modification watchpoint events for specific variable
      eventManager.addResumingEventHandler(ModificationWatchpointEventType, e => {
        val modificationWatchpointEvent = e.asInstanceOf[ModificationWatchpointEvent]
        val name = modificationWatchpointEvent.field().name()

        // If we detected modification for our variable, mark our flag
        if (name == fieldName) detectedModificationWatchpoint.set(true)
      })

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          assert(detectedModificationWatchpoint.get(), s"$fieldName never modificationed!")
        })
      }
    }
  }
}
