package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.api.lowlevel.jvm.events.filters.processors.CustomPropertyFilterProcessor
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class UniqueIdPropertyFilterSpec extends ParallelMockFunSpec
{
  private val testId = java.util.UUID.randomUUID().toString
  private val uniqueIdPropertyFilter = UniqueIdPropertyFilter(id = testId)

  describe("UniqueIdPropertyFilter") {
    describe("constructor") {
      it("should set the custom property filter key to \"_id\"") {
        uniqueIdPropertyFilter.key should be ("_id")
      }

      it("should set the custom property filter value to the id") {
        uniqueIdPropertyFilter.value should be (testId)
      }
    }
    describe("#toProcessor") {
      it("should return a custom property filter processor") {
        uniqueIdPropertyFilter.toProcessor shouldBe
          a [CustomPropertyFilterProcessor]
      }

      it("should return a processor containing the unique id property filter") {
        uniqueIdPropertyFilter.toProcessor.argument should
          be (uniqueIdPropertyFilter)
      }
    }
  }
}
