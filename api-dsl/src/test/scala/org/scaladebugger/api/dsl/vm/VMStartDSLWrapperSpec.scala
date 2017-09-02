package org.scaladebugger.api.dsl.vm

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.utils.ParallelMockFunSpec

import scala.util.Success

class VMStartDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockVMStartProfile = mock[VMStartRequest]

  describe("VMStartDSLWrapper") {
    describe("#onVMStart") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[VMStartEventInfo]))

        (mockVMStartProfile.tryGetOrCreateVMStartRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMStartProfile.onVMStart(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeVMStart") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[VMStartEventInfo])

        (mockVMStartProfile.getOrCreateVMStartRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMStartProfile.onUnsafeVMStart(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onVMStartWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(VMStartEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockVMStartProfile.tryGetOrCreateVMStartRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMStartProfile.onVMStartWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeVMStartWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(VMStartEventInfo, Seq[JDIEventDataResult])]
        )

        (mockVMStartProfile.getOrCreateVMStartRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMStartProfile.onUnsafeVMStartWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}