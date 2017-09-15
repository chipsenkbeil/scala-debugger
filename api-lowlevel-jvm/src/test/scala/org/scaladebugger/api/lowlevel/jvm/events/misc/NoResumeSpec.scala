package org.scaladebugger.api.lowlevel.jvm.events.misc

import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class NoResumeSpec extends ParallelMockFunSpec
{
  describe("NoResume") {
    describe("#value") {
      it("should return false") {
        val expected = false
        val actual = NoResume.value
        actual should be (expected)
      }
    }

    describe("#toProcessor") {
      it("should return a processor containing NoResume") {
        NoResume.toProcessor.argument should be (NoResume)
      }
    }
  }
}

