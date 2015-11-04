package org.senkbeil.debugger.api.profiles.swappable

import org.senkbeil.debugger.api.profiles.ProfileManager
import org.senkbeil.debugger.api.profiles.swappable.breakpoints.SwappableBreakpointProfile
import org.senkbeil.debugger.api.profiles.swappable.classes.{SwappableClassPrepareProfile, SwappableClassUnloadProfile}
import org.senkbeil.debugger.api.profiles.swappable.events.SwappableEventProfile
import org.senkbeil.debugger.api.profiles.swappable.exceptions.SwappableExceptionProfile
import org.senkbeil.debugger.api.profiles.swappable.info.SwappableMiscInfoProfile
import org.senkbeil.debugger.api.profiles.swappable.methods.{SwappableMethodEntryProfile, SwappableMethodExitProfile}
import org.senkbeil.debugger.api.profiles.swappable.monitors.{SwappableMonitorContendedEnteredProfile, SwappableMonitorContendedEnterProfile, SwappableMonitorWaitedProfile, SwappableMonitorWaitProfile}
import org.senkbeil.debugger.api.profiles.swappable.steps.SwappableStepProfile
import org.senkbeil.debugger.api.profiles.swappable.threads.{SwappableThreadDeathProfile, SwappableThreadStartProfile}
import org.senkbeil.debugger.api.profiles.swappable.vm.{SwappableVMStartProfile, SwappableVMDisconnectProfile, SwappableVMDeathProfile}
import org.senkbeil.debugger.api.profiles.swappable.watchpoints.{SwappableAccessWatchpointProfile, SwappableModificationWatchpointProfile}
import org.senkbeil.debugger.api.profiles.traits.DebugProfile

/**
 * Contains information about the pure debug profile.
 */
object SwappableDebugProfile {
  val Name: String = "swappable"
}

/**
 * Represents a debug profile that allows swapping the actual profile
 * implementation underneath.
 */
trait SwappableDebugProfile
  extends DebugProfile
  with SwappableAccessWatchpointProfile
  with SwappableBreakpointProfile
  with SwappableClassPrepareProfile
  with SwappableClassUnloadProfile
  with SwappableEventProfile
  with SwappableExceptionProfile
  with SwappableMethodEntryProfile
  with SwappableMethodExitProfile
  with SwappableMiscInfoProfile
  with SwappableModificationWatchpointProfile
  with SwappableMonitorContendedEnteredProfile
  with SwappableMonitorContendedEnterProfile
  with SwappableMonitorWaitedProfile
  with SwappableMonitorWaitProfile
  with SwappableStepProfile
  with SwappableThreadDeathProfile
  with SwappableThreadStartProfile
  with SwappableVMStartProfile
  with SwappableVMDeathProfile
  with SwappableVMDisconnectProfile
{
  protected val profileManager: ProfileManager

  @volatile private var currentProfileName = ""

  /**
   * Sets the current profile to the one with the provided name.
   *
   * @param name The name of the profile
   *
   * @return The updated profile
   */
  def use(name: String): DebugProfile = {
    currentProfileName = name
    this
  }

  /**
   * Retrieves the current underlying profile.
   *
   * @return The active underlying profile
   */
  def withCurrentProfile: DebugProfile = withProfile(currentProfileName)

  /**
   * Retrieves the profile with the provided name.
   *
   * @param name The name of the profile
   *
   * @throws AssertionError If the profile is not found
   * @return The debug profile
   */
  @throws[AssertionError]
  def withProfile(name: String): DebugProfile = {
    val profile = profileManager.retrieve(name)

    assert(profile.nonEmpty, s"Profile $name does not exist!")

    profile.get
  }
}