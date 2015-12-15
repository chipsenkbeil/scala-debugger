package org.senkbeil.debugger.api.lowlevel.threads

import java.util.concurrent.atomic.{AtomicBoolean, AtomicInteger}

import com.sun.jdi.event.ThreadStartEvent
import org.scalatest.concurrent.Eventually
import org.scalatest.time.{Milliseconds, Seconds, Span}
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}
import org.senkbeil.debugger.api.lowlevel.events.EventType._
import org.senkbeil.debugger.api.virtualmachines.DummyScalaVirtualMachine
import test.{TestUtilities, VirtualMachineFixtures}

class StandardThreadStartManagerIntegrationSpec extends FunSpec with Matchers
  with ParallelTestExecution with VirtualMachineFixtures
  with TestUtilities with Eventually
{
  implicit override val patienceConfig = PatienceConfig(
    timeout = scaled(test.Constants.EventuallyTimeout),
    interval = scaled(test.Constants.EventuallyInterval)
  )

  describe("StandardThreadStartManager") {
    it("should trigger when a thread starts") {
      val testClass = "org.senkbeil.debugger.test.threads.ThreadStart"
      val testFile = scalaClassStringToFileString(testClass)

      val threadStartCount = new AtomicInteger(0)

      val s = DummyScalaVirtualMachine.newInstance()
      import s.lowlevel._

      // Mark that we want to receive thread start events
      threadStartManager.createThreadStartRequest()
      eventManager.addResumingEventHandler(ThreadStartEventType, e => {
        val threadEvent = e.asInstanceOf[ThreadStartEvent]
        val threadName = threadEvent.thread().name()

        logger.debug(s"Detected start of thread named $threadName")
        if (threadName != "main") threadStartCount.incrementAndGet()
      })

      // Start our Thread and listen for the start event
      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        // Eventually, we should receive a total of 10 thread starts
        logTimeTaken(eventually {
          threadStartCount.get() should be (10)
        })
      }
    }
  }
}
