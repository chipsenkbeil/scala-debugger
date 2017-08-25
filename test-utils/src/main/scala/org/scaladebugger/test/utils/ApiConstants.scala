package org.scaladebugger.test.utils

/**
 * Constants for our tests.
 */
object ApiConstants {
  val EventuallyTimeout = Span(10, Seconds)
  val EventuallyInterval = Span(5, Milliseconds)
  val NoWindows = Tag("NoWindows")
}
