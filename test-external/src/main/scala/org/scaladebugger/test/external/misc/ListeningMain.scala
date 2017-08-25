package org.scaladebugger.test.external.misc

/**
 * Used by the listening debugger to verify it can receive JVM connections.
 */
object ListeningMain extends App {
  while (true) { Thread.sleep(1000) }
}
