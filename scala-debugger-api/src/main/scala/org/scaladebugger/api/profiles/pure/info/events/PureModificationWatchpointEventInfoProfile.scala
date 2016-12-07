package org.scaladebugger.api.profiles.pure.info.events

import com.sun.jdi._
import com.sun.jdi.event.ModificationWatchpointEvent
import org.scaladebugger.api.lowlevel.JDIArgument
import org.scaladebugger.api.profiles.traits.info.InfoProducerProfile
import org.scaladebugger.api.profiles.traits.info.events.{ModificationWatchpointEventInfoProfile, EventInfoProfile}
import org.scaladebugger.api.virtualmachines.ScalaVirtualMachine

/**
 * Represents a pure implementation of a modification watchpoint event info
 * profile that adds no custom logic on top of the standard JDI.
 *
 * @param scalaVirtualMachine The high-level virtual machine containing the
 *                            array
 * @param infoProducer The producer of info-based profile instances
 * @param modificationWatchpointEvent The modification watchpoint event to wrap in the profile
 * @param jdiArguments The request and event arguments tied to the provided
 *                     event
 * @param _container Either the object or class (if static) containing the
 *                   field being modified
 * @param _field The field being modified
 * @param _virtualMachine The low-level virtual machine where the event
 *                        originated
 * @param _thread The thread where the event originated
 * @param _threadReferenceType The reference type of the thread where the
 *                             event originated
 * @param _location The location of the modification/modification occurrence
 */
class PureModificationWatchpointEventInfoProfile(
  override val scalaVirtualMachine: ScalaVirtualMachine,
  override protected val infoProducer: InfoProducerProfile,
  private val modificationWatchpointEvent: ModificationWatchpointEvent,
  private val jdiArguments: Seq[JDIArgument] = Nil
)(
  _container: => Either[ObjectReference, ReferenceType],
  _field: => Field,
  _virtualMachine: => VirtualMachine,
  _thread: => ThreadReference,
  _threadReferenceType: => ReferenceType,
  _location: => Location
) extends PureWatchpointEventInfoProfile(
  scalaVirtualMachine = scalaVirtualMachine,
  infoProducer = infoProducer,
  watchpointEvent = modificationWatchpointEvent,
  jdiArguments = jdiArguments
)(
  _container = _container,
  _field = _field,
  _virtualMachine = _virtualMachine,
  _thread = _thread,
  _threadReferenceType = _threadReferenceType,
  _location = _location
) with ModificationWatchpointEventInfoProfile {
  /**
   * Returns the JDI representation this profile instance wraps.
   *
   * @return The JDI instance
   */
  override def toJdiInstance: ModificationWatchpointEvent = {
    modificationWatchpointEvent
  }

  /**
   * Returns whether or not this info profile represents the low-level Java
   * implementation.
   *
   * @return If true, this profile represents the low-level Java information,
   *         otherwise this profile represents something higher-level like
   *         Scala, Jython, or JRuby
   */
  override def isJavaInfo: Boolean = true

  /**
   * Converts the current profile instance to a representation of
   * low-level Java instead of a higher-level abstraction.
   *
   * @return The profile instance providing an implementation corresponding
   *         to Java
   */
  override def toJavaInfo: ModificationWatchpointEventInfoProfile = {
    val jep = infoProducer.eventProducer.toJavaInfo
    jep.newModificationWatchpointEventInfoProfile(
      scalaVirtualMachine = scalaVirtualMachine,
      modificationWatchpointEvent = modificationWatchpointEvent,
      jdiArguments = jdiArguments
    )(
      container = _container,
      field = _field,
      virtualMachine = _virtualMachine,
      thread = _thread,
      threadReferenceType = _threadReferenceType,
      location = _location
    )
  }
}