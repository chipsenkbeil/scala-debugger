package org.scaladebugger.api.interfaces.info.events

import com.sun.jdi.event.VMStartEvent

/**
 * Represents the interface that needs to be implemented to provide
 * an abstraction over the JDI vm start event interface.
 */
trait VMStartEventInfo extends EventInfo {
  /**
   * Returns the JDI representation this profile instance wraps.
   *
   * @return The JDI instance
   */
  override def toJdiInstance: VMStartEvent

  /**
   * Returns the initial thread of the VM that started.
   *
   * @return The information profile about the thread
   */
  def thread: ThreadInfo
}
