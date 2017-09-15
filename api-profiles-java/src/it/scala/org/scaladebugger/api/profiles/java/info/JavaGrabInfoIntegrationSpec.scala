package org.scaladebugger.api.profiles.java.info
import org.scaladebugger.api.interfaces.profiles.info.ThreadInfo
import org.scaladebugger.api.profiles.java.JavaDebugProfile
import org.scaladebugger.api.utils.JDITools
import org.scaladebugger.api.virtualmachines.DummyScalaVirtualMachine
import org.scaladebugger.test.common.utils.ParallelMockFunSpec
import test.{ApiTestUtilities, VirtualMachineFixtures}

class JavaGrabInfoIntegrationSpec extends ParallelMockFunSpec
  with VirtualMachineFixtures
  with ApiTestUtilities
{
  describe("JavaGrabInfoProfile") {
    it("should be able to list all active threads") {
      val testClass = "org.scaladebugger.test.misc.LaunchingMain"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      withVirtualMachine(testClass) { (s) =>
        logTimeTaken(eventually {
          val threadNames = s.withProfile(JavaDebugProfile.Name)
            .threads.map(_.name)

          // Other threads such as "Signal Handler" can differ per JVM
          threadNames should contain ("main")
        })
      }
    }

    it("should be able to find a thread by its unique id") {
      val testClass = "org.scaladebugger.test.misc.LaunchingMain"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      @volatile var t: Option[ThreadInfo] = None
      val s = DummyScalaVirtualMachine.newInstance()
      s.withProfile(JavaDebugProfile.Name)
        .getOrCreateBreakpointRequest(testFile, 7).foreach(e => t = Some(e.thread))

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          val id = t.get.toJdiInstance.uniqueID()

          s.withProfile(JavaDebugProfile.Name)
            .thread(id).uniqueId should be (id)

          s.withProfile(JavaDebugProfile.Name)
            .thread(t.get.toJdiInstance).uniqueId should be (id)
        })
      }
    }

    it("should be able to find a thread by its name") {
      val testClass = "org.scaladebugger.test.info.Threads"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      val s = DummyScalaVirtualMachine.newInstance()
      s.withProfile(JavaDebugProfile.Name)
        .getOrCreateBreakpointRequest(testFile, 24)

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          val threadWithoutGroup = s.withProfile(JavaDebugProfile.Name)
            .thread("thread-without-group")
          threadWithoutGroup.name should be ("thread-without-group")

          // There is no guarantee that the thread is always from a specific
          // group (test-thread exists in group 1 and 2)
          val testThread = s.withProfile(JavaDebugProfile.Name)
            .thread("test-thread")
          testThread.name should be ("test-thread")

          val uniqueTestThread = s.withProfile(JavaDebugProfile.Name)
            .thread("unique-test-name")
          uniqueTestThread.name should be ("unique-test-name")
        })
      }
    }

    it("should be able to find a thread by its name and thread group") {
      val testClass = "org.scaladebugger.test.info.Threads"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      val s = DummyScalaVirtualMachine.newInstance()
      s.withProfile(JavaDebugProfile.Name)
        .getOrCreateBreakpointRequest(testFile, 24)

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          val threadWithoutGroup = s.withProfile(JavaDebugProfile.Name)
            .thread("thread-without-group", "main")
          threadWithoutGroup.name should be ("thread-without-group")

          val testThreadFromGroup1 = s.withProfile(JavaDebugProfile.Name)
            .thread("test-thread", "test1")
          testThreadFromGroup1.name should be ("test-thread")

          val testThreadFromGroup2 = s.withProfile(JavaDebugProfile.Name)
            .thread("test-thread", "test2")
          testThreadFromGroup2.name should be ("test-thread")

          testThreadFromGroup1.uniqueId should
            not be (testThreadFromGroup2.uniqueId)

          val uniqueTestThread = s.withProfile(JavaDebugProfile.Name)
            .thread("unique-test-name", "test1")
          uniqueTestThread.name should be ("unique-test-name")

          // A matching thread name but incorrect thread group should be None
          s.withProfile(JavaDebugProfile.Name)
            .threadOption("unique-test-name", "test2") should be (None)

          // A matching thread group name but incorrect thread name should be None
          s.withProfile(JavaDebugProfile.Name)
            .threadOption("unique-test-name2", "test1") should be (None)
        })
      }
    }

    it("should be able to list top-level thread groups") {
      val testClass = "org.scaladebugger.test.misc.LaunchingMain"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      withVirtualMachine(testClass) { (s) =>
        logTimeTaken(eventually {
          s.withProfile(JavaDebugProfile.Name).threadGroups.map(_.name) should
            contain theSameElementsAs Seq("system")
        })
      }
    }

    it("should be able to find a thread group by its unique id") {
      val testClass = "org.scaladebugger.test.misc.LaunchingMain"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      @volatile var t: Option[ThreadInfo] = None
      val s = DummyScalaVirtualMachine.newInstance()
      s.withProfile(JavaDebugProfile.Name)
        .getOrCreateBreakpointRequest(testFile, 7).foreach(e => t = Some(e.thread))

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          val tg = t.get.threadGroup
          val id = tg.toJdiInstance.uniqueID()

          s.withProfile(JavaDebugProfile.Name)
            .threadGroup(tg.uniqueId).uniqueId should be (id)

          s.withProfile(JavaDebugProfile.Name)
            .threadGroup(tg.toJdiInstance).uniqueId should be (id)
        })
      }
    }

    it("should be able to find a thread group by its name") {
      val testClass = "org.scaladebugger.test.misc.LaunchingMain"
      val testFile = JDITools.scalaClassStringToFileString(testClass)

      @volatile var t: Option[ThreadInfo] = None
      val s = DummyScalaVirtualMachine.newInstance()
      s.withProfile(JavaDebugProfile.Name)
        .getOrCreateBreakpointRequest(testFile, 7).foreach(e => t = Some(e.thread))

      withVirtualMachine(testClass, pendingScalaVirtualMachines = Seq(s)) { (s) =>
        logTimeTaken(eventually {
          val tg = t.get.threadGroup
          val name = tg.toJdiInstance.name()

          s.withProfile(JavaDebugProfile.Name)
            .threadGroup(name).name should be (name)
        })
      }
    }
  }
}
