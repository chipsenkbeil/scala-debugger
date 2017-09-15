package org.scaladebugger.api.lowlevel.interfaces.vm

/**
 * Provides pending vm death capabilities to an existing vm death
 * manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingVMDeathSupport extends PendingVMDeathSupport {
  override protected val pendingActionManager: PendingActionManager[VMDeathRequestInfo] =
    new PendingActionManager[VMDeathRequestInfo]
}
