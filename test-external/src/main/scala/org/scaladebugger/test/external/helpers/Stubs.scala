package org.scaladebugger.test.external.helpers

/**
 * Provides stub methods that do not do anything but provide a breakpointable
 * and steppable line.
 *
 * @note Lifted from Ensime, which in turn lifted from Scala IDE.
 */
object Stubs {
  def noop(a: Any) = {}
  def ret[T](a: T): T = a
}
