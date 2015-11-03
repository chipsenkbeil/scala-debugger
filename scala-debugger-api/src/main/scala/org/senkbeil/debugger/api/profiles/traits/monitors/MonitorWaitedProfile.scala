package org.senkbeil.debugger.api.profiles.traits.monitors

import com.sun.jdi.event.MonitorWaitedEvent
import org.senkbeil.debugger.api.lowlevel.JDIArgument
import org.senkbeil.debugger.api.lowlevel.events.data.JDIEventDataResult
import org.senkbeil.debugger.api.pipelines.Pipeline
import org.senkbeil.debugger.api.pipelines.Pipeline.IdentityPipeline

import scala.util.Try

/**
 * Represents the interface that needs to be implemented to provide
 * monitor waited functionality for a specific debug profile.
 */
trait MonitorWaitedProfile {
  /** Represents a monitor waited event and any associated data. */
  type MonitorWaitedEventAndData =
    (MonitorWaitedEvent, Seq[JDIEventDataResult])

  /**
   * Constructs a stream of monitor waited events.
   *
   * @param extraArguments The additional JDI arguments to provide
   *
   * @return The stream of monitor waited events
   */
  def onMonitorWaited(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[MonitorWaitedEvent]] = {
    onMonitorWaitedWithData(extraArguments: _*).map(_.map(_._1).noop())
  }

  /**
   * Constructs a stream of monitor waited events.
   *
   * @param extraArguments The additional JDI arguments to provide
   *
   * @return The stream of monitor waited events and any retrieved
   *         data based on requests from extra arguments
   */
  def onMonitorWaitedWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[MonitorWaitedEventAndData]]

  /**
   * Constructs a stream of monitor waited events.
   *
   * @param extraArguments The additional JDI arguments to provide
   *
   * @return The stream of monitor waited events
   */
  def onUnsafeMonitorWaited(
    extraArguments: JDIArgument*
  ): IdentityPipeline[MonitorWaitedEvent] = {
    onMonitorWaited(extraArguments: _*).get
  }

  /**
   * Constructs a stream of monitor waited events.
   *
   * @param extraArguments The additional JDI arguments to provide
   *
   * @return The stream of monitor waited events and any retrieved
   *         data based on requests from extra arguments
   */
  def onUnsafeMonitorWaitedWithData(
    extraArguments: JDIArgument*
  ): IdentityPipeline[MonitorWaitedEventAndData] = {
    onMonitorWaitedWithData(extraArguments: _*).get
  }
}
