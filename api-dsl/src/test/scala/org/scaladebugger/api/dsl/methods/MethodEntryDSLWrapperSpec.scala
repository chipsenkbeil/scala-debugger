package org.scaladebugger.api.dsl.methods

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.utils.ParallelMockFunSpec

import scala.util.Success

class MethodEntryDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockMethodEntryProfile = mock[MethodEntryRequest]

  describe("MethodEntryDSLWrapper") {
    describe("#onMethodEntry") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val methodName = "someMethodName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[MethodEntryEventInfo]))

        (mockMethodEntryProfile.tryGetOrCreateMethodEntryRequest _).expects(
          className,
          methodName,
          extraArguments
        ).returning(returnValue).once()

        mockMethodEntryProfile.onMethodEntry(
          className,
          methodName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeMethodEntry") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val methodName = "someMethodName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[MethodEntryEventInfo])

        (mockMethodEntryProfile.getOrCreateMethodEntryRequest _).expects(
          className,
          methodName,
          extraArguments
        ).returning(returnValue).once()

        mockMethodEntryProfile.onUnsafeMethodEntry(
          className,
          methodName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onMethodEntryWithData") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val methodName = "someMethodName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(MethodEntryEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockMethodEntryProfile.tryGetOrCreateMethodEntryRequestWithData _).expects(
          className,
          methodName,
          extraArguments
        ).returning(returnValue).once()

        mockMethodEntryProfile.onMethodEntryWithData(
          className,
          methodName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeMethodEntryWithData") {
      it("should invoke the underlying profile method") {

        val className = "some.class.name"
        val methodName = "someMethodName"
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(MethodEntryEventInfo, Seq[JDIEventDataResult])]
        )

        (mockMethodEntryProfile.getOrCreateMethodEntryRequestWithData _).expects(
          className,
          methodName,
          extraArguments
        ).returning(returnValue).once()

        mockMethodEntryProfile.onUnsafeMethodEntryWithData(
          className,
          methodName,
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
