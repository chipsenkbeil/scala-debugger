package org.scaladebugger.api.profiles.java.info

import com.sun.jdi._
import org.scaladebugger.api.virtualmachines.ScalaVirtualMachine
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

class JavaStringInfoSpec extends ParallelMockFunSpec
{
  private val mockNewTypeProfile = mockFunction[Type, TypeInfo]
  private val mockScalaVirtualMachine = mock[ScalaVirtualMachine]
  private val mockInfoProducerProfile = mock[InfoProducer]
  private val mockVirtualMachine = mock[VirtualMachine]
  private val mockReferenceType = mock[ReferenceType]
  private val mockStringReference = mock[StringReference]
  private val javaStringInfoProfile = new JavaStringInfo(
    mockScalaVirtualMachine, mockInfoProducerProfile, mockStringReference
  )(
    _virtualMachine = mockVirtualMachine,
    _referenceType = mockReferenceType
  ) {
    override protected def newTypeProfile(_type: Type): TypeInfo =
      mockNewTypeProfile(_type)
  }

  describe("JavaStringInfo") {
    describe("#toJavaInfo") {
      it("should return a new instance of the Java profile representation") {
        val expected = mock[StringInfo]

        // Get Java version of info producer
        (mockInfoProducerProfile.toJavaInfo _).expects()
          .returning(mockInfoProducerProfile).once()

        // Create new info profile using Java version of info producer
        // NOTE: Cannot validate second set of args because they are
        //       call-by-name, which ScalaMock does not support presently
        (mockInfoProducerProfile.newStringInfo(
          _: ScalaVirtualMachine,
          _: StringReference
        )(
          _: VirtualMachine,
          _: ReferenceType
        )).expects(
          mockScalaVirtualMachine,
          mockStringReference,
          *, *
        ).returning(expected).once()

        val actual = javaStringInfoProfile.toJavaInfo

        actual should be (expected)
      }
    }

    describe("#isJavaInfo") {
      it("should return true") {
        val expected = true

        val actual = javaStringInfoProfile.isJavaInfo

        actual should be (expected)
      }
    }

    describe("#toJdiInstance") {
      it("should return the JDI instance this profile instance represents") {
        val expected = mockStringReference

        val actual = javaStringInfoProfile.toJdiInstance

        actual should be (expected)
      }
    }
  }
}
