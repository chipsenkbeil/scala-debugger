package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.test.utils.ParallelMockFunSpec

class MethodNameFilterSpec extends ParallelMockFunSpec
{
  private val testName = "some name"
  private val methodNameFilter = MethodNameFilter(name = testName)

  describe("MethodNameFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the method name filter") {
        methodNameFilter.toProcessor.argument should be (methodNameFilter)
      }
    }
  }
}
