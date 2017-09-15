package org.scaladebugger.api.profiles.java.info

import com.sun.jdi._
import org.scaladebugger.api.interfaces.lowlevel.{InvokeNonVirtualArgument, InvokeSingleThreadedArgument}
import org.scaladebugger.api.lowlevel.{InvokeSingleThreadedArgument, JDIArgument}
import org.scaladebugger.api.virtualmachines.ScalaVirtualMachine
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

class JavaObjectInfoSpec extends ParallelMockFunSpec
{
  private val mockNewFieldProfile = mockFunction[Field, Int, FieldVariableInfo]
  private val mockNewMethodProfile = mockFunction[Method, MethodInfo]
  private val mockNewValueProfile = mockFunction[Value, ValueInfo]
  private val mockNewTypeProfile = mockFunction[Type, TypeInfo]
  private val mockNewTypeCheckerProfile = mockFunction[TypeChecker]
  private val mockScalaVirtualMachine = mock[ScalaVirtualMachine]
  private val mockInfoProducerProfile = mock[InfoProducer]
  private val mockVirtualMachine = mock[VirtualMachine]
  private val mockReferenceType = mock[ReferenceType]
  private val mockObjectReference = mock[ObjectReference]
  private val javaObjectInfoProfile = new JavaObjectInfo(
    mockScalaVirtualMachine,
    mockInfoProducerProfile,
    mockObjectReference
  )(
    _virtualMachine = mockVirtualMachine,
    _referenceType = mockReferenceType
  ) {
    override protected def newFieldProfile(field: Field, offsetIndex: Int): FieldVariableInfo =
      mockNewFieldProfile(field, offsetIndex)
    override protected def newMethodProfile(method: Method): MethodInfo =
      mockNewMethodProfile(method)
    override protected def newValueProfile(value: Value): ValueInfo =
      mockNewValueProfile(value)
    override protected def newTypeCheckerProfile(): TypeChecker =
      mockNewTypeCheckerProfile()
    override protected def newTypeProfile(_type: Type): TypeInfo =
      mockNewTypeProfile(_type)
  }

