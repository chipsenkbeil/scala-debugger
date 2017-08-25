package org.scaladebugger.test.utils

abstract class ParallelMockFunSpec
  extends FunSpec
    with Matchers
    with MockFactory
    with ParallelTestExecution
    with org.scalamock.matchers.Matchers
    with FixedParallelSuite
