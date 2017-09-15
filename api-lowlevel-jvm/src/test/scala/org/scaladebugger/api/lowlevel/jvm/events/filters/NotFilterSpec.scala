package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class NotFilterSpec extends ParallelMockFunSpec
{
  private val notFilter = NotFilter(mock[JDIEventFilter])

  describe("NotFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the or filter") {
        notFilter.toProcessor.argument should be (notFilter)
      }
    }
  }
}
