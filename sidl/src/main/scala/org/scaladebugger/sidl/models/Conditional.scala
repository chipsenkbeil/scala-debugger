package org.scaladebugger.sidl.models

case class Conditional(
  condition: Expression,
  trueBranch: Expression,
  falseBranch: Expression
) extends Expression
