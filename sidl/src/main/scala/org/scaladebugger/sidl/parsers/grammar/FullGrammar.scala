package org.scaladebugger.sidl.parsers.grammar

import org.parboiled2._
import org.scaladebugger.sidl.models.Expression

/**
 * Represents the full grammar of the small debugger language.
 */
trait FullGrammar
  extends Parser
  with ConditionGrammar
  with FunctionGrammar
  with OperationsGrammar
  with ValueGrammar
  with WhiteSpaceGrammar
{
  def AllInputLines: Rule1[Seq[Expression]] = rule {
    oneOrMore(InputLine) ~ EOI
  }

  def InputLine: Rule1[Expression] = rule { Expression }
}
