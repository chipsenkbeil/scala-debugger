package org.scaladebugger.api.profiles.swappable.requests.classes

import org.scaladebugger.api.interfaces.lowlevel.classes.ClassUnloadRequestInfo
import org.scaladebugger.api.lowlevel.JDIArgument

import scala.util.Try

/**
 * Represents a swappable profile for class unload events that redirects the
 * invocation to another profile.
 */
trait SwappableClassUnloadRequest extends ClassUnloadRequest {
  this: SwappableDebugProfileManagement =>

  override def tryGetOrCreateClassUnloadRequestWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[ClassUnloadEventAndData]] = {
    withCurrentProfile.tryGetOrCreateClassUnloadRequestWithData(extraArguments: _*)
  }

  override def isClassUnloadRequestWithArgsPending(
    extraArguments: JDIArgument*
  ): Boolean = {
    withCurrentProfile.isClassUnloadRequestWithArgsPending(extraArguments: _*)
  }

  override def removeClassUnloadRequestWithArgs(
    extraArguments: JDIArgument*
  ): Option[ClassUnloadRequestInfo] = {
    withCurrentProfile.removeClassUnloadRequestWithArgs(extraArguments: _*)
  }

  override def removeAllClassUnloadRequests(): Seq[ClassUnloadRequestInfo] = {
    withCurrentProfile.removeAllClassUnloadRequests()
  }

  override def classUnloadRequests: Seq[ClassUnloadRequestInfo] = {
    withCurrentProfile.classUnloadRequests
  }
}
