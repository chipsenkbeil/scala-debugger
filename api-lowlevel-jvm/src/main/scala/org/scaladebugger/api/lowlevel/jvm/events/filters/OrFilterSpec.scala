package org.scaladebugger.api.lowlevel.jvm.events.filters

import org.scaladebugger.test.utils.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

class OrFilterSpec extends ParallelMockFunSpec
{
  private val orFilter = OrFilter()

  describe("OrFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the or filter") {
        orFilter.toProcessor.argument should be (orFilter)
      }
    }
  }
}
