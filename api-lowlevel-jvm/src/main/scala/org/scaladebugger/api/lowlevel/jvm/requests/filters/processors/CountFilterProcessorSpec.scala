package org.scaladebugger.api.lowlevel.jvm.requests.filters.processors

import com.sun.jdi.request._
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}
import org.scaladebugger.api.lowlevel.jvm.requests.filters.CountFilter
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class CountFilterProcessorSpec extends ParallelMockFunSpec
{
  private val testCount = 3
  private val countFilter = CountFilter(count = testCount)
  private val countProcessor = new CountFilterProcessor(countFilter)

  describe("CountFilterProcessor") {
    describe("#process") {
      it("should add the count for all requests") {
        val mockEventRequest = mock[EventRequest]

        (mockEventRequest.addCountFilter _).expects(testCount)

        countProcessor.process(mockEventRequest)
      }
    }
  }
}
