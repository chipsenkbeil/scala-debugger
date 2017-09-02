package org.scaladebugger.api.profiles.swappable.requests.monitors

import org.scaladebugger.api.lowlevel.JDIArgument
import org.scaladebugger.api.lowlevel.jvm.monitors.MonitorWaitedRequestInfo

import scala.util.Try

/**
 * Represents a swappable profile for monitor waited events that
 * redirects the invocation to another profile.
 */
trait SwappableMonitorWaitedRequest extends MonitorWaitedRequest {
  this: SwappableDebugProfileManagement =>

  override def tryGetOrCreateMonitorWaitedRequestWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[MonitorWaitedEventAndData]] = {
    withCurrentProfile.tryGetOrCreateMonitorWaitedRequestWithData(extraArguments: _*)
  }

  override def isMonitorWaitedRequestWithArgsPending(
    extraArguments: JDIArgument*
  ): Boolean = {
    withCurrentProfile.isMonitorWaitedRequestWithArgsPending(extraArguments: _*)
  }

  override def removeMonitorWaitedRequestWithArgs(
    extraArguments: JDIArgument*
  ): Option[MonitorWaitedRequestInfo] = {
    withCurrentProfile.removeMonitorWaitedRequestWithArgs(extraArguments: _*)
  }

  override def removeAllMonitorWaitedRequests(): Seq[MonitorWaitedRequestInfo] = {
    withCurrentProfile.removeAllMonitorWaitedRequests()
  }

  override def monitorWaitedRequests: Seq[MonitorWaitedRequestInfo] = {
    withCurrentProfile.monitorWaitedRequests
  }
}