package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.test.utils.ParallelMockFunSpec

class AndFilterSpec extends ParallelMockFunSpec
{
  private val andFilter = AndFilter()

  describe("AndFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the and filter") {
        andFilter.toProcessor.argument should be (andFilter)
      }
    }
  }
}
