package org.scaladebugger.api.lowlevel.interfaces.exceptions

/**
 * Provides pending exception capabilities to an existing exception manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingExceptionSupport extends PendingExceptionSupport {
  override protected val pendingActionManager: PendingActionManager[ExceptionRequestInfo] =
    new PendingActionManager[ExceptionRequestInfo]
}
