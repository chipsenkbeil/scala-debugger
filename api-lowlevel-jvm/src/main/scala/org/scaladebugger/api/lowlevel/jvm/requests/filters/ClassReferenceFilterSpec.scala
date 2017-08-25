package org.scaladebugger.api.lowlevel.jvm.requests.filters

import com.sun.jdi.ReferenceType
import org.scaladebugger.test.utils.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

class ClassReferenceFilterSpec extends ParallelMockFunSpec
{
  private val mockReferenceType = mock[ReferenceType]
  private val classReferenceFilter = ClassReferenceFilter(
    referenceType = mockReferenceType
  )

  describe("ClassReferenceFilter") {
    describe("#toProcessor") {
      it("should return a processor containing the class reference filter") {
        classReferenceFilter.toProcessor.argument should be (classReferenceFilter)
      }
    }
  }
}
