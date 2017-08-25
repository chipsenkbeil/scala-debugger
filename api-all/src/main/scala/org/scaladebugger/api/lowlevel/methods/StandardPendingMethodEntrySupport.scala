package org.scaladebugger.api.lowlevel.methods

/**
 * Provides pending method entry capabilities to an existing method entry
 * manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingMethodEntrySupport extends PendingMethodEntrySupport {
  override protected val pendingActionManager: PendingActionManager[MethodEntryRequestInfo] =
    new PendingActionManager[MethodEntryRequestInfo]
}