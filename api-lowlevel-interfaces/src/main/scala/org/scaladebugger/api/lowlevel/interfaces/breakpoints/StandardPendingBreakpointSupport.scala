package org.scaladebugger.api.lowlevel.interfaces.breakpoints

import BreakpointRequestInfo

/**
 * Provides pending breakpoint capabilities to an existing breakpoint manager.
 * Contains an internal pending action manager.
 */
trait StandardPendingBreakpointSupport extends PendingBreakpointSupport {
  override protected val pendingActionManager: PendingActionManager[BreakpointRequestInfo] =
    new PendingActionManager[BreakpointRequestInfo]
}
