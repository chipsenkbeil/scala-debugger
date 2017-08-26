package org.scaladebugger.api.dsl.monitors

import org.scaladebugger.api.lowlevel.JDIArgument
import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult

import scala.util.Try

/**
 * Wraps a profile, providing DSL-like syntax.
 *
 * @param monitorContendedEnterProfile The profile to wrap
 */
class MonitorContendedEnterDSLWrapper private[dsl] (
  private val monitorContendedEnterProfile: MonitorContendedEnterRequest
) {
  /** Represents a MonitorContendedEnter event and any associated data. */
  type MonitorContendedEnterEventAndData =
    (MonitorContendedEnterEventInfo, Seq[JDIEventDataResult])

  /** @see MonitorContendedEnterRequest#tryGetOrCreateMonitorContendedEnterRequest(JDIArgument*) */
  def onMonitorContendedEnter(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[MonitorContendedEnterEventInfo]] =
    monitorContendedEnterProfile.tryGetOrCreateMonitorContendedEnterRequest(extraArguments: _*)

  /** @see MonitorContendedEnterRequest#getOrCreateMonitorContendedEnterRequest(JDIArgument*) */
  def onUnsafeMonitorContendedEnter(
    extraArguments: JDIArgument*
  ): IdentityPipeline[MonitorContendedEnterEventInfo] =
    monitorContendedEnterProfile.getOrCreateMonitorContendedEnterRequest(extraArguments: _*)

  /** @see MonitorContendedEnterRequest#getOrCreateMonitorContendedEnterRequestWithData(JDIArgument*) */
  def onUnsafeMonitorContendedEnterWithData(
    extraArguments: JDIArgument*
  ): IdentityPipeline[MonitorContendedEnterEventAndData] =
    monitorContendedEnterProfile.getOrCreateMonitorContendedEnterRequestWithData(
      extraArguments: _*
    )

  /** @see MonitorContendedEnterRequest#tryGetOrCreateMonitorContendedEnterRequestWithData(JDIArgument*) */
  def onMonitorContendedEnterWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[MonitorContendedEnterEventAndData]] =
    monitorContendedEnterProfile.tryGetOrCreateMonitorContendedEnterRequestWithData(
      extraArguments: _*
    )
}
