package org.scaladebugger.api.dsl.steps

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

import scala.util.Success

class StepDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockStepProfile = mock[StepRequest]

  describe("StepDSLWrapper") {
    describe("#onStep") {
      it("should invoke the underlying profile method") {

        val threadInfoProfile = mock[ThreadInfo]
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[StepEventInfo]))

        (mockStepProfile.tryCreateStepListener _).expects(
          threadInfoProfile,
          extraArguments
        ).returning(returnValue).once()

        mockStepProfile.onStep(
          threadInfoProfile,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeStep") {
      it("should invoke the underlying profile method") {

        val threadInfoProfile = mock[ThreadInfo]
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[StepEventInfo])

        (mockStepProfile.createStepListener _).expects(
          threadInfoProfile,
          extraArguments
        ).returning(returnValue).once()

        mockStepProfile.onUnsafeStep(
          threadInfoProfile,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onStepWithData") {
      it("should invoke the underlying profile method") {

        val threadInfoProfile = mock[ThreadInfo]
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(StepEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockStepProfile.tryCreateStepListenerWithData _).expects(
          threadInfoProfile,
          extraArguments
        ).returning(returnValue).once()

        mockStepProfile.onStepWithData(
          threadInfoProfile,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeStepWithData") {
      it("should invoke the underlying profile method") {

        val threadInfoProfile = mock[ThreadInfo]
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(StepEventInfo, Seq[JDIEventDataResult])]
        )

        (mockStepProfile.createStepListenerWithData _).expects(
          threadInfoProfile,
          extraArguments
        ).returning(returnValue).once()

        mockStepProfile.onUnsafeStepWithData(
          threadInfoProfile,
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
