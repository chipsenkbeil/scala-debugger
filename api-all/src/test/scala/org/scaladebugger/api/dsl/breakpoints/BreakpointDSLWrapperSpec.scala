package org.scaladebugger.api.dsl.breakpoints

import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument
import org.scaladebugger.test.utils.ParallelMockFunSpec

import scala.util.Success

class BreakpointDSLWrapperSpec extends ParallelMockFunSpec
{
  private val mockBreakpointProfile = mock[BreakpointRequest]

  describe("BreakpointDSLWrapper") {
    describe("#onBreakpoint") {
      it("should invoke the underlying profile method") {
        import org.scaladebugger.api.dsl.Implicits.BreakpointDSL

        val fileName = "someFile.scala"
        val lineNumber = 999
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[BreakpointEventInfo]))

        (mockBreakpointProfile.tryGetOrCreateBreakpointRequest _).expects(
          fileName,
          lineNumber,
          extraArguments
        ).returning(returnValue).once()

        mockBreakpointProfile.onBreakpoint(
          fileName,
          lineNumber,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeBreakpoint") {
      it("should invoke the underlying profile method") {
        import org.scaladebugger.api.dsl.Implicits.BreakpointDSL

        val fileName = "someFile.scala"
        val lineNumber = 999
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[BreakpointEventInfo])

        (mockBreakpointProfile.getOrCreateBreakpointRequest _).expects(
          fileName,
          lineNumber,
          extraArguments
        ).returning(returnValue).once()

        mockBreakpointProfile.onUnsafeBreakpoint(
          fileName,
          lineNumber,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onBreakpointWithData") {
      it("should invoke the underlying profile method") {
        import org.scaladebugger.api.dsl.Implicits.BreakpointDSL

        val fileName = "someFile.scala"
        val lineNumber = 999
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(BreakpointEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockBreakpointProfile.tryGetOrCreateBreakpointRequestWithData _).expects(
          fileName,
          lineNumber,
          extraArguments
        ).returning(returnValue).once()

        mockBreakpointProfile.onBreakpointWithData(
          fileName,
          lineNumber,
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeBreakpointWithData") {
      it("should invoke the underlying profile method") {
        import org.scaladebugger.api.dsl.Implicits.BreakpointDSL

        val fileName = "someFile.scala"
        val lineNumber = 999
        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(BreakpointEventInfo, Seq[JDIEventDataResult])]
        )

        (mockBreakpointProfile.getOrCreateBreakpointRequestWithData _).expects(
          fileName,
          lineNumber,
          extraArguments
        ).returning(returnValue).once()

        mockBreakpointProfile.onUnsafeBreakpointWithData(
          fileName,
          lineNumber,
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
