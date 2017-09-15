package org.scaladebugger.api.profiles.java.info.events

import com.sun.jdi._
import com.sun.jdi.event._
import org.scaladebugger.api.interfaces.lowlevel.JDIArgument
import org.scaladebugger.api.lowlevel.jvm.events.JDIEventArgument
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.api.virtualmachines.ScalaVirtualMachine
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class JavaWatchpointEventInfoSpec extends ParallelMockFunSpec {
  private val mockScalaVirtualMachine = mock[ScalaVirtualMachine]
  private val mockInfoProducer = mock[InfoProducer]
  private val mockWatchpointEvent = mock[WatchpointEvent]

  private val mockJdiRequestArguments = Seq(mock[JDIRequestArgument])
  private val mockJdiEventArguments = Seq(mock[JDIEventArgument])
  private val mockJdiArguments =
    mockJdiRequestArguments ++ mockJdiEventArguments

  private val mockContainerObjectReference = Left(mock[ObjectReference])
  private val mockField = mock[Field]
  private val mockVirtualMachine = mock[VirtualMachine]
  private val mockThreadReference = mock[ThreadReference]
  private val mockThreadReferenceType = mock[ReferenceType]
  private val mockLocation = mock[Location]
  private val testOffsetIndex = -1

  private val javaWatchpointEventInfoProfile = new JavaWatchpointEventInfo(
    scalaVirtualMachine = mockScalaVirtualMachine,
    infoProducer = mockInfoProducer,
    watchpointEvent = mockWatchpointEvent,
    jdiArguments = mockJdiArguments
  )(
    _container = mockContainerObjectReference,
    _field = mockField,
    _virtualMachine = mockVirtualMachine,
    _thread = mockThreadReference,
    _threadReferenceType = mockThreadReferenceType,
    _location = mockLocation
  )

  describe("JavaWatchpointEventInfo") {
    describe("#toJavaInfo") {
      it("should return a new instance of the Java profile representation") {
        val expected = mock[WatchpointEventInfo]

        // Event info producer will be generated in its Java form
        val mockEventInfoProducer = mock[EventInfoProducer]
        (mockInfoProducer.eventProducer _).expects()
          .returning(mockEventInfoProducer).once()
        (mockEventInfoProducer.toJavaInfo _).expects()
          .returning(mockEventInfoProducer).once()

        // Java version of event info producer creates a new event instance
        // NOTE: Cannot validate second set of args because they are
        //       call-by-name, which ScalaMock does not support presently
        (mockEventInfoProducer.newWatchpointEventInfo(
          _: ScalaVirtualMachine,
          _: WatchpointEvent,
          _: Seq[JDIArgument]
        )(
          _: Either[ObjectReference, ReferenceType],
          _: Field,
          _: VirtualMachine,
          _: ThreadReference,
          _: ReferenceType,
          _: Location
        )).expects(
          mockScalaVirtualMachine,
          mockWatchpointEvent,
          mockJdiArguments,
          *, *, *, *, *, *
        ).returning(expected).once()

        val actual = javaWatchpointEventInfoProfile.toJavaInfo

        actual should be (expected)
      }
    }

    describe("#isJavaInfo") {
      it("should return true") {
        val expected = true

        val actual = javaWatchpointEventInfoProfile.isJavaInfo

        actual should be (expected)
      }
    }

    describe("#toJdiInstance") {
      it("should return the JDI instance this profile instance represents") {
        val expected = mockWatchpointEvent

        val actual = javaWatchpointEventInfoProfile.toJdiInstance

        actual should be (expected)
      }
    }

    describe("#field") {
      it("should return a new instance of the field info profile") {
        val expected = mock[FieldVariableInfo]

        // NOTE: Cannot validate second set of args because they are
        //       call-by-name, which ScalaMock does not support presently
        (mockInfoProducer.newFieldInfo(
          _: ScalaVirtualMachine,
          _: Either[ObjectReference, ReferenceType],
          _: Field,
          _: Int
        )(
          _: VirtualMachine
        )).expects(
          mockScalaVirtualMachine,
          mockContainerObjectReference,
          mockField,
          testOffsetIndex,
          *
        ).returning(expected).once()

        val actual = javaWatchpointEventInfoProfile.field

        actual should be (expected)
      }
    }

    describe("#`object`") {
      it("should return Some new instance of the object info profile if underlying event has an object") {
        val expected = Some(mock[ObjectInfo])

        // Event has an object
        val mockObjectReference = mock[ObjectReference]
        (mockWatchpointEvent.`object` _).expects()
          .returning(mockObjectReference).once()

        (mockInfoProducer.newObjectInfo(
          _: ScalaVirtualMachine,
          _: ObjectReference
        )(
          _: VirtualMachine,
          _: ReferenceType
        )).expects(
          mockScalaVirtualMachine,
          mockObjectReference,
          *, *
        ).returning(expected.get).once()

        val actual = javaWatchpointEventInfoProfile.`object`

        actual should be (expected)
      }

      it("should return None if underlying event has no object") {
        val expected = None

        // Event has no object
        (mockWatchpointEvent.`object` _).expects()
          .returning(null).once()

        val actual = javaWatchpointEventInfoProfile.`object`

        actual should be (expected)
      }
    }

    describe("#currentValue") {
      it("should return a new instance of the value info profile") {
        val expected = mock[ValueInfo]

        // Invokes watchpoint event's valueCurrent function
        val mockValue = mock[Value]
        (mockWatchpointEvent.valueCurrent _).expects()
          .returning(mockValue).once()

        (mockInfoProducer.newValueInfo _)
          .expects(mockScalaVirtualMachine, mockValue)
          .returning(expected).once()

        val actual = javaWatchpointEventInfoProfile.currentValue

        actual should be (expected)
      }
    }
  }
}
