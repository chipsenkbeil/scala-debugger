package org.scaladebugger.api.dsl.classes

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.utils.ParallelMockFunSpec

import scala.util.Success

class ClassPrepareDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockClassPrepareProfile = mock[ClassPrepareRequest]

  describe("ClassPrepareDSLWrapper") {
    describe("#onClassPrepare") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[ClassPrepareEventInfo]))

        (mockClassPrepareProfile.tryGetOrCreateClassPrepareRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockClassPrepareProfile.onClassPrepare(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeClassPrepare") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[ClassPrepareEventInfo])

        (mockClassPrepareProfile.getOrCreateClassPrepareRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockClassPrepareProfile.onUnsafeClassPrepare(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onClassPrepareWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(ClassPrepareEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockClassPrepareProfile.tryGetOrCreateClassPrepareRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockClassPrepareProfile.onClassPrepareWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeClassPrepareWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(ClassPrepareEventInfo, Seq[JDIEventDataResult])]
        )

        (mockClassPrepareProfile.getOrCreateClassPrepareRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockClassPrepareProfile.onUnsafeClassPrepareWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
