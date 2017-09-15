package org.scaladebugger.test.it.utils.helpers

import com.sun.jdi.request.VMDeathRequest

import scala.util.Try

/**
 * Test vm death manager that merely invokes the provided
 * vm death manager underneath to make it easier to mock.
 *
 * @param vmDeathManager The underlying vm death manager used to
 *                            execute all methods
 */
class TestVMDeathManager(
  private val vmDeathManager: VMDeathManager
) extends VMDeathManager {
  override def vmDeathRequestList: Seq[String] =
    vmDeathManager.vmDeathRequestList
  override def hasVMDeathRequest(id: String): Boolean =
    vmDeathManager.hasVMDeathRequest(id)
  override def getVMDeathRequest(id: String): Option[VMDeathRequest] =
    vmDeathManager.getVMDeathRequest(id)
  override def createVMDeathRequestWithId(requestId: String, extraArguments: JDIRequestArgument*): Try[String] =
    vmDeathManager.createVMDeathRequestWithId(requestId, extraArguments: _*)
  override def removeVMDeathRequest(id: String): Boolean =
    vmDeathManager.removeVMDeathRequest(id)
  override def getVMDeathRequestInfo(id: String): Option[VMDeathRequestInfo] =
    vmDeathManager.getVMDeathRequestInfo(id)
}
