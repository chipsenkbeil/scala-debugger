package org.scaladebugger.api.lowlevel.jvm.monitors

import org.scaladebugger.api.lowlevel.jvm.DummyOperationException
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

class DummyMonitorContendedEnterManagerSpec extends ParallelMockFunSpec
{
  private val TestRequestId = java.util.UUID.randomUUID().toString
  private val monitorContendedEnterManager = new DummyMonitorContendedEnterManager

  describe("DummyMonitorContendedEnterManager") {
    describe("#monitorContendedEnterRequestList") {
      it("should return an empty list") {
        monitorContendedEnterManager.monitorContendedEnterRequestList should be (empty)
      }
    }

    describe("#createMonitorContendedEnterRequestWithId") {
      it("should return a failure of dummy operation") {
        val result = monitorContendedEnterManager.createMonitorContendedEnterRequestWithId(
          TestRequestId
        )

        result.isFailure should be (true)
        result.failed.get shouldBe a [DummyOperationException]
      }
    }

    describe("#hasMonitorContendedEnterRequest") {
      it("should return false") {
        val expected = false

        val actual = monitorContendedEnterManager.hasMonitorContendedEnterRequest(
          TestRequestId
        )

        actual should be (expected)
      }
    }

    describe("#getMonitorContendedEnterRequest") {
      it("should return None") {
        val expected = None

        val actual = monitorContendedEnterManager.getMonitorContendedEnterRequest(
          TestRequestId
        )

        actual should be (expected)
      }
    }

    describe("#getMonitorContendedEnterRequestInfo") {
      it("should return None") {
        val expected = None

        val actual = monitorContendedEnterManager.getMonitorContendedEnterRequestInfo(
          TestRequestId
        )

        actual should be (expected)
      }
    }

    describe("#removeMonitorContendedEnterRequest") {
      it("should return false") {
        val expected = false

        val actual = monitorContendedEnterManager.removeMonitorContendedEnterRequest(
          TestRequestId
        )

        actual should be (expected)
      }
    }
  }
}
