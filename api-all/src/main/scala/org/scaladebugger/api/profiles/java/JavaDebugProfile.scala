package org.scaladebugger.api.profiles.java
import com.sun.jdi.VirtualMachine
import org.scaladebugger.api.lowlevel.ManagerContainer
import org.scaladebugger.api.virtualmachines.ScalaVirtualMachine

/**
 * Contains information about the java debug profile.
 */
object JavaDebugProfile {
  val Name: String = "java"
}

/**
 * Represents a debug profile that adds no extra logic on top of the standard
 * JDI.
 *
 * @param scalaVirtualMachine The high-level virtual machine using this profile
 * @param managerContainer The container of low-level managers to use as the
 *                         underlying implementation
 * @param _virtualMachine The underlying virtual machine to use for various
 *                        retrieval methods
 * @param infoProducer The producer of information profiles
 */
class JavaDebugProfile(
  protected val scalaVirtualMachine: ScalaVirtualMachine,
  protected val managerContainer: ManagerContainer
)(
  protected val _virtualMachine: VirtualMachine =
    scalaVirtualMachine.underlyingVirtualMachine,
  protected val infoProducer: InfoProducer = new JavaInfoProducer
) extends ManagerContainerDebugProfile
  with JavaAccessWatchpointRequest
  with JavaBreakpointRequest
  with JavaClassPrepareRequest
  with JavaClassUnloadRequest
  with JavaCreateInfo
  with JavaEventListenerRequest
  with JavaExceptionRequest
  with JavaGrabInfoProfile
  with JavaMethodEntryRequest
  with JavaMethodExitRequest
  with JavaMiscInfo
  with JavaModificationWatchpointRequest
  with JavaMonitorContendedEnteredRequest
  with JavaMonitorContendedEnterRequest
  with JavaMonitorWaitedRequest
  with JavaMonitorWaitRequest
  with JavaStepRequest
  with JavaThreadDeathRequest
  with JavaThreadStartRequest
  with JavaVMStartRequest
  with JavaVMDeathRequest
  with JavaVMDisconnectRequest
