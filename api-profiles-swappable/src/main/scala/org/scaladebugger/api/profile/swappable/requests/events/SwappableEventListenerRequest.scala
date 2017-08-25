package org.scaladebugger.api.profile.swappable.requests.events

import org.scaladebugger.api.lowlevel.JDIArgument
import org.scaladebugger.api.lowlevel.events.EventHandlerInfo
import org.scaladebugger.api.lowlevel.events.EventType.EventType

import scala.util.Try

/**
 * Represents a swappable profile for events that redirects the
 * invocation to another profile.
 */
trait SwappableEventListenerRequest extends EventListenerRequest {
  this: SwappableDebugProfileManagement =>

  override def tryCreateEventListenerWithData(
    eventType: EventType,
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[EventAndData]] = {
    withCurrentProfile.tryCreateEventListenerWithData(eventType, extraArguments: _*)
  }

  override def eventHandlers: Seq[EventHandlerInfo] = {
    withCurrentProfile.eventHandlers
  }
}
