package org.scaladebugger.api.lowlevel.breakpoints

import org.scaladebugger.api.interfaces.lowlevel.breakpoints.BreakpointRequestInfo

/**
 * Provides pending breakpoint capabilities to an existing breakpoint manager.
 * Contains an internal pending action manager.
 */
trait StandardPendingBreakpointSupport extends PendingBreakpointSupport {
  override protected val pendingActionManager: PendingActionManager[BreakpointRequestInfo] =
    new PendingActionManager[BreakpointRequestInfo]
}
