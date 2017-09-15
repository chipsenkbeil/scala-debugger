package org.scaladebugger.api.dsl.watchpoints

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

import scala.util.Success

class AccessWatchpointDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockAccessWatchpointProfile = mock[AccessWatchpointRequest]

  describe("AccessWatchpointDSLWrapper") {
    describe("#onAccessWatchpoint") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val fieldName = "someFieldName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[AccessWatchpointEventInfo]))

        (mockAccessWatchpointProfile.tryGetOrCreateAccessWatchpointRequest _).expects(
          className,
          fieldName,
          extraArguments
        ).returning(returnValue).once()

        mockAccessWatchpointProfile.onAccessWatchpoint(
          className,
          fieldName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeAccessWatchpoint") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val fieldName = "someFieldName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[AccessWatchpointEventInfo])

        (mockAccessWatchpointProfile.getOrCreateAccessWatchpointRequest _).expects(
          className,
          fieldName,
          extraArguments
        ).returning(returnValue).once()

        mockAccessWatchpointProfile.onUnsafeAccessWatchpoint(
          className,
          fieldName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onAccessWatchpointWithData") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val fieldName = "someFieldName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(AccessWatchpointEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockAccessWatchpointProfile.tryGetOrCreateAccessWatchpointRequestWithData _).expects(
          className,
          fieldName,
          extraArguments
        ).returning(returnValue).once()

        mockAccessWatchpointProfile.onAccessWatchpointWithData(
          className,
          fieldName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeAccessWatchpointWithData") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val fieldName = "someFieldName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(AccessWatchpointEventInfo, Seq[JDIEventDataResult])]
        )

        (mockAccessWatchpointProfile.getOrCreateAccessWatchpointRequestWithData _).expects(
          className,
          fieldName,
          extraArguments
        ).returning(returnValue).once()

        mockAccessWatchpointProfile.onUnsafeAccessWatchpointWithData(
          className,
          fieldName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
