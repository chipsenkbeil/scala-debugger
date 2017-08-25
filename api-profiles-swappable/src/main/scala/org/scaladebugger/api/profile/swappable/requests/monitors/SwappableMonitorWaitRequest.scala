package org.scaladebugger.api.profile.swappable.requests.monitors

import org.scaladebugger.api.lowlevel.JDIArgument
import org.scaladebugger.api.lowlevel.jvm.monitors.MonitorWaitRequestInfo

import scala.util.Try

/**
 * Represents a swappable profile for monitor wait events that
 * redirects the invocation to another profile.
 */
trait SwappableMonitorWaitRequest extends MonitorWaitRequest {
  this: SwappableDebugProfileManagement =>

  override def tryGetOrCreateMonitorWaitRequestWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[MonitorWaitEventAndData]] = {
    withCurrentProfile.tryGetOrCreateMonitorWaitRequestWithData(extraArguments: _*)
  }

  override def isMonitorWaitRequestWithArgsPending(
    extraArguments: JDIArgument*
  ): Boolean = {
    withCurrentProfile.isMonitorWaitRequestWithArgsPending(extraArguments: _*)
  }

  override def removeMonitorWaitRequestWithArgs(
    extraArguments: JDIArgument*
  ): Option[MonitorWaitRequestInfo] = {
    withCurrentProfile.removeMonitorWaitRequestWithArgs(extraArguments: _*)
  }

  override def removeAllMonitorWaitRequests(): Seq[MonitorWaitRequestInfo] = {
    withCurrentProfile.removeAllMonitorWaitRequests()
  }

  override def monitorWaitRequests: Seq[MonitorWaitRequestInfo] = {
    withCurrentProfile.monitorWaitRequests
  }
}
