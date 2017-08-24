package org.scaladebugger.api.profiles.java.info

import com.sun.jdi._
import org.scaladebugger.test.helpers.ParallelMockFunSpec
import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}

class JavaTypeCheckerSpec extends ParallelMockFunSpec
{
  private val javaTypeCheckerProfile = new JavaTypeChecker

  describe("JavaTypeChecker") {
    describe("#equalTypeNames") {
      it("should return true for string equal type names") {
        val expected = true

        val t1 = "some.type"
        val t2 = "some.type"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }

      it("should return true for java.lang.Boolean and boolean") {
        val expected = true

        val t1 = "java.lang.Boolean"
        val t2 = "boolean"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }

      it("should return true for java.lang.Byte and byte") {
        val expected = true

        val t1 = "java.lang.Byte"
        val t2 = "byte"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }

      it("should return true for java.lang.Char and char") {
        val expected = true

        val t1 = "java.lang.Char"
        val t2 = "char"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }

      it("should return true for java.lang.Integer and int") {
        val expected = true

        val t1 = "java.lang.Integer"
        val t2 = "int"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }

      it("should return true for java.lang.Short and short") {
        val expected = true

        val t1 = "java.lang.Short"
        val t2 = "short"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }

      it("should return true for java.lang.Long and long") {
        val expected = true

        val t1 = "java.lang.Long"
        val t2 = "long"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }

      it("should return true for java.lang.Float and float") {
        val expected = true

        val t1 = "java.lang.Float"
        val t2 = "float"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }

      it("should return true for java.lang.Double and double") {
        val expected = true

        val t1 = "java.lang.Double"
        val t2 = "double"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }

      it("should return false when not string equal or primitive equivalent") {
        val expected = false

        val t1 = "some.type"
        val t2 = "some.other.type"

        val actual = javaTypeCheckerProfile.equalTypeNames(t1, t2)

        actual should be (expected)
      }
    }
  }
}
