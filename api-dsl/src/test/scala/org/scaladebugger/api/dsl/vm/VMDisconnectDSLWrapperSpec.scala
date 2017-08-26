package org.scaladebugger.api.dsl.vm

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.utils.ParallelMockFunSpec

import scala.util.Success

class VMDisconnectDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockVMDisconnectProfile = mock[VMDisconnectRequest]

  describe("VMDisconnectDSLWrapper") {
    describe("#onVMDisconnect") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[VMDisconnectEventInfo]))

        (mockVMDisconnectProfile.tryGetOrCreateVMDisconnectRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMDisconnectProfile.onVMDisconnect(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeVMDisconnect") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[VMDisconnectEventInfo])

        (mockVMDisconnectProfile.getOrCreateVMDisconnectRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMDisconnectProfile.onUnsafeVMDisconnect(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onVMDisconnectWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(VMDisconnectEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockVMDisconnectProfile.tryGetOrCreateVMDisconnectRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMDisconnectProfile.onVMDisconnectWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeVMDisconnectWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(VMDisconnectEventInfo, Seq[JDIEventDataResult])]
        )

        (mockVMDisconnectProfile.getOrCreateVMDisconnectRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMDisconnectProfile.onUnsafeVMDisconnectWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
