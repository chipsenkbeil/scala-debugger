package org.scaladebugger.api.lowlevel.interfaces.vm

import org.scaladebugger.api.lowlevel.RequestInfo
import org.scaladebugger.api.lowlevel.jvm.requests.JDIRequestArgument

/**
 * Represents information about a vm death request.
 *
 * @param requestId The id of the request
 * @param isPending Whether or not this request is pending (not on remote JVM)
 * @param extraArguments The additional arguments provided to the
 *                       vm death request
 */
case class VMDeathRequestInfo(
  requestId: String,
  isPending: Boolean,
  extraArguments: Seq[JDIRequestArgument] = Nil
) extends RequestInfo

