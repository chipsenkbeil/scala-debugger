package org.scaladebugger.sidl.models

case class Variable(
  identifier: Identifier,
  value: Expression
) extends Expression
