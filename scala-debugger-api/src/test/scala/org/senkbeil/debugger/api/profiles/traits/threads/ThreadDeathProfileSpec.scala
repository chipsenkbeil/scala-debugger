package org.senkbeil.debugger.api.profiles.traits.threads

import com.sun.jdi.event.ThreadDeathEvent
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, OneInstancePerTest}
import org.senkbeil.debugger.api.lowlevel.JDIArgument
import org.senkbeil.debugger.api.lowlevel.events.data.JDIEventDataResult
import org.senkbeil.debugger.api.pipelines.Pipeline

class ThreadDeathProfileSpec extends FunSpec with Matchers with OneInstancePerTest
  with MockFactory
{
  describe("ThreadDeathProfile") {
    describe("#onThreadDeath") {
      it("should return a pipeline with the event data results filtered out") {
        val expected = mock[ThreadDeathEvent]

        // Data to be run through pipeline
        val data = (expected, Seq(mock[JDIEventDataResult]))

        // Pipeline that is parent to the one that just streams the event
        val pipelineWithData = Pipeline.newPipeline(
          classOf[ThreadDeathProfile#ThreadDeathEventAndData]
        )

        val threadDeathProfile = new Object with ThreadDeathProfile {
          override def onThreadDeathWithData(
            extraArguments: JDIArgument*
          ): Pipeline[ThreadDeathEventAndData, ThreadDeathEventAndData] = {
            pipelineWithData
          }
        }

        var actual: ThreadDeathEvent = null
        threadDeathProfile.onThreadDeath().foreach(actual = _)

        // Funnel the data through the parent pipeline that contains data to
        // demonstrate that the pipeline with just the event is merely a
        // mapping on top of the pipeline containing the data
        pipelineWithData.process(data)

        actual should be (expected)
      }
    }
  }
}