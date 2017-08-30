package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.test.utils.ParallelMockFunSpec

class MaxTriggerFilterSpec extends ParallelMockFunSpec
{
  private val testCount = 3
  private val maxTriggerFilter = MaxTriggerFilter(count = testCount)

  describe("MaxTriggerFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the max trigger filter") {
        maxTriggerFilter.toProcessor.argument should be (maxTriggerFilter)
      }
    }
  }
}
