package org.scaladebugger.api.dsl.info

import com.sun.jdi.{ThreadReference, VirtualMachine}
import org.scaladebugger.api.lowlevel.jvm.ManagerContainer
import org.scaladebugger.api.virtualmachines.{ObjectCache, ScalaVirtualMachine, ScalaVirtualMachineManager}
import org.scaladebugger.test.utils.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

import scala.util.Success

class ObjectInfoDSLWrapperSpec extends ParallelMockFunSpec
{
  private val TestUniqueId = 1234L
  private val mockObjectInfoProfile = mock[ObjectInfo]

  private val testObjectCache = new ObjectCache()
  private val testScalaVirtualMachine = new Object with ScalaVirtualMachine {
    override val cache: ObjectCache = testObjectCache
    override val lowlevel: ManagerContainer = null
    override val manager: ScalaVirtualMachineManager = null
    override def startProcessingEvents(): Unit = {}
    override def isInitialized: Boolean = false
    override def isProcessingEvents: Boolean = false
    override def suspend(): Unit = {}
    override def stopProcessingEvents(): Unit = {}
    override def resume(): Unit = {}
    override def initialize(
      defaultProfile: String,
      startProcessingEvents: Boolean
    ): Unit = {}
    override val underlyingVirtualMachine: VirtualMachine = null
    override def isStarted: Boolean = false
    override val uniqueId: String = ""
    override protected val profileManager: ProfileManager = null
    override def register(
      name: String,
      profile: DebugProfile
    ): Option[DebugProfile] = None
    override def retrieve(name: String): Option[DebugProfile] = None
    override def unregister(name: String): Option[DebugProfile] = None
  }

  describe("ObjectInfoDSLWrapper") {
    describe("#cache") {
      it("should add the object to the implicit cache if available") {

        (mockObjectInfoProfile.uniqueId _).expects()
          .returning(TestUniqueId).once()

        implicit val objectCache: ObjectCache = testObjectCache
        mockObjectInfoProfile.cache()

        objectCache.has(TestUniqueId) should be (true)
      }

      it("should add the object to underlying JVM's cache if no implicit available") {

        (mockObjectInfoProfile.scalaVirtualMachine _).expects()
          .returning(testScalaVirtualMachine).once()

        (mockObjectInfoProfile.uniqueId _).expects()
          .returning(TestUniqueId).once()

        mockObjectInfoProfile.cache()

        testObjectCache.has(TestUniqueId) should be (true)
      }
    }

    describe("#uncache") {
      it("should remove the object to the implicit cache if available") {

        (mockObjectInfoProfile.uniqueId _).expects()
          .returning(TestUniqueId).twice()

        implicit val objectCache: ObjectCache = testObjectCache
        objectCache.save(mockObjectInfoProfile)

        mockObjectInfoProfile.uncache()

        objectCache.has(TestUniqueId) should be (false)
      }

      it("should remove the object to underlying JVM's cache if no implicit available") {

        (mockObjectInfoProfile.scalaVirtualMachine _).expects()
          .returning(testScalaVirtualMachine).once()

        (mockObjectInfoProfile.uniqueId _).expects()
          .returning(TestUniqueId).twice()

        testObjectCache.save(mockObjectInfoProfile)
        mockObjectInfoProfile.uncache()

        testObjectCache.has(TestUniqueId) should be (false)
      }
    }
  }
}