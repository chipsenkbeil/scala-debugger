package org.scaladebugger.api.profiles.swappable.requests.vm

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}
import org.scaladebugger.api.lowlevel.JDIArgument
import org.scaladebugger.test.common.utils.ParallelMockFunSpec

class SwappableVMDisconnectRequestSpec extends ParallelMockFunSpec
{
  private val mockDebugProfile = mock[DebugProfile]
  private val mockProfileManager = mock[ProfileManager]

  private val swappableDebugProfile = new Object with SwappableDebugProfile {
    override protected val profileManager: ProfileManager = mockProfileManager
  }

  describe("SwappableVMDisconnectRequest") {
    describe("#tryGetOrCreateVMDisconnectRequestWithData") {
      it("should invoke the method on the underlying profile") {
        val arguments = Seq(mock[JDIArgument])

        (mockProfileManager.retrieve _).expects(*)
          .returning(Some(mockDebugProfile)).once()

        // NOTE: Forced to use onCall with product due to issues with ScalaMock
        //       casting and inability to work with varargs directly
        (mockDebugProfile.tryGetOrCreateVMDisconnectRequestWithData _).expects(*)
          .onCall((t: Product) => {
            val args = t.productElement(0).asInstanceOf[Seq[JDIArgument]]
            args should be(arguments)
            null
          })

        swappableDebugProfile.tryGetOrCreateVMDisconnectRequestWithData(arguments: _*)
      }

      it("should throw an exception if there is no underlying profile") {
        val arguments = Seq(mock[JDIArgument])

        (mockProfileManager.retrieve _).expects(*).returning(None).once()

        intercept[AssertionError] {
          swappableDebugProfile.tryGetOrCreateVMDisconnectRequestWithData(arguments: _*)
        }
      }
    }
  }
}
