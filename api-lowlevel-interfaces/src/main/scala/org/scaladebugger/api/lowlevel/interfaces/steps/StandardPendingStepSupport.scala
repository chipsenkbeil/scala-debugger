package org.scaladebugger.api.lowlevel.interfaces.steps

/**
 * Provides pending step capabilities to an existing access watchpoint manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingStepSupport extends PendingStepSupport {
  override protected val pendingActionManager: PendingActionManager[StepRequestInfo] =
    new PendingActionManager[StepRequestInfo]
}
