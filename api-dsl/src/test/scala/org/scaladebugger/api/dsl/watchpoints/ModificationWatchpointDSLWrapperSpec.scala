package org.scaladebugger.api.dsl.watchpoints

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.utils.ParallelMockFunSpec

import scala.util.Success

class ModificationWatchpointDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockModificationWatchpointProfile = mock[ModificationWatchpointRequest]

  describe("ModificationWatchpointDSLWrapper") {
    describe("#onModificationWatchpoint") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val fieldName = "someFieldName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[ModificationWatchpointEventInfo]))

        (mockModificationWatchpointProfile.tryGetOrCreateModificationWatchpointRequest _).expects(
          className,
          fieldName,
          extraArguments
        ).returning(returnValue).once()

        mockModificationWatchpointProfile.onModificationWatchpoint(
          className,
          fieldName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeModificationWatchpoint") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val fieldName = "someFieldName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[ModificationWatchpointEventInfo])

        (mockModificationWatchpointProfile.getOrCreateModificationWatchpointRequest _).expects(
          className,
          fieldName,
          extraArguments
        ).returning(returnValue).once()

        mockModificationWatchpointProfile.onUnsafeModificationWatchpoint(
          className,
          fieldName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onModificationWatchpointWithData") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val fieldName = "someFieldName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(ModificationWatchpointEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockModificationWatchpointProfile.tryGetOrCreateModificationWatchpointRequestWithData _).expects(
          className,
          fieldName,
          extraArguments
        ).returning(returnValue).once()

        mockModificationWatchpointProfile.onModificationWatchpointWithData(
          className,
          fieldName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeModificationWatchpointWithData") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val fieldName = "someFieldName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(ModificationWatchpointEventInfo, Seq[JDIEventDataResult])]
        )

        (mockModificationWatchpointProfile.getOrCreateModificationWatchpointRequestWithData _).expects(
          className,
          fieldName,
          extraArguments
        ).returning(returnValue).once()

        mockModificationWatchpointProfile.onUnsafeModificationWatchpointWithData(
          className,
          fieldName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
