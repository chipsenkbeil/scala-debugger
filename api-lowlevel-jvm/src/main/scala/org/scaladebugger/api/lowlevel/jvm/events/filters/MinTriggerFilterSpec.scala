package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.test.utils.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

class MinTriggerFilterSpec extends ParallelMockFunSpec
{
  private val testCount = 3
  private val minTriggerFilter = MinTriggerFilter(count = testCount)

  describe("MinTriggerFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the min trigger filter") {
        minTriggerFilter.toProcessor.argument should be (minTriggerFilter)
      }
    }
  }
}