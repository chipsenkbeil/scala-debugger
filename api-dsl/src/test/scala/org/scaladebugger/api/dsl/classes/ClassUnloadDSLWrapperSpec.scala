package org.scaladebugger.api.dsl.classes

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

import scala.util.Success

class ClassUnloadDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockClassUnloadProfile = mock[ClassUnloadRequest]

  describe("ClassUnloadDSLWrapper") {
    describe("#onClassUnload") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[ClassUnloadEventInfo]))

        (mockClassUnloadProfile.tryGetOrCreateClassUnloadRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockClassUnloadProfile.onClassUnload(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeClassUnload") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[ClassUnloadEventInfo])

        (mockClassUnloadProfile.getOrCreateClassUnloadRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockClassUnloadProfile.onUnsafeClassUnload(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onClassUnloadWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(ClassUnloadEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockClassUnloadProfile.tryGetOrCreateClassUnloadRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockClassUnloadProfile.onClassUnloadWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeClassUnloadWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(ClassUnloadEventInfo, Seq[JDIEventDataResult])]
        )

        (mockClassUnloadProfile.getOrCreateClassUnloadRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockClassUnloadProfile.onUnsafeClassUnloadWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
