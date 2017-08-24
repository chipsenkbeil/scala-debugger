package org.scaladebugger.api.profiles.java.info


import com.sun.jdi._

/**
 * Represents a java implementation of a thread status profile that adds no
 * custom logic on top of the standard JDI.
 *
 * @param _threadReference The reference to the underlying JDI thread
 */
class JavaThreadStatusInfo(
  private val _threadReference: ThreadReference
) extends ThreadStatusInfo {
  /**
   * Represents the status code for the thread.
   *
   * @return The status code as a number
   */
  override def statusCode: Int = _threadReference.status()

  /**
   * Indicates whether or not the status of this thread is known.
   *
   * @return False if the status is known, otherwise true
   */
  override def isUnknown: Boolean =
    statusCode == ThreadReference.THREAD_STATUS_UNKNOWN

  /**
   * Indicates whether or not this thread is a zombie.
   *
   * @return True if a zombie, otherwise false
   */
  override def isZombie: Boolean =
    statusCode == ThreadReference.THREAD_STATUS_ZOMBIE

  /**
   * Indicates whether or not this thread is running.
   *
   * @return True if running, otherwise false
   */
  override def isRunning: Boolean =
    statusCode == ThreadReference.THREAD_STATUS_RUNNING

  /**
   * Indicates whether or not this thread is sleeping.
   *
   * @return True if sleeping, otherwise false
   */
  override def isSleeping: Boolean =
    statusCode == ThreadReference.THREAD_STATUS_SLEEPING

  /**
   * Indicates whether or not this thread is monitoring.
   *
   * @return True if monitoring, otherwise false
   */
  override def isMonitor: Boolean =
    statusCode == ThreadReference.THREAD_STATUS_MONITOR

  /**
   * Indicates whether or not this thread is waiting.
   *
   * @return True if waiting, otherwise false
   */
  override def isWait: Boolean =
    statusCode == ThreadReference.THREAD_STATUS_WAIT

  /**
   * Indicates whether or not this thread has started.
   *
   * @return True if has started, otherwise false
   */
  override def isNotStarted: Boolean =
    statusCode == ThreadReference.THREAD_STATUS_NOT_STARTED

  /**
   * Indicates whether or not this thread is suspended at a breakpoint.
   *
   * @return True if suspended at a breakpoint, otherwise false
   */
  override def isAtBreakpoint: Boolean = _threadReference.isAtBreakpoint

  /**
   * Indicates the total number of times this thread has been suspended.
   *
   * @return The total number of pending suspensions
   */
  override def suspendCount: Int = _threadReference.suspendCount()

  /**
   * Indicates whether or not this thread is suspended.
   *
   * @return True if suspended, otherwise false
   */
  override def isSuspended: Boolean = _threadReference.isSuspended
}
