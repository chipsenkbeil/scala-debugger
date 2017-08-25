package test

import com.sun.jdi.request.EventRequestManager
import org.scaladebugger.api.interfaces.lowlevel.classes.{ClassPrepareManager, ClassUnloadManager}
import org.scaladebugger.api.lowlevel.PendingRequestSupport
import org.scaladebugger.api.lowlevel.jvm.breakpoints.BreakpointManager
import org.scaladebugger.api.lowlevel.jvm.classes.{ClassManager, ClassPrepareManager, ClassUnloadManager}
import org.scaladebugger.api.lowlevel.jvm.events.EventManager
import org.scaladebugger.api.lowlevel.jvm.exceptions.ExceptionManager
import org.scaladebugger.api.lowlevel.jvm.methods.{MethodEntryManager, MethodExitManager}
import org.scaladebugger.api.lowlevel.jvm.monitors.{MonitorContendedEnterManager, MonitorContendedEnteredManager, MonitorWaitManager, MonitorWaitedManager}
import org.scaladebugger.api.lowlevel.jvm.steps.StepManager
import org.scaladebugger.api.lowlevel.jvm.threads.{ThreadDeathManager, ThreadStartManager}
import org.scaladebugger.api.lowlevel.jvm.vm.VMDeathManager
import org.scaladebugger.api.lowlevel.jvm.watchpoints.{AccessWatchpointManager, ModificationWatchpointManager}

/**
 * Contains all managers with pending support enabled.
 */
object PendingManagers {
  //
  // NOTE: The following classes are explicitly created due to a limitation
  //       of ScalaMock where you cannot issue
  //
  //           mock[VMDeathManager with PendingRequestSupport]
  //
  trait TestPendingAccessWatchpointManager extends AccessWatchpointManager with PendingRequestSupport
  trait TestPendingBreakpointManager extends BreakpointManager with PendingRequestSupport
  trait TestPendingClassManager extends ClassManager with PendingRequestSupport
  trait TestPendingClassPrepareManager extends ClassPrepareManager with PendingRequestSupport
  trait TestPendingClassUnloadManager extends ClassUnloadManager with PendingRequestSupport
  trait TestPendingEventManager extends EventManager with PendingRequestSupport
  trait TestPendingExceptionManager extends ExceptionManager with PendingRequestSupport
  trait TestPendingMethodEntryManager extends MethodEntryManager with PendingRequestSupport
  trait TestPendingMethodExitManager extends MethodExitManager with PendingRequestSupport
  trait TestPendingModificationWatchpointManager extends ModificationWatchpointManager with PendingRequestSupport
  trait TestPendingMonitorContendedEnteredManager extends MonitorContendedEnteredManager with PendingRequestSupport
  trait TestPendingMonitorContendedEnterManager extends MonitorContendedEnterManager with PendingRequestSupport
  trait TestPendingMonitorWaitedManager extends MonitorWaitedManager with PendingRequestSupport
  trait TestPendingMonitorWaitManager extends MonitorWaitManager with PendingRequestSupport
  trait TestPendingEventRequestManager extends EventRequestManager with PendingRequestSupport
  trait TestPendingStepManager extends StepManager with PendingRequestSupport
  trait TestPendingThreadDeathManager extends ThreadDeathManager with PendingRequestSupport
  trait TestPendingThreadStartManager extends ThreadStartManager with PendingRequestSupport
  trait TestPendingVMDeathManager extends VMDeathManager with PendingRequestSupport
}
