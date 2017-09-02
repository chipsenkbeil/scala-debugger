package org.scaladebugger.api.interfaces.info.events

import com.sun.jdi.event.ThreadDeathEvent

/**
 * Represents the interface that needs to be implemented to provide
 * an abstraction over the JDI thread death event interface.
 */
trait ThreadDeathEventInfo extends EventInfo {
  /**
   * Returns the JDI representation this profile instance wraps.
   *
   * @return The JDI instance
   */
  override def toJdiInstance: ThreadDeathEvent

  /**
   * Returns the thread which is terminating.
   *
   * @return The information profile about the thread
   */
  def thread: ThreadInfo
}
