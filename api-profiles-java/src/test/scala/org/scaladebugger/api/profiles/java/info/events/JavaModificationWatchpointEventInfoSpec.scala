package org.scaladebugger.api.profiles.java.info.events

import com.sun.jdi._
import com.sun.jdi.event._
import org.scaladebugger.api.interfaces.lowlevel.JDIArgument
import org.scaladebugger.api.lowlevel.jvm.events.JDIEventArgument
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.api.virtualmachines.ScalaVirtualMachine
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class JavaModificationWatchpointEventInfoSpec extends ParallelMockFunSpec {
  private val mockScalaVirtualMachine = mock[ScalaVirtualMachine]
  private val mockInfoProducer = mock[InfoProducer]
  private val mockModificationWatchpointEvent = mock[ModificationWatchpointEvent]

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

  private val javaModificationWatchpointEventInfoProfile = new JavaModificationWatchpointEventInfo(
    scalaVirtualMachine = mockScalaVirtualMachine,
    infoProducer = mockInfoProducer,
    modificationWatchpointEvent = mockModificationWatchpointEvent,
    jdiArguments = mockJdiArguments
  )(
    _container = mockContainerObjectReference,
    _field = mockField,
    _virtualMachine = mockVirtualMachine,
    _thread = mockThreadReference,
    _threadReferenceType = mockThreadReferenceType,
    _location = mockLocation
  )

  describe("JavaModificationWatchpointEventInfo") {
    describe("#toJavaInfo") {
      it("should return a new instance of the Java profile representation") {
        val expected = mock[ModificationWatchpointEventInfo]

        // Event info producer will be generated in its Java form
        val mockEventInfoProducer = mock[EventInfoProducer]
        (mockInfoProducer.eventProducer _).expects()
          .returning(mockEventInfoProducer).once()
        (mockEventInfoProducer.toJavaInfo _).expects()
          .returning(mockEventInfoProducer).once()

        // Java version of event info producer creates a new event instance
        // NOTE: Cannot validate second set of args because they are
        //       call-by-name, which ScalaMock does not support presently
        (mockEventInfoProducer.newModificationWatchpointEventInfo(
          _: ScalaVirtualMachine,
          _: ModificationWatchpointEvent,
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
          mockModificationWatchpointEvent,
          mockJdiArguments,
          *, *, *, *, *, *
        ).returning(expected).once()

        val actual = javaModificationWatchpointEventInfoProfile.toJavaInfo

        actual should be (expected)
      }
    }

    describe("#isJavaInfo") {
      it("should return true") {
        val expected = true

        val actual = javaModificationWatchpointEventInfoProfile.isJavaInfo

        actual should be (expected)
      }
    }

    describe("#toJdiInstance") {
      it("should return the JDI instance this profile instance represents") {
        val expected = mockModificationWatchpointEvent

        val actual = javaModificationWatchpointEventInfoProfile.toJdiInstance

        actual should be (expected)
      }
    }

    describe("#valueToBe") {
      it("should return a new instance of the value info profile") {
        val expected = mock[ValueInfo]

        // Invokes watchpoint event's valueCurrent function
        val mockValue = mock[Value]
        (mockModificationWatchpointEvent.valueToBe _).expects()
          .returning(mockValue).once()

        (mockInfoProducer.newValueInfo _)
          .expects(mockScalaVirtualMachine, mockValue)
          .returning(expected).once()

        val actual = javaModificationWatchpointEventInfoProfile.valueToBe

        actual should be (expected)
      }
    }
  }
}
