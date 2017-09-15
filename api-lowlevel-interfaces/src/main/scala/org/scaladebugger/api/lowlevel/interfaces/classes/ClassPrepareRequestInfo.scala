package org.scaladebugger.api.lowlevel.interfaces.classes

/**
 * Represents information about a class prepare request.
 *
 * @param requestId The id of the request
 * @param isPending Whether or not this request is pending (not on remote JVM)
 * @param extraArguments The additional arguments provided to the
 *                       class prepare request
 */
case class ClassPrepareRequestInfo(
  requestId: String,
  isPending: Boolean,
  extraArguments: Seq[JDIRequestArgument] = Nil
) extends RequestInfo

