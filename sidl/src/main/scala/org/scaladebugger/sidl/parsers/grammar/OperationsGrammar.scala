package org.scaladebugger.sidl.parsers.grammar

import org.parboiled2._
import org.scaladebugger.sidl
import org.scaladebugger.sidl.models._

/**
 * Contains operation-related grammars.
 */
trait OperationsGrammar extends Parser with ValueGrammar with WhiteSpaceGrammar {
  this: FunctionGrammar with ConditionGrammar =>

  def Parens: Rule1[Expression] = rule {
    ws('(') ~ Expression ~ ws(')')
  }

  def Assignment: Rule1[Variable] = rule {
    Identifier ~ ws(":=") ~ Expression ~> sidl.models.Variable
  }

  def ExpressionGroup: Rule1[ExpressionGroup] = rule {
    ws('{') ~ zeroOrMore(Expression) ~ ws('}') ~> sidl.models.ExpressionGroup
  }

  def SkipEval: Rule1[SkipEval] = rule {
    ws(ReservedKeywords.SkipEval) ~ (
      FunctionCall | ExpressionGroup | Parens | Identifier
    ) ~> sidl.models.SkipEval
  }

  def BaseValue: Rule1[Expression] = rule {
    SkipEval | Parens | ExpressionGroup | Number | Text |
    Truthy | Falsey | Undefined | Identifier
  }

  def Factor: Rule1[Expression] = rule {
    Assignment | Conditional | Function | FunctionCall | BaseValue
  }

  def Term: Rule1[Expression] = rule {
    Factor ~ zeroOrMore(
      ws('*') ~ Factor ~> sidl.models.Multiply |
      ws('/') ~ Factor ~> sidl.models.Divide   |
      ws('%') ~ Factor ~> sidl.models.Modulus
    )
  }

  def Equation: Rule1[Expression] = rule {
    Term ~ zeroOrMore(
      ws("++") ~ Term ~> PlusPlus |
      ws('+') ~ Term ~> Plus |
      ws('-') ~ Term ~> sidl.models.Minus
    )
  }

  def Expression: Rule1[Expression] = rule {
    WhiteSpace ~ Equation ~ zeroOrMore(
      ws('<')   ~ Equation ~> sidl.models.Less         |
      ws("<=")  ~ Equation ~> sidl.models.LessEqual    |
      ws('>')   ~ Equation ~> sidl.models.Greater      |
      ws(">=")  ~ Equation ~> sidl.models.GreaterEqual |
      ws("==")  ~ Equation ~> sidl.models.Equal        |
      ws("!=")  ~ Equation ~> sidl.models.NotEqual
    ) ~ WhiteSpace ~ optional(';' ~ WhiteSpace)
  }
}
