package org.scaladebugger.api.lowlevel.interfaces.monitors

/**
 * Provides pending monitor contended enter capabilities to an existing
 * monitor contended enter manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingMonitorContendedEnterSupport extends PendingMonitorContendedEnterSupport {
  override protected val pendingActionManager: PendingActionManager[MonitorContendedEnterRequestInfo] =
    new PendingActionManager[MonitorContendedEnterRequestInfo]
}
