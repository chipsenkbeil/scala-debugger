package org.scaladebugger.api.lowlevel.jvm.monitors

import org.scaladebugger.api.lowlevel.RequestInfo
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument

/**
 * Represents information about a monitor contended entered request.
 *
 * @param requestId The id of the request
 * @param isPending Whether or not this request is pending (not on remote JVM)
 * @param extraArguments The additional arguments provided to the
 *                       monitor contended entered request
 */
case class MonitorContendedEnteredRequestInfo(
  requestId: String,
  isPending: Boolean,
  extraArguments: Seq[JDIRequestArgument] = Nil
) extends RequestInfo

