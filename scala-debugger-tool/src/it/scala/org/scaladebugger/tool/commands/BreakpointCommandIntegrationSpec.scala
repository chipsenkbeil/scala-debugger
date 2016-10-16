package org.scaladebugger.tool.commands

import java.io.File

import org.scaladebugger.api.utils.JDITools
import org.scaladebugger.tool.frontend.VirtualTerminal
import org.scalatest.concurrent.Eventually
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}
import test.{Constants, TestUtilities, ToolFixtures}

class BreakpointCommandIntegrationSpec extends FunSpec with Matchers
  with ParallelTestExecution with ToolFixtures
  with TestUtilities with Eventually
{
  implicit override val patienceConfig = PatienceConfig(
    timeout = scaled(Constants.EventuallyTimeout),
    interval = scaled(Constants.EventuallyInterval)
  )

  describe("BreakpointCommand") {
    it("should create breakpoints successfully") {
      val testClass = "org.scaladebugger.test.breakpoints.DelayedInit"
      val testFile = JDITools.scalaClassStringToFileString(testClass)
      val testFileName = new File(testFile).getName

      // Create two breakpoints before connecting to the JVM
      val q = "\""
      val virtualTerminal = new VirtualTerminal()
      virtualTerminal.newInputLine(s"bp $q$testFile$q 10")
      virtualTerminal.newInputLine(s"bp $q$testFile$q 11")

      withToolRunningUsingTerminal(
        className = testClass,
        virtualTerminal = virtualTerminal
      ) { (vt, sm, start) =>
        // Construct our "nextLine" method based on average timeout
        val waitTime = Constants.NextOutputLineTimeout.millisPart
        val nextLine = () => vt.nextOutputLine(waitTime = waitTime).get

        logTimeTaken({
          // Verify our breakpoints were set
          nextLine() should be (s"Set breakpoint at $testFile:10\n")
          nextLine() should be (s"Set breakpoint at $testFile:11\n")

          // Verify that we have attached to the JVM
          nextLine() should startWith ("Attached with id")

          // Assert that we hit the first breakpoint
          nextLine() should be (s"Breakpoint hit at $testFileName:10\n")

          // Continue on to the next breakpoint (resume main thread)
          vt.newInputLine("resume \"main\"")

          // Assert that we hit the second breakpoint
          nextLine() should be (s"Breakpoint hit at $testFileName:11\n")
        })
      }
    }
  }
}
