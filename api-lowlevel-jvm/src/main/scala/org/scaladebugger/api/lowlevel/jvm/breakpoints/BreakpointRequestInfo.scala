package org.scaladebugger.api.lowlevel.jvm.breakpoints

/**
 * Represents information about a breakpoint request.
 *
 * @param requestId The id of the request
 * @param isPending Whether or not this request is pending (not on remote JVM)
 * @param fileName The name of the file containing the breakpoint
 * @param lineNumber The number of the line where the breakpoint is set
 * @param extraArguments The additional arguments provided to the breakpoint
 */
case class BreakpointRequestInfo(
  requestId: String,
  isPending: Boolean,
  fileName: String,
  lineNumber: Int,
  extraArguments: Seq[JDIRequestArgument] = Nil
) extends RequestInfo

