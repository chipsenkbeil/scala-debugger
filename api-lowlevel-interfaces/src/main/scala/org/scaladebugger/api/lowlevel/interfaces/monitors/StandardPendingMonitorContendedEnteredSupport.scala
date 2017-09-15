package org.scaladebugger.api.lowlevel.interfaces.monitors

/**
 * Provides pending monitor contended entered capabilities to an existing
 * monitor contended entered manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingMonitorContendedEnteredSupport extends PendingMonitorContendedEnteredSupport {
  override protected val pendingActionManager: PendingActionManager[MonitorContendedEnteredRequestInfo] =
    new PendingActionManager[MonitorContendedEnteredRequestInfo]
}