  describe("JavaObjectInfo") {
    describe("#toJavaInfo") {
      it("should return a new instance of the Java profile representation") {
        val expected = mock[ObjectInfo]
        val mockF = mockFunction[VirtualMachine, ReferenceType, ObjectInfo]

        // Get Java version of info producer
        (mockInfoProducerProfile.toJavaInfo _).expects()
          .returning(mockInfoProducerProfile).once()

        // Create new info profile using Java version of info producer
        // NOTE: Cannot validate second set of args because they are
        //       call-by-name, which ScalaMock does not support presently
        (mockInfoProducerProfile.newObjectInfo(
          _: ScalaVirtualMachine,
          _: ObjectReference
        )(
          _: VirtualMachine,
          _: ReferenceType
        )).expects(
          mockScalaVirtualMachine,
          mockObjectReference,
          *, *
        ).returning(expected).once()

        val actual = javaObjectInfoProfile.toJavaInfo

        actual should be (expected)
      }
    }

    describe("#isJavaInfo") {
      it("should return true") {
        val expected = true

        val actual = javaObjectInfoProfile.isJavaInfo

        actual should be (expected)
      }
    }

    describe("#toJdiInstance") {
      it("should return the JDI instance this profile instance represents") {
        val expected = mockObjectReference

        val actual = javaObjectInfoProfile.toJdiInstance

        actual should be (expected)
      }
    }

    describe("#typeInfo") {
      it("should should return a new reference type profile wrapping the type") {
        val expected = mock[ReferenceTypeInfo]

        val mockType = mock[Type]
        (mockObjectReference.`type` _).expects()
          .returning(mockType).once()

        val mockTypeInfoProfile = mock[TypeInfo]
        mockNewTypeProfile.expects(mockType)
          .returning(mockTypeInfoProfile).once()

        (mockTypeInfoProfile.toReferenceType _).expects()
          .returning(expected).once()

        val actual = javaObjectInfoProfile.`type`

        actual should be (expected)
      }
    }

    describe("#uniqueId") {
      it("should return the unique id of the object") {
        val expected = 12345L

        (mockObjectReference.uniqueID _).expects().returning(expected).once()

        val actual = javaObjectInfoProfile.uniqueId

        actual should be (expected)
      }
    }

    describe("#referenceType") {
      it("should return a profile wrapping the object's reference type") {
        val expected = mock[ReferenceTypeInfo]
        val mockReferenceType = mock[ReferenceType]

        (mockObjectReference.referenceType _).expects()
          .returning(mockReferenceType).once()

        val mockTypeInfoProfile = mock[TypeInfo]
        mockNewTypeProfile.expects(mockReferenceType)
          .returning(mockTypeInfoProfile).once()

        (mockTypeInfoProfile.toReferenceType _).expects()
          .returning(expected).once()

        val actual = javaObjectInfoProfile.referenceType

        actual should be (expected)
      }
    }


    describe("#invoke(thread profile, method profile, arguments, JDI arguments)") {
      it("should invoke using the provided thread and method, returning wrapper profile of value") {
        val expected = mock[ValueInfo]

        val mockThreadReference = mock[ThreadReference]
        val mockThreadInfoProfile = mock[ThreadInfo]
        (mockThreadInfoProfile.toJdiInstance _).expects()
          .returning(mockThreadReference).once()

        val mockMethod = mock[Method]
        val javaMethodInfoProfile = new JavaMethodInfo(
          mockScalaVirtualMachine,
          mockInfoProducerProfile,
          mockMethod
        )

        // Object method is invoked
        val mockValue = mock[Value]
        import scala.collection.JavaConverters._
        (mockObjectReference.invokeMethod _).expects(
          mockThreadReference,
          mockMethod,
          Seq[Value]().asJava,
          0
        ).returning(mockValue).once()

        // Profile is created for return value
        mockNewValueProfile.expects(mockValue).returning(expected).once()

        val actual = javaObjectInfoProfile.invoke(
          mockThreadInfoProfile,
          javaMethodInfoProfile,
          Nil
        )

        actual should be (expected)
      }

      it("should invoke using the provided arguments") {
        val arguments = Seq(1)

        val mockThreadReference = mock[ThreadReference]
        val mockThreadInfoProfile = mock[ThreadInfo]
        (mockThreadInfoProfile.toJdiInstance _).expects()
          .returning(mockThreadReference).once()

        val mockMethod = mock[Method]
        val javaMethodInfoProfile = new JavaMethodInfo(
          mockScalaVirtualMachine,
          mockInfoProducerProfile,
          mockMethod
        )

        // Arguments are mirrored remotely
        val mockValues = Seq(mock[IntegerValue])
        arguments.zip(mockValues).foreach { case (ar, ma) =>
          (mockVirtualMachine.mirrorOf(_: Int)).expects(ar)
            .returning(ma).once()
        }

        // Object method is invoked
        val mockValue = mock[Value]
        import scala.collection.JavaConverters._
        (mockObjectReference.invokeMethod _).expects(
          *,
          *,
          mockValues.asJava,
          *
        ).returning(mockValue).once()

        // Profile is created for return value
        mockNewValueProfile.expects(*).once()

        javaObjectInfoProfile.invoke(
          mockThreadInfoProfile,
          javaMethodInfoProfile,
          arguments
        )
      }

      it("should provide relevant JDI options as an OR'd value") {
        val jdiArguments = Seq(
          InvokeNonVirtualArgument,
          InvokeSingleThreadedArgument
        )

        val mockThreadReference = mock[ThreadReference]
        val mockThreadInfoProfile = mock[ThreadInfo]
        (mockThreadInfoProfile.toJdiInstance _).expects()
          .returning(mockThreadReference).once()

        val mockMethod = mock[Method]
        val javaMethodInfoProfile = new JavaMethodInfo(
          mockScalaVirtualMachine,
          mockInfoProducerProfile,
          mockMethod
        )

        // Object method is invoked
        // NOTE: Both arguments OR'd together is 3 (1 | 2)
        (mockObjectReference.invokeMethod _).expects(*, *, *, 3)
          .returning(mock[Value]).once()

        // Profile is created for return value
        mockNewValueProfile.expects(*).returning(null).once()

        javaObjectInfoProfile.invoke(
          mockThreadInfoProfile,
          javaMethodInfoProfile,
          Nil,
          jdiArguments: _*
        )
      }
    }

    describe("#methods") {
      it("should return a collection of profiles wrapping the object's visible methods") {
        val expected = Seq(mock[MethodInfo])

        // Lookup the visible methods
        import scala.collection.JavaConverters._
        val mockMethods = Seq(mock[Method])
        (mockReferenceType.visibleMethods _).expects()
          .returning(mockMethods.asJava).once()

        // Create the new profiles for the methods
        mockMethods.zip(expected).foreach { case (m, e) =>
          mockNewMethodProfile.expects(m).returning(e).once()
        }

        val actual = javaObjectInfoProfile.methods

        actual should be (expected)
      }
    }

    describe("#methodOption") {
      it("should return None if no method with matching name is found") {
        val expected = None

        val name = "someName"
        val paramTypes = Seq("some.type")

        // Lookup the method and return empty list indicating no method found
        import scala.collection.JavaConverters._
        (mockReferenceType.methodsByName(_: String)).expects(name)
          .returning(Seq[Method]().asJava).once()

        val actual = javaObjectInfoProfile.methodOption(name, paramTypes: _*)

        actual should be (expected)
      }

      it("should return None if no method with matching parameters is found") {
        val expected = None

        val name = "someName"
        val paramTypes = Seq("some.type")

        // Lookup the method and return method indicating matching name found
        val mockMethod = mock[Method]
        import scala.collection.JavaConverters._
        (mockReferenceType.methodsByName(_: String)).expects(name)
          .returning(Seq(mockMethod).asJava).once()

        (mockMethod.argumentTypeNames _).expects()
          .returning(paramTypes.map(_ + "other").asJava).once()

        // Arguments do not match, so return false
        val mockTypeCheckerProfile = mock[TypeChecker]
        mockNewTypeCheckerProfile.expects()
          .returning(mockTypeCheckerProfile).once()
        (mockTypeCheckerProfile.equalTypeNames _).expects(*, *)
          .returning(false).once()

        val actual = javaObjectInfoProfile.methodOption(name, paramTypes: _*)

        actual should be (expected)
      }

      it("should return Some profile wrapping the associated method if found") {
        val expected = Some(mock[MethodInfo])

        val name = "someName"
        val paramTypes = Seq("some.type")

        // Lookup the method and return method indicating matching name found
        val mockMethod = mock[Method]
        import scala.collection.JavaConverters._
        (mockReferenceType.methodsByName(_: String)).expects(name)
          .returning(Seq(mockMethod).asJava).once()

        (mockMethod.argumentTypeNames _).expects()
          .returning(paramTypes.asJava).once()

        // Arguments do match, so return true
        val mockTypeCheckerProfile = mock[TypeChecker]
        mockNewTypeCheckerProfile.expects()
          .returning(mockTypeCheckerProfile).once()
        (mockTypeCheckerProfile.equalTypeNames _).expects(*, *)
          .returning(true).once()

        // New method profile created
        mockNewMethodProfile.expects(mockMethod).returning(expected.get).once()

        val actual = javaObjectInfoProfile.methodOption(name, paramTypes: _*)

        actual should be (expected)
      }
    }

    describe("#fields") {
      it("should return a collection of profiles wrapping the object's visible fields") {
        val expected = Seq(mock[FieldVariableInfo])

        // Lookup the visible fields
        import scala.collection.JavaConverters._
        val mockFields = Seq(mock[Field])
        (mockReferenceType.visibleFields _).expects()
          .returning(mockFields.asJava).once()

        // Create the new profiles for the fields
        mockFields.zip(expected).foreach { case (f, e) =>
          mockNewFieldProfile.expects(f, -1).returning(e).once()
        }

        val actual = javaObjectInfoProfile.fields

        actual should be (expected)
      }
    }

    describe("#fieldOption") {
      it("should return None if no field with matching name is found") {
        val expected = None

        val name = "someName"

        // Lookup the field and return null indicating no field found
        (mockReferenceType.fieldByName _).expects(name)
          .returning(null).once()

        val actual = javaObjectInfoProfile.fieldOption(name)

        actual should be (expected)
      }

      it("should return Some profile wrapping the associated field if found") {
        val expected = Some(mock[FieldVariableInfo])
        val name = "someName"

        // Lookup the field
        val mockField = mock[Field]
        (mockReferenceType.fieldByName _).expects(name)
          .returning(mockField).once()

        // Create the new profile
        mockNewFieldProfile.expects(mockField, -1).returning(expected.get).once()

        val actual = javaObjectInfoProfile.fieldOption(name)

        actual should be(expected)
      }
    }

    describe("#indexedFields") {
      it("should return a collection of profiles wrapping the object's visible fields") {
        val expected = Seq(mock[FieldVariableInfo])

        // Lookup the visible fields
        import scala.collection.JavaConverters._
        val mockFields = Seq(mock[Field])
        (mockReferenceType.visibleFields _).expects()
          .returning(mockFields.asJava).once()

        // Create the new profiles for the fields
        mockFields.zip(expected).zipWithIndex.foreach { case ((f, e), i) =>
          mockNewFieldProfile.expects(f, i).returning(e).once()
        }

        val actual = javaObjectInfoProfile.indexedFields

        actual should be (expected)
      }
    }

    describe("#indexedField") {
      it("should return None if no field with matching name is found") {
        val expected = None

        val name = "someName"

        // Lookup the visible fields (Nil indicates none)
        import scala.collection.JavaConverters._
        (mockReferenceType.visibleFields _).expects()
          .returning(Seq[Field]().asJava).once()

        val actual = javaObjectInfoProfile.indexedFieldOption(name)

        actual should be (expected)
      }

      it("should return a profile wrapping the associated field if found") {
        val expected = Some(mock[FieldVariableInfo])
        val name = "someName"

        // Lookup the visible fields
        val mockField = mock[Field]
        (expected.get.name _).expects().returning(name).once()

        import scala.collection.JavaConverters._
        (mockReferenceType.visibleFields _).expects()
          .returning(Seq(mockField).asJava).once()

        // Create the new profile
        mockNewFieldProfile.expects(mockField, 0).returning(expected.get).once()

        val actual = javaObjectInfoProfile.indexedFieldOption(name)

        actual should be (expected)
      }
    }
  }
}
