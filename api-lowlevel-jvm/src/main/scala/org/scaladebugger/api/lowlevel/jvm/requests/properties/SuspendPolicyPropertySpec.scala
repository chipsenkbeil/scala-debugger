package org.scaladebugger.api.lowlevel.jvm.requests.properties

import org.scaladebugger.test.utils.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

class SuspendPolicyPropertySpec extends ParallelMockFunSpec
{
  private val testPolicy = 0
  private val suspendPolicyProperty = SuspendPolicyProperty(policy = testPolicy)

  describe("SuspendPolicyProperty") {
    describe("#toProcessor") {
      it("should return a processor containing the suspend policy property") {
        suspendPolicyProperty.toProcessor.argument should
          be (suspendPolicyProperty)
      }
    }
  }
}
