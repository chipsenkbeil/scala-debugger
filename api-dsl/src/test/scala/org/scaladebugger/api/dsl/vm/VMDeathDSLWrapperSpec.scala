package org.scaladebugger.api.dsl.vm

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.utils.ParallelMockFunSpec

import scala.util.Success

class VMDeathDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockVMDeathProfile = mock[VMDeathRequest]

  describe("VMDeathDSLWrapper") {
    describe("#onVMDeath") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[VMDeathEventInfo]))

        (mockVMDeathProfile.tryGetOrCreateVMDeathRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMDeathProfile.onVMDeath(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeVMDeath") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[VMDeathEventInfo])

        (mockVMDeathProfile.getOrCreateVMDeathRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMDeathProfile.onUnsafeVMDeath(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onVMDeathWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(VMDeathEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockVMDeathProfile.tryGetOrCreateVMDeathRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMDeathProfile.onVMDeathWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeVMDeathWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(VMDeathEventInfo, Seq[JDIEventDataResult])]
        )

        (mockVMDeathProfile.getOrCreateVMDeathRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockVMDeathProfile.onUnsafeVMDeathWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}