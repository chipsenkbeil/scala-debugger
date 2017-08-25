package org.scaladebugger.api.lowlevel.jvm

case class StandardRequestInfo(
  requestId: String,
  isPending: Boolean,
  extraArguments: Seq[JDIRequestArgument]
) extends RequestInfo
