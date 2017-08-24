package org.scaladebugger.api.lowlevel.classes

import org.scaladebugger.api.interfaces.lowlevel.classes.ClassPrepareRequestInfo

/**
 * Provides pending class prepare capabilities to an existing class prepare
 * manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingClassPrepareSupport extends PendingClassPrepareSupport {
  override protected val pendingActionManager: PendingActionManager[ClassPrepareRequestInfo] =
    new PendingActionManager[ClassPrepareRequestInfo]
}
