package org.scaladebugger.api.dsl.monitors

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

import scala.util.Success

class MonitorWaitDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockMonitorWaitProfile = mock[MonitorWaitRequest]

  describe("MonitorWaitDSLWrapper") {
    describe("#onMonitorWait") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[MonitorWaitEventInfo]))

        (mockMonitorWaitProfile.tryGetOrCreateMonitorWaitRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockMonitorWaitProfile.onMonitorWait(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeMonitorWait") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[MonitorWaitEventInfo])

        (mockMonitorWaitProfile.getOrCreateMonitorWaitRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockMonitorWaitProfile.onUnsafeMonitorWait(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onMonitorWaitWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(MonitorWaitEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockMonitorWaitProfile.tryGetOrCreateMonitorWaitRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockMonitorWaitProfile.onMonitorWaitWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeMonitorWaitWithData") {
      it("should invoke the underlying profile method") {

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(MonitorWaitEventInfo, Seq[JDIEventDataResult])]
        )

        (mockMonitorWaitProfile.getOrCreateMonitorWaitRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockMonitorWaitProfile.onUnsafeMonitorWaitWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
