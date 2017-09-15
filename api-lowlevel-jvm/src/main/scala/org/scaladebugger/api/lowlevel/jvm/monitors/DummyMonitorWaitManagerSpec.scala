package org.scaladebugger.api.lowlevel.jvm.monitors

import org.scaladebugger.api.lowlevel.jvm.DummyOperationException
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

class DummyMonitorWaitManagerSpec extends ParallelMockFunSpec
{
  private val TestRequestId = java.util.UUID.randomUUID().toString
  private val monitorWaitManager = new DummyMonitorWaitManager

  describe("DummyMonitorWaitManager") {
    describe("#monitorWaitRequestList") {
      it("should return an empty list") {
        monitorWaitManager.monitorWaitRequestList should be (empty)
      }
    }

    describe("#createMonitorWaitRequestWithId") {
      it("should return a failure of dummy operation") {
        val result = monitorWaitManager.createMonitorWaitRequestWithId(
          TestRequestId
        )

        result.isFailure should be (true)
        result.failed.get shouldBe a [DummyOperationException]
      }
    }

    describe("#hasMonitorWaitRequest") {
      it("should return false") {
        val expected = false

        val actual = monitorWaitManager.hasMonitorWaitRequest(
          TestRequestId
        )

        actual should be (expected)
      }
    }

    describe("#getMonitorWaitRequest") {
      it("should return None") {
        val expected = None

        val actual = monitorWaitManager.getMonitorWaitRequest(
          TestRequestId
        )

        actual should be (expected)
      }
    }

    describe("#getMonitorWaitRequestInfo") {
      it("should return None") {
        val expected = None

        val actual = monitorWaitManager.getMonitorWaitRequestInfo(
          TestRequestId
        )

        actual should be (expected)
      }
    }

    describe("#removeMonitorWaitRequest") {
      it("should return false") {
        val expected = false

        val actual = monitorWaitManager.removeMonitorWaitRequest(
          TestRequestId
        )

        actual should be (expected)
      }
    }
  }
}
