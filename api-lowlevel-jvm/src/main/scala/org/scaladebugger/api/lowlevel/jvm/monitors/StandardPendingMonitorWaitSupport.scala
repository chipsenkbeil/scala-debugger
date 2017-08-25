package org.scaladebugger.api.lowlevel.jvm.monitors

/**
 * Provides pending monitor wait capabilities to an existing
 * monitor wait manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingMonitorWaitSupport extends PendingMonitorWaitSupport {
  override protected val pendingActionManager: PendingActionManager[MonitorWaitRequestInfo] =
    new PendingActionManager[MonitorWaitRequestInfo]
}
