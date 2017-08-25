package org.scaladebugger.api.interfaces.profiles.requests.vm

import org.scaladebugger.api.lowlevel.JDIArgument
import org.scaladebugger.api.lowlevel.jvm.events.data.JDIEventDataResult

import scala.util.Try

/**
 * Represents the interface that needs to be implemented to provide
 * vm disconnect functionality for a specific debug profile.
 */
trait VMDisconnectRequest {
  /** Represents a vm disconnect event and any associated data. */
  type VMDisconnectEventAndData =
    (VMDisconnectEventInfo, Seq[JDIEventDataResult])

  /**
   * Constructs a stream of vm disconnect events.
   *
   * @param extraArguments The additional JDI arguments to provide
   * @return The stream of vm disconnect events
   */
  def tryGetOrCreateVMDisconnectRequest(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[VMDisconnectEventInfo]] = {
    tryGetOrCreateVMDisconnectRequestWithData(extraArguments: _*).map(_.map(_._1).noop())
  }

  /**
   * Constructs a stream of vm disconnect events.
   *
   * @param extraArguments The additional JDI arguments to provide
   * @return The stream of vm disconnect events and any retrieved data based on
   *         requests from extra arguments
   */
  def tryGetOrCreateVMDisconnectRequestWithData(
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[VMDisconnectEventAndData]]

  /**
   * Constructs a stream of vm disconnect events.
   *
   * @param extraArguments The additional JDI arguments to provide
   * @return The stream of vm disconnect events
   */
  def getOrCreateVMDisconnectRequest(
    extraArguments: JDIArgument*
  ): IdentityPipeline[VMDisconnectEventInfo] = {
    tryGetOrCreateVMDisconnectRequest(extraArguments: _*).get
  }

  /**
   * Constructs a stream of vm disconnect events.
   *
   * @param extraArguments The additional JDI arguments to provide
   * @return The stream of vm disconnect events and any retrieved data based on
   *         requests from extra arguments
   */
  def getOrCreateVMDisconnectRequestWithData(
    extraArguments: JDIArgument*
  ): IdentityPipeline[VMDisconnectEventAndData] = {
    tryGetOrCreateVMDisconnectRequestWithData(extraArguments: _*).get
  }
}
