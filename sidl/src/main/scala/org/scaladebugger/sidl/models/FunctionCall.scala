package org.scaladebugger.sidl.models

case class FunctionCall(
  expression: Expression,
  values: Seq[(Identifier, Expression)]
) extends Expression

