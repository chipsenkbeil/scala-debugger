package org.scaladebugger.api.profile.swappable.requests.vm

import org.scaladebugger.api.lowlevel.JDIArgument

import scala.util.Try

/**
 * Represents a swappable profile for vm start events that redirects the
 * invocation to another profile.
 */
trait SwappableVMStartRequest extends VMStartRequest {
  this: SwappableDebugProfileManagement =>

  override def tryGetOrCreateVMStartRequestWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[VMStartEventAndData]] = {
    withCurrentProfile.tryGetOrCreateVMStartRequestWithData(extraArguments: _*)
  }
}
