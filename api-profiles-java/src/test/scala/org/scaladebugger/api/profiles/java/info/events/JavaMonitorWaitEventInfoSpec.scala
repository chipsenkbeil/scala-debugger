package org.scaladebugger.api.profiles.java.info.events

import com.sun.jdi._
import com.sun.jdi.event._
import org.scaladebugger.api.interfaces.lowlevel.JDIArgument
import org.scaladebugger.api.lowlevel.jvm.events.JDIEventArgument
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.api.virtualmachines.ScalaVirtualMachine
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class JavaMonitorWaitEventInfoSpec extends ParallelMockFunSpec {
  private val mockScalaVirtualMachine = mock[ScalaVirtualMachine]
  private val mockInfoProducer = mock[InfoProducer]
  private val mockMonitorWaitEvent = mock[MonitorWaitEvent]

  private val mockJdiRequestArguments = Seq(mock[JDIRequestArgument])
  private val mockJdiEventArguments = Seq(mock[JDIEventArgument])
  private val mockJdiArguments =
    mockJdiRequestArguments ++ mockJdiEventArguments

  private val mockMonitorReference = mock[ObjectReference]
  private val mockMonitorReferenceType = mock[ReferenceType]
  private val mockVirtualMachine = mock[VirtualMachine]
  private val mockThreadReference = mock[ThreadReference]
  private val mockThreadReferenceType = mock[ReferenceType]
  private val mockLocation = mock[Location]

  private val javaMonitorWaitEventInfoProfile = new JavaMonitorWaitEventInfo(
    scalaVirtualMachine = mockScalaVirtualMachine,
    infoProducer = mockInfoProducer,
    monitorWaitEvent = mockMonitorWaitEvent,
    jdiArguments = mockJdiArguments
  )(
    _monitor = mockMonitorReference,
    _monitorReferenceType = mockMonitorReferenceType,
    _virtualMachine = mockVirtualMachine,
    _thread = mockThreadReference,
    _threadReferenceType = mockThreadReferenceType,
    _location = mockLocation
  )

  describe("JavaMonitorWaitEventInfo") {
    describe("#toJavaInfo") {
      it("should return a new instance of the Java profile representation") {
        val expected = mock[MonitorWaitEventInfo]

        // Event info producer will be generated in its Java form
        val mockEventInfoProducer = mock[EventInfoProducer]
        (mockInfoProducer.eventProducer _).expects()
          .returning(mockEventInfoProducer).once()
        (mockEventInfoProducer.toJavaInfo _).expects()
          .returning(mockEventInfoProducer).once()

        // Java version of event info producer creates a new event instance
        // NOTE: Cannot validate second set of args because they are
        //       call-by-name, which ScalaMock does not support presently
        (mockEventInfoProducer.newMonitorWaitEventInfo(
          _: ScalaVirtualMachine,
          _: MonitorWaitEvent,
          _: Seq[JDIArgument]
        )(
          _: ObjectReference,
          _: ReferenceType,
          _: VirtualMachine,
          _: ThreadReference,
          _: ReferenceType,
          _: Location
        )).expects(
          mockScalaVirtualMachine,
          mockMonitorWaitEvent,
          mockJdiArguments,
          *, *, *, *, *, *
        ).returning(expected).once()

        val actual = javaMonitorWaitEventInfoProfile.toJavaInfo

        actual should be (expected)
      }
    }

    describe("#isJavaInfo") {
      it("should return true") {
        val expected = true

        val actual = javaMonitorWaitEventInfoProfile.isJavaInfo

        actual should be (expected)
      }
    }

    describe("#toJdiInstance") {
      it("should return the JDI instance this profile instance represents") {
        val expected = mockMonitorWaitEvent

        val actual = javaMonitorWaitEventInfoProfile.toJdiInstance

        actual should be (expected)
      }
    }
  }
}
