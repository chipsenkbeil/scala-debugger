package org.scaladebugger.api.dsl.monitors

import org.scaladebugger.api.lowlevel.JDIArgument
import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult

import scala.util.Try

/**
 * Wraps a profile, providing DSL-like syntax.
 *
 * @param monitorWaitProfile The profile to wrap
 */
class MonitorWaitDSLWrapper private[dsl] (
  private val monitorWaitProfile: MonitorWaitRequest
) {
  /** Represents a MonitorWait event and any associated data. */
  type MonitorWaitEventAndData = (MonitorWaitEventInfo, Seq[JDIEventDataResult])

  /** @see MonitorWaitRequest#tryGetOrCreateMonitorWaitRequest(JDIArgument*) */
  def onMonitorWait(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[MonitorWaitEventInfo]] =
    monitorWaitProfile.tryGetOrCreateMonitorWaitRequest(extraArguments: _*)

  /** @see MonitorWaitRequest#getOrCreateMonitorWaitRequest(JDIArgument*) */
  def onUnsafeMonitorWait(
    extraArguments: JDIArgument*
  ): IdentityPipeline[MonitorWaitEventInfo] =
    monitorWaitProfile.getOrCreateMonitorWaitRequest(extraArguments: _*)

  /** @see MonitorWaitRequest#getOrCreateMonitorWaitRequestWithData(JDIArgument*) */
  def onUnsafeMonitorWaitWithData(
    extraArguments: JDIArgument*
  ): IdentityPipeline[MonitorWaitEventAndData] =
    monitorWaitProfile.getOrCreateMonitorWaitRequestWithData(
      extraArguments: _*
    )

  /** @see MonitorWaitRequest#tryGetOrCreateMonitorWaitRequestWithData(JDIArgument*) */
  def onMonitorWaitWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[MonitorWaitEventAndData]] =
    monitorWaitProfile.tryGetOrCreateMonitorWaitRequestWithData(
      extraArguments: _*
    )
}
