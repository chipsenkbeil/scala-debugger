package org.scaladebugger.api.profiles.interfaces

import org.scaladebugger.api.interfaces.info.{CreateInfo, GrabInfoProfile, MiscInfo}
import org.scaladebugger.api.interfaces.requests.breakpoints.BreakpointRequest
import org.scaladebugger.api.interfaces.requests.classes.{ClassPrepareRequest, ClassUnloadRequest}
import org.scaladebugger.api.interfaces.requests.events.EventListenerRequest
import org.scaladebugger.api.interfaces.requests.exceptions.ExceptionRequest
import org.scaladebugger.api.interfaces.requests.methods.{MethodEntryRequest, MethodExitRequest}
import org.scaladebugger.api.interfaces.requests.monitors.{MonitorContendedEnterRequest, MonitorContendedEnteredRequest, MonitorWaitRequest, MonitorWaitedRequest}
import org.scaladebugger.api.interfaces.requests.steps.StepRequest
import org.scaladebugger.api.interfaces.requests.threads.{ThreadDeathRequest, ThreadStartRequest}
import org.scaladebugger.api.interfaces.requests.vm.{VMDeathRequest, VMDisconnectRequest, VMStartRequest}
import org.scaladebugger.api.interfaces.requests.watchpoints.{AccessWatchpointRequest, ModificationWatchpointRequest}

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
