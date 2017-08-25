package org.scaladebugger.api.lowlevel.watchpoints

/**
 * Provides pending access watchpoint capabilities to an existing access
 * watchpoint manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingAccessWatchpointSupport extends PendingAccessWatchpointSupport {
  override protected val pendingActionManager: PendingActionManager[AccessWatchpointRequestInfo] =
    new PendingActionManager[AccessWatchpointRequestInfo]
}
