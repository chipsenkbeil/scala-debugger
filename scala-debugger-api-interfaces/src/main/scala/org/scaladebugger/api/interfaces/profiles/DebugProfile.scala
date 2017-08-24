package org.scaladebugger.api.interfaces.profiles

/**
 * Represents the interface that needs to be implemented to provide
 * functionality for a specific debug profile.
 */
trait DebugProfile
  extends AccessWatchpointRequest
  with BreakpointRequest
  with ClassPrepareRequest
  with ClassUnloadRequest
  with CreateInfo
  with EventListenerRequest
  with ExceptionRequest
  with GrabInfoProfile
  with MethodEntryRequest
  with MethodExitRequest
  with MiscInfo
  with ModificationWatchpointRequest
  with MonitorContendedEnteredRequest
  with MonitorContendedEnterRequest
  with MonitorWaitedRequest
  with MonitorWaitRequest
  with StepRequest
  with ThreadDeathRequest
  with ThreadStartRequest
  with VMStartRequest
  with VMDeathRequest
  with VMDisconnectRequest
