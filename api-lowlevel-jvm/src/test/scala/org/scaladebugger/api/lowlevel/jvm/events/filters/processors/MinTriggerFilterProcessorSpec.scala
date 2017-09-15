package org.scaladebugger.api.lowlevel.jvm.events.filters.processors

import com.sun.jdi.event.Event
import org.scaladebugger.api.lowlevel.jvm.events.filters.MinTriggerFilter
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class MinTriggerFilterProcessorSpec extends ParallelMockFunSpec
{
  private val testCount = 3
  private val minTriggerFilter = MinTriggerFilter(count = testCount)
  private val minTriggerProcessor =
    new MinTriggerFilterProcessor(minTriggerFilter)

  describe("MinTriggerFilterProcessor") {
    describe("#process") {
      it ("should return false if the total already processed has not reached the min count") {
        // Verify that the processor is false for the first N processes
        for (i <- 1 to testCount) {
          minTriggerProcessor.process(mock[Event]) should be (false)
        }
      }

      it("should return true if the total already processed has reached the min count") {
        for (i <- 1 to testCount) {
          minTriggerProcessor.process(mock[Event])
        }

        // Verify that the processor is true after the first N processes
        minTriggerProcessor.process(mock[Event]) should be (true)
      }
    }

    describe("#reset") {
      it("should reset the internal counter to zero") {
        for (i <- 1 to testCount) {
          minTriggerProcessor.process(mock[Event])
        }

        minTriggerProcessor.reset()

        // Verify that the processor is false even after the first N processes
        minTriggerProcessor.process(mock[Event]) should be (false)
      }
    }
  }
}
