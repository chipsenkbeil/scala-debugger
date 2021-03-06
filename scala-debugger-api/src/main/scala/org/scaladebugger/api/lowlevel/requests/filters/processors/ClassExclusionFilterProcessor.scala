package org.scaladebugger.api.lowlevel.requests.filters.processors

import com.sun.jdi.request._
import org.scaladebugger.api.lowlevel.requests.filters.{JDIRequestFilter, JDIRequestFilterProcessor, ClassExclusionFilter}

/**
 * Represents a processor for the class exclusion filter.
 *
 * @param classExclusionFilter The class exclusion filter to use when processing
 */
class ClassExclusionFilterProcessor(
  val classExclusionFilter: ClassExclusionFilter
) extends JDIRequestFilterProcessor {
  private val classPattern = classExclusionFilter.classPattern

  /**
   * Processes the provided event request with the filter logic.
   *
   * @param eventRequest The request to process
   *
   * @return The updated request
   */
  override def process(eventRequest: EventRequest): EventRequest = {
    // Apply the filter to the JDI request if it supports the filter
    if (eventRequest != null) (eventRequest match {
      case r: AccessWatchpointRequest         => r.addClassExclusionFilter _
      case r: ClassPrepareRequest             => r.addClassExclusionFilter _
      case r: ClassUnloadRequest              => r.addClassExclusionFilter _
      case r: ExceptionRequest                => r.addClassExclusionFilter _
      case r: MethodEntryRequest              => r.addClassExclusionFilter _
      case r: MethodExitRequest               => r.addClassExclusionFilter _
      case r: ModificationWatchpointRequest   => r.addClassExclusionFilter _
      case r: MonitorContendedEnteredRequest  => r.addClassExclusionFilter _
      case r: MonitorContendedEnterRequest    => r.addClassExclusionFilter _
      case r: MonitorWaitedRequest            => r.addClassExclusionFilter _
      case r: MonitorWaitRequest              => r.addClassExclusionFilter _
      case r: StepRequest                     => r.addClassExclusionFilter _
      case _                                  => (_: String) => {}
    })(classPattern)

    eventRequest
  }

  override val argument: JDIRequestFilter = classExclusionFilter
}
