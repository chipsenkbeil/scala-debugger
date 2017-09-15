package org.scaladebugger.api.dsl.threads

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

import scala.util.Success

class ThreadDeathDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockThreadDeathProfile = mock[ThreadDeathRequest]

  describe("ThreadDeathDSLWrapper") {
    describe("#onThreadDeath") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[ThreadDeathEventInfo]))

        (mockThreadDeathProfile.tryGetOrCreateThreadDeathRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockThreadDeathProfile.onThreadDeath(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeThreadDeath") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[ThreadDeathEventInfo])

        (mockThreadDeathProfile.getOrCreateThreadDeathRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockThreadDeathProfile.onUnsafeThreadDeath(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onThreadDeathWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(ThreadDeathEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockThreadDeathProfile.tryGetOrCreateThreadDeathRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockThreadDeathProfile.onThreadDeathWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeThreadDeathWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(ThreadDeathEventInfo, Seq[JDIEventDataResult])]
        )

        (mockThreadDeathProfile.getOrCreateThreadDeathRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockThreadDeathProfile.onUnsafeThreadDeathWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
