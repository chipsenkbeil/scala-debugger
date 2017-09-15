package org.scaladebugger.api.lowlevel.interfaces.requests.properties

import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument

/**
 * Represents a property for a JDI Request.
 */
trait JDIRequestProperty extends JDIRequestArgument {
  /**
   * Creates a new JDI request processor based on this property.
   *
   * @return The new JDI request property processor instance
   */
  def toProcessor: JDIRequestPropertyProcessor
}
