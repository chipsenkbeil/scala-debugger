package org.scaladebugger.api.lowlevel.interfaces

/**
 * Represents generic information about a request.
 */
trait RequestInfo {
  /** The id of the request. */
  def requestId: String

  /** Whether or not this request is pending (not on remote JVM). */
  def isPending: Boolean

  /** Represents extra arguments provided to the request. */
  def extraArguments: Seq[JDIRequestArgument]
}
