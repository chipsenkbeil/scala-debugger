package org.scaladebugger.api.profiles.traits.info

import com.sun.jdi.ClassLoaderReference
import org.scaladebugger.macros.freeze.FreezeMetadata.ReturnType
import org.scaladebugger.macros.freeze.{CanFreeze, CannotFreeze, Freezable, FreezeMetadata}

/**
 * Represents the interface for "class loader"-based interaction.
 */
@Freezable
trait ClassLoaderInfo extends ObjectInfo with CommonInfo {
  /**
   * Converts the current profile instance to a representation of
   * low-level Java instead of a higher-level abstraction.
   *
   * @return The profile instance providing an implementation corresponding
   *         to Java
   */
  @CannotFreeze
  override def toJavaInfo: ClassLoaderInfo

  /**
   * Returns the JDI representation this profile instance wraps.
   *
   * @return The JDI instance
   */
  @CannotFreeze
  override def toJdiInstance: ClassLoaderReference

  /**
   * Retrieves all loaded classes defined by this class loader.
   *
   * @return The collection of reference types for the loaded classes
   */
  @FreezeMetadata(ReturnType.FreezeCollection)
  @CanFreeze
  def definedClasses: Seq[ReferenceTypeInfo]

  /**
   * Retrieves all classes for which this class loader served as the initiating
   * loader.
   *
   * @return The collection of reference types for the initiated classes
   */
  @FreezeMetadata(ReturnType.FreezeCollection)
  @CanFreeze
  def visibleClasses: Seq[ReferenceTypeInfo]
}
