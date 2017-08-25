package org.scaladebugger.api.profile.swappable.requests.classes

import org.scaladebugger.api.interfaces.lowlevel.classes.ClassPrepareRequestInfo
import org.scaladebugger.api.lowlevel.JDIArgument

import scala.util.Try

/**
 * Represents a swappable profile for class prepare events that redirects the
 * invocation to another profile.
 */
trait SwappableClassPrepareRequest extends ClassPrepareRequest {
  this: SwappableDebugProfileManagement =>

  override def tryGetOrCreateClassPrepareRequestWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[ClassPrepareEventAndData]] = {
    withCurrentProfile.tryGetOrCreateClassPrepareRequestWithData(extraArguments: _*)
  }

  override def isClassPrepareRequestWithArgsPending(
    extraArguments: JDIArgument*
  ): Boolean = {
    withCurrentProfile.isClassPrepareRequestWithArgsPending(extraArguments: _*)
  }

  override def removeClassPrepareRequestWithArgs(
    extraArguments: JDIArgument*
  ): Option[ClassPrepareRequestInfo] = {
    withCurrentProfile.removeClassPrepareRequestWithArgs(extraArguments: _*)
  }

  override def removeAllClassPrepareRequests(): Seq[ClassPrepareRequestInfo] = {
    withCurrentProfile.removeAllClassPrepareRequests()
  }

  override def classPrepareRequests: Seq[ClassPrepareRequestInfo] = {
    withCurrentProfile.classPrepareRequests
  }
}
