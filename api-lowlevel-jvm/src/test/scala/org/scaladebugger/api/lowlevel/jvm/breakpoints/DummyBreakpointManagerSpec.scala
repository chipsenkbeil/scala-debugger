package org.scaladebugger.api.lowlevel.jvm.breakpoints

import org.scaladebugger.api.lowlevel.jvm.DummyOperationException
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import org.scaladebugger.test.it.utils.helpers.JDIMockHelpers

class DummyBreakpointManagerSpec extends ParallelMockFunSpec with JDIMockHelpers
{
  private val TestRequestId = java.util.UUID.randomUUID().toString
  private val breakpointManager = new DummyBreakpointManager

  describe("DummyBreakpointManager") {
    describe("#breakpointRequestListById") {
      it("should return an empty list") {
        breakpointManager.breakpointRequestListById should be (empty)
      }
    }

    describe("#breakpointRequestList") {
      it("should return an empty list") {
        breakpointManager.breakpointRequestListById should be (empty)
      }
    }

    describe("#createBreakpointRequestWithId") {
      it("should return a failure of dummy operation") {
        val testFileName = "some/file/name"
        val testLineNumber = 1

        val result = breakpointManager.createBreakpointRequestWithId(
          TestRequestId,
          testFileName,
          testLineNumber
        )

        result.isFailure should be (true)
        result.failed.get shouldBe a [DummyOperationException]
      }
    }

    describe("#hasBreakpointRequestWithId") {
      it("should return false") {
        val expected = false

        val actual = breakpointManager.hasBreakpointRequestWithId(TestRequestId)

        actual should be (expected)
      }
    }

    describe("#hasBreakpointRequest") {
      it("should return false") {
        val expected = false
        val testFileName = "some/file/name"
        val testLineNumber = 1

        val actual = breakpointManager.hasBreakpointRequest(
          testFileName,
          testLineNumber
        )

        actual should be (expected)
      }
    }

    describe("#getBreakpointRequestWithId") {
      it("should return None") {
        val expected = None

        val actual = breakpointManager.getBreakpointRequestWithId(TestRequestId)

        actual should be (expected)
      }
    }

    describe("#getBreakpointRequestInfoWithId") {
      it("should return None") {
        val expected = None

        val actual = breakpointManager.getBreakpointRequestInfoWithId(
          TestRequestId
        )

        actual should be (expected)
      }
    }

    describe("#getBreakpointRequest") {
      it("should return None") {
        val expected = None
        val testFileName = "some/file/name"
        val testLineNumber = 1

        val actual = breakpointManager.getBreakpointRequest(
          testFileName,
          testLineNumber
        )

        actual should be (expected)
      }
    }

    describe("#removeBreakpointRequestWithId") {
      it("should return false") {
        val expected = false

        val actual = breakpointManager.removeBreakpointRequestWithId(
          TestRequestId
        )

        actual should be (expected)
      }
    }

    describe("#removeBreakpointRequest") {
      it("should return false") {
        val expected = false
        val testFileName = "some/file/name"
        val testLineNumber = 1

        val actual = breakpointManager.removeBreakpointRequest(
          testFileName,
          testLineNumber
        )

        actual should be (expected)
      }
    }
  }
}
