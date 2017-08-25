package org.scaladebugger.api.lowlevel.jvm.classes

import ClassUnloadRequestInfo

/**
 * Provides pending class unload capabilities to an existing class unload
 * manager.
 *
 * Contains an internal pending action manager.
 */
trait StandardPendingClassUnloadSupport extends PendingClassUnloadSupport {
  override protected val pendingActionManager: PendingActionManager[ClassUnloadRequestInfo] =
    new PendingActionManager[ClassUnloadRequestInfo]
}
