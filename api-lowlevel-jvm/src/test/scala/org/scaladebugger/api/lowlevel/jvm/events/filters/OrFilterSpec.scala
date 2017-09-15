package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class OrFilterSpec extends ParallelMockFunSpec
{
  private val orFilter = OrFilter()

  describe("OrFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the or filter") {
        orFilter.toProcessor.argument should be (orFilter)
      }
    }
  }
}
