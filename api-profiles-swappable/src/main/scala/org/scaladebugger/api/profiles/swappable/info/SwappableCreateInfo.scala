package org.scaladebugger.api.profiles.swappable.info

/**
 * Represents a swappable profile for creating data that redirects the
 * invocation to another profile.
 */
trait SwappableCreateInfo extends CreateInfo {
  this: SwappableDebugProfileManagement =>

  override def createRemotely(value: AnyVal): ValueInfo = {
    withCurrentProfile.createRemotely(value)
  }

  override def createRemotely(value: String): ValueInfo = {
    withCurrentProfile.createRemotely(value)
  }
}
