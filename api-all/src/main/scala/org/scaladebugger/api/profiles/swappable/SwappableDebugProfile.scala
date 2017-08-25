package org.scaladebugger.api.profiles.swappable

/**
 * Contains information about the java debug profile.
 */
object SwappableDebugProfile {
  val Name: String = "swappable"
}

/**
 * Represents a debug profile that allows swapping the actual profile
 * implementation underneath.
 */
trait SwappableDebugProfile
  extends DebugProfile
  with SwappableDebugProfileManagement
  with SwappableAccessWatchpointRequest
  with SwappableBreakpointRequest
  with SwappableClassPrepareRequest
  with SwappableClassUnloadRequest
  with SwappableCreateInfo
  with SwappableEventListenerRequest
  with SwappableExceptionRequest
  with SwappableGrabInfoProfile
  with SwappableMethodEntryRequest
  with SwappableMethodExitRequest
  with SwappableMiscInfo
  with SwappableModificationWatchpointRequest
  with SwappableMonitorContendedEnteredRequest
  with SwappableMonitorContendedEnterRequest
  with SwappableMonitorWaitedRequest
  with SwappableMonitorWaitRequest
  with SwappableStepRequest
  with SwappableThreadDeathRequest
  with SwappableThreadStartRequest
  with SwappableVMStartRequest
  with SwappableVMDeathRequest
  with SwappableVMDisconnectRequest
