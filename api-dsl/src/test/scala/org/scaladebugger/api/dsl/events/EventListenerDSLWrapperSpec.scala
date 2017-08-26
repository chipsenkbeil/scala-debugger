package org.scaladebugger.api.dsl.events

import org.scaladebugger.api.lowlevel.jvm.events.EventType
import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.utils.ParallelMockFunSpec

import scala.util.Success

class EventListenerDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockEventProfile = mock[EventListenerRequest]

  describe("EventListenerDSLWrapper") {
    describe("#onEvent") {
      it("should invoke the underlying profile method") {

        // NOTE: Cannot mock EventType (get stack overflow error)
        val eventType = EventType.BreakpointEventType
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[EventInfo]))

        (mockEventProfile.tryCreateEventListener _).expects(
          eventType,
          extraArguments
        ).returning(returnValue).once()

        mockEventProfile.onEvent(
          eventType,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeEvent") {
      it("should invoke the underlying profile method") {

        // NOTE: Cannot mock EventType (get stack overflow error)
        val eventType = EventType.BreakpointEventType
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[EventInfo])

        (mockEventProfile.createEventListener _).expects(
          eventType,
          extraArguments
        ).returning(returnValue).once()

        mockEventProfile.onUnsafeEvent(
          eventType,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onEventWithData") {
      it("should invoke the underlying profile method") {

        // NOTE: Cannot mock EventType (get stack overflow error)
        val eventType = EventType.BreakpointEventType
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(EventInfo, Seq[JDIEventDataResult])]
        ))

        (mockEventProfile.tryCreateEventListenerWithData _).expects(
          eventType,
          extraArguments
        ).returning(returnValue).once()

        mockEventProfile.onEventWithData(
          eventType,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeEventWithData") {
      it("should invoke the underlying profile method") {

        // NOTE: Cannot mock EventType (get stack overflow error)
        val eventType = EventType.BreakpointEventType
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(EventInfo, Seq[JDIEventDataResult])]
        )

        (mockEventProfile.createEventListenerWithData _).expects(
          eventType,
          extraArguments
        ).returning(returnValue).once()

        mockEventProfile.onUnsafeEventWithData(
          eventType,
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
