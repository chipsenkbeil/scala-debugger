package org.senkbeil.debugger.api.debuggers

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, OneInstancePerTest}
import org.senkbeil.debugger.api.utils.JDILoader
import org.senkbeil.debugger.api.virtualmachines.{DummyScalaVirtualMachine, ScalaVirtualMachine, StandardScalaVirtualMachine}

class DebuggerSpec extends FunSpec with Matchers with OneInstancePerTest
  with MockFactory
{
  private class TestDebugger(override val jdiLoader: JDILoader)
    extends Debugger
  {
    override def assertJdiLoaded(): Unit = super.assertJdiLoaded()
    override def stop(): Unit = ???
    override def isRunning: Boolean = ???
    override def start[T](
      startProcessingEvents: Boolean,
      newVirtualMachineFunc: (ScalaVirtualMachine) => T
    ): Unit = ???
    override def newDummyScalaVirtualMachine(): ScalaVirtualMachine = ???
  }

  describe("Debugger") {
    describe("#isAvailable") {
      it("should return true if jdi loader is available") {
        val expected = true

        // NOTE: Unable to mock default argument
        // See https://github.com/paulbutcher/ScalaMock/issues/43
        val _jdiLoader = new JDILoader() {
          override def isJdiAvailable(classLoader: ClassLoader): Boolean = true
        }
        val debugger = new TestDebugger(_jdiLoader)

        val actual = debugger.isAvailable

        actual should be (expected)
      }

      it("should return false if jdi loader is not available") {
        val expected = false

        // NOTE: Unable to mock default argument
        // See https://github.com/paulbutcher/ScalaMock/issues/43
        val _jdiLoader = new JDILoader() {
          override def isJdiAvailable(classLoader: ClassLoader): Boolean = false
        }
        val debugger = new TestDebugger(_jdiLoader)

        val actual = debugger.isAvailable

        actual should be (expected)
      }
    }

    describe("#assertJdiLoaded") {
      it("should throw an assertion error if failed to load jdi") {
        // NOTE: Unable to mock default argument
        // See https://github.com/paulbutcher/ScalaMock/issues/43
        val _jdiLoader = new JDILoader() {
          override def tryLoadJdi(classLoader: ClassLoader): Boolean = false
        }
        val debugger = new TestDebugger(_jdiLoader)

        intercept[AssertionError] {
          debugger.assertJdiLoaded()
        }
      }

      it("should do nothing if successfully loaded jdi") {
        // NOTE: Unable to mock default argument
        // See https://github.com/paulbutcher/ScalaMock/issues/43
        val _jdiLoader = new JDILoader() {
          override def tryLoadJdi(classLoader: ClassLoader): Boolean = true
        }
        val debugger = new TestDebugger(_jdiLoader)

        debugger.assertJdiLoaded()
      }
    }

    describe("#addPendingScalaVirtualMachine") {
      it("should add the virtual machine to the list") {
        val debugger = new TestDebugger(null)

        val testUniqueId = java.util.UUID.randomUUID().toString
        val scalaVirtualMachine = new DummyScalaVirtualMachine(null) {
          override val uniqueId: String = testUniqueId
        }

        val expected = Some(scalaVirtualMachine)

        val actual = debugger.addPendingScalaVirtualMachine(scalaVirtualMachine)

        actual should be (expected)
        debugger.getPendingScalaVirtualMachines should contain (expected.get)
      }

      it("should not add the virtual machine if one with the same id has already been added") {
        val debugger = new TestDebugger(null)

        val testUniqueId = java.util.UUID.randomUUID().toString
        val scalaVirtualMachine = new DummyScalaVirtualMachine(null) {
          override val uniqueId: String = testUniqueId
        }

        debugger.addPendingScalaVirtualMachine(scalaVirtualMachine)

        val expected = None

        val actual = debugger.addPendingScalaVirtualMachine(scalaVirtualMachine)

        actual should be (expected)
        debugger.getPendingScalaVirtualMachines should contain (scalaVirtualMachine)
      }
    }

    describe("#removePendingScalaVirtualMachine") {
      it("should remove the virtual machine from the list") {
        val debugger = new TestDebugger(null)

        val testUniqueId = java.util.UUID.randomUUID().toString
        val scalaVirtualMachine = new DummyScalaVirtualMachine(null) {
          override val uniqueId: String = testUniqueId
        }

        debugger.addPendingScalaVirtualMachine(scalaVirtualMachine)

        debugger.removePendingScalaVirtualMachine(scalaVirtualMachine.uniqueId)

        debugger.getPendingScalaVirtualMachines should be (empty)
      }

      it("should do nothing if no virtual machine with the id has been added") {
        val debugger = new TestDebugger(null)

        debugger.removePendingScalaVirtualMachine("") should be (None)
      }
    }

    describe("#withPending") {
      it("should add the virtual machine to the list") {
        val debugger = new TestDebugger(null)

        val testUniqueId = java.util.UUID.randomUUID().toString
        val scalaVirtualMachine = new DummyScalaVirtualMachine(null) {
          override val uniqueId: String = testUniqueId
        }

        debugger.withPending(scalaVirtualMachine)

        debugger.getPendingScalaVirtualMachines should contain (scalaVirtualMachine)
      }

      it("should not add the virtual machine if one with the same id has already been added") {
        val debugger = new TestDebugger(null)

        val testUniqueId = java.util.UUID.randomUUID().toString
        val scalaVirtualMachine = new DummyScalaVirtualMachine(null) {
          override val uniqueId: String = testUniqueId
        }

        val expected = Seq(scalaVirtualMachine)

        debugger
          .withPending(scalaVirtualMachine)
          .withPending(scalaVirtualMachine)

        val actual = debugger.getPendingScalaVirtualMachines

        actual should be (expected)
      }
    }

    describe("#withoutPending") {
      it("should remove the virtual machine from the list") {
        val debugger = new TestDebugger(null)

        val testUniqueId = java.util.UUID.randomUUID().toString
        val scalaVirtualMachine = new DummyScalaVirtualMachine(null) {
          override val uniqueId: String = testUniqueId
        }

        debugger
          .withPending(scalaVirtualMachine)
          .withoutPending(scalaVirtualMachine.uniqueId)

        debugger.getPendingScalaVirtualMachines should be (empty)
      }

      it("should do nothing if no virtual machine with the id has been added") {
        val debugger = new TestDebugger(null)

        debugger.withoutPending(java.util.UUID.randomUUID().toString)

        debugger.getPendingScalaVirtualMachines should be (empty)
      }
    }
  }
}
