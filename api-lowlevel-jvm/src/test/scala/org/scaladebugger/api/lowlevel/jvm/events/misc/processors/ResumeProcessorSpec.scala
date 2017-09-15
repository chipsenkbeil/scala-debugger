package org.scaladebugger.api.lowlevel.jvm.events.misc.processors

import com.sun.jdi.event.Event
import org.scaladebugger.api.lowlevel.jvm.events.misc.{NoResume, YesResume}
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class ResumeProcessorSpec extends ParallelMockFunSpec
{
  describe("ResumeProcessor") {
    describe("#process") {
      it("should return false if created from NoResume") {
        val expected = false
        val actual = NoResume.toProcessor.process(mock[Event])
        actual should be (expected)
      }

      it("should return true if created from YesResume") {
        val expected = true
        val actual = YesResume.toProcessor.process(mock[Event])
        actual should be (expected)
      }
    }
  }
}
