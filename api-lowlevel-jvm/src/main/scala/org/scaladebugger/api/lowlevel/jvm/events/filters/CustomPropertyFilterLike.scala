package org.scaladebugger.api.lowlevel.jvm.events.filters

/**
 * Represents the interface for custom property filters to implement.
 */
trait CustomPropertyFilterLike {
  /** @return The key of the property to match */
  def key: AnyRef

  /** @return The value of the property to match */
  def value: AnyRef
}
