package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class WildcardPatternFilterSpec extends ParallelMockFunSpec
{
  private val testPattern = "some*pattern"
  private val wildcardPatternFilter = WildcardPatternFilter(
    pattern = testPattern
  )

  describe("WildcardPatternFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the wildcard pattern filter") {
        wildcardPatternFilter.toProcessor.argument should be (wildcardPatternFilter)
      }
    }
  }
}
