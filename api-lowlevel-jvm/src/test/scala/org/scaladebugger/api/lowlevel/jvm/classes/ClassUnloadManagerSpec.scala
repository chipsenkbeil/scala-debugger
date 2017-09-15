package org.scaladebugger.api.lowlevel.jvm.classes

import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

import scala.util.Success

class ClassUnloadManagerSpec extends ParallelMockFunSpec
{
  private val TestRequestId = java.util.UUID.randomUUID().toString
  private val mockClassUnloadManager = mock[ClassUnloadManager]
  private val testClassUnloadManager = new TestClassUnloadManager(
    mockClassUnloadManager
  ) {
    override protected def newRequestId(): String = TestRequestId
  }

  describe("ClassUnloadManager") {
    describe("#createClassUnloadRequest") {
      it("should invoke createClassUnloadRequestWithId") {
        val expected = Success(TestRequestId)
        val testExtraArguments = Seq(stub[JDIRequestArgument])

        (mockClassUnloadManager.createClassUnloadRequestWithId _)
          .expects(TestRequestId, testExtraArguments)
          .returning(expected).once()

        val actual = testClassUnloadManager.createClassUnloadRequest(
          testExtraArguments: _*
        )

        actual should be (expected)
      }
    }

    describe("#createClassUnloadRequestFromInfo") {
      it("should invoke createClassUnloadRequestWithId") {
        val expected = Success(TestRequestId)
        val testIsPending = false
        val testExtraArguments = Seq(stub[JDIRequestArgument])

        (mockClassUnloadManager.createClassUnloadRequestWithId _)
          .expects(TestRequestId, testExtraArguments)
          .returning(expected).once()

        val info = ClassUnloadRequestInfo(
          TestRequestId,
          testIsPending,
          testExtraArguments
        )
        val actual = testClassUnloadManager.createClassUnloadRequestFromInfo(info)

        actual should be(expected)
      }
    }
  }
}
