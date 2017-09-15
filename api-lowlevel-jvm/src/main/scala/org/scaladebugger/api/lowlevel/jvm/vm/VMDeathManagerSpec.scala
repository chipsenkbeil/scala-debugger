package org.scaladebugger.api.lowlevel.jvm.vm

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import test.TestVMDeathManager

import scala.util.Success

class VMDeathManagerSpec extends ParallelMockFunSpec
{
  private val TestRequestId = java.util.UUID.randomUUID().toString
  private val mockVMDeathManager = mock[VMDeathManager]
  private val testVMDeathManager = new TestVMDeathManager(
    mockVMDeathManager
  ) {
    override protected def newRequestId(): String = TestRequestId
  }

  describe("VMDeathManager") {
    describe("#createVMDeathRequest") {
      it("should invoke createVMDeathRequestWithId") {
        val expected = Success(TestRequestId)
        val testExtraArguments = Seq(stub[JDIRequestArgument])

        (mockVMDeathManager.createVMDeathRequestWithId _)
          .expects(TestRequestId, testExtraArguments)
          .returning(expected).once()

        val actual = testVMDeathManager.createVMDeathRequest(
          testExtraArguments: _*
        )

        actual should be (expected)
      }
    }

    describe("#createVMDeathRequestFromInfo") {
      it("should invoke createVMDeathRequestWithId") {
        val expected = Success(TestRequestId)
        val testIsPending = false
        val testExtraArguments = Seq(stub[JDIRequestArgument])

        (mockVMDeathManager.createVMDeathRequestWithId _)
          .expects(TestRequestId, testExtraArguments)
          .returning(expected).once()

        val info = VMDeathRequestInfo(
          TestRequestId,
          testIsPending,
          testExtraArguments
        )
        val actual = testVMDeathManager.createVMDeathRequestFromInfo(info)

        actual should be(expected)
      }
    }
  }
}
