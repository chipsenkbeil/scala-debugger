package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class CustomPropertyFilterSpec extends ParallelMockFunSpec
{
  private val testKey = "some key"
  private val testValue = "some value"
  private val customPropertyFilter = CustomPropertyFilter(
    key = testKey,
    value = testValue
  )

  describe("CustomPropertyFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the custom property filter") {
        customPropertyFilter.toProcessor.argument should
          be (customPropertyFilter)
      }
    }
  }
}
