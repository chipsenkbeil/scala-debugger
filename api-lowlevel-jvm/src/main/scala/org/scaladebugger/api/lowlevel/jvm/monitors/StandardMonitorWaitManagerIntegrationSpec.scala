package org.scaladebugger.api.lowlevel.jvm.monitors

import java.util.concurrent.atomic.AtomicBoolean

import com.sun.jdi.event.MonitorWaitEvent
import org.scaladebugger.api.lowlevel.jvm.events.EventType._
import org.scaladebugger.api.virtualmachines.DummyScalaVirtualMachine
import org.scaladebugger.it.utils.VirtualMachineFixtures
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import org.scaladebugger.test.it.utils.{ApiTestUtilities, VirtualMachineFixtures}
import test.VirtualMachineFixtures

class StandardMonitorWaitManagerIntegrationSpec extends ParallelMockFunSpec
  with VirtualMachineFixtures
  with ApiTestUtilities
{
  describe("StandardMonitorWaitManager") {
    it("should trigger when a thread is about to wait on a monitor object") {
      val testClass = "org.scaladebugger.test.monitors.MonitorWait"

      val detectedWait = new AtomicBoolean(false)

      val s = DummyScalaVirtualMachine.newInstance()
      import s.lowlevel._

      // Mark that we want to receive monitor wait events and
      // watch for one
      monitorWaitManager.createMonitorWaitRequest()
      eventManager.addResumingEventHandler(MonitorWaitEventType, e => {
        val monitorWaitEvent =
          e.asInstanceOf[MonitorWaitEvent]

        val threadName = monitorWaitEvent.thread().name()
        val monitorTypeName =
          monitorWaitEvent.monitor().referenceType().name()

        logger.debug(s"Detected wait in thread $threadName for monitor of type $monitorTypeName")
        detectedWait.set(true)
      })

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        // Eventually, we should receive the monitor wait event
        logTimeTaken(eventually {
          // NOTE: Using asserts to provide more helpful failure messages
          assert(detectedWait.get(), s"No monitor wait was detected!")
        })
      }
    }
  }
}
