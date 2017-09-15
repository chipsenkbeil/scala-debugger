package org.scaladebugger.api.lowlevel.interfaces.monitors

import org.scaladebugger.api.lowlevel.RequestInfo
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument

/**
 * Represents information about a monitor contended enter request.
 *
 * @param requestId The id of the request
 * @param isPending Whether or not this request is pending (not on remote JVM)
 * @param extraArguments The additional arguments provided to the
 *                       monitor contended enter request
 */
case class MonitorContendedEnterRequestInfo(
  requestId: String,
  isPending: Boolean,
  extraArguments: Seq[JDIRequestArgument] = Nil
) extends RequestInfo

