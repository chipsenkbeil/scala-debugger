package org.scaladebugger.api.profiles.swappable.requests.vm

import org.scaladebugger.api.lowlevel.JDIArgument

import scala.util.Try

/**
 * Represents a swappable profile for vm disconnect events that redirects the
 * invocation to another profile.
 */
trait SwappableVMDisconnectRequest extends VMDisconnectRequest {
  this: SwappableDebugProfileManagement =>

  override def tryGetOrCreateVMDisconnectRequestWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[VMDisconnectEventAndData]] = {
    withCurrentProfile.tryGetOrCreateVMDisconnectRequestWithData(extraArguments: _*)
  }
}
