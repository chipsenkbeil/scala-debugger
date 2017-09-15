package org.scaladebugger.test.it.utils.helpers

import com.sun.jdi.request.ClassUnloadRequest

import scala.util.Try

/**
 * Test class unload manager that merely invokes the provided
 * class unload manager underneath to make it easier to mock.
 *
 * @param classUnloadManager The underlying class unload manager used to
 *                            execute all methods
 */
class TestClassUnloadManager(
  private val classUnloadManager: ClassUnloadManager
) extends ClassUnloadManager {
  override def classUnloadRequestList: Seq[String] =
    classUnloadManager.classUnloadRequestList
  override def hasClassUnloadRequest(id: String): Boolean =
    classUnloadManager.hasClassUnloadRequest(id)
  override def getClassUnloadRequest(id: String): Option[ClassUnloadRequest] =
    classUnloadManager.getClassUnloadRequest(id)
  override def createClassUnloadRequestWithId(requestId: String, extraArguments: JDIRequestArgument*): Try[String] =
    classUnloadManager.createClassUnloadRequestWithId(requestId, extraArguments: _*)
  override def removeClassUnloadRequest(id: String): Boolean =
    classUnloadManager.removeClassUnloadRequest(id)
  override def getClassUnloadRequestInfo(id: String): Option[ClassUnloadRequestInfo] =
    classUnloadManager.getClassUnloadRequestInfo(id)
}
