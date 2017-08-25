package org.scaladebugger.api.lowlevel.jvm.requests.properties

/**
 * Represents the interface for custom properties to implement.
 */
trait CustomPropertyLike {
  /** @return The key used for the property */
  def key: AnyRef

  /** @return The value used for the property */
  def value: AnyRef
}
