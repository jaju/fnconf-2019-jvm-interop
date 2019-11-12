package co.gywb.demo.web_front.scala

import scala.util.parsing.combinator._

class InfixCalculator extends JavaTokenParsers {

  type BinOp = (Double, Double) => Double

  def plus: Parser[BinOp] = "+" ^^ { _ => _ + _ }

  def minus: Parser[BinOp] = "-" ^^ { _ => _ - _ }

  def times: Parser[BinOp] = """(x|X|\*)""".r ^^ { _ => _ * _ }

  def divides: Parser[BinOp] = "/" ^^ { _ => _ / _ }

  def multiplyTerm: Parser[Double] = {
    chainl1(divideTerm | number, times) ^^ { answer => answer }
  }

  def divideTerm: Parser[Double] = {
    chainl1(number, divides) ^^ { answer => answer }
  }

  def number: Parser[Double] = floatingPointNumber ^^ {
    _.toDouble
  }

  def factor: Parser[Double] = divideTerm ||| multiplyTerm | number

  def expr: Parser[Double] = {
    chainl1(factor, plus | minus) ^^ { answer => answer }
  }

  def apply(input: String) = parseAll(expr, input) match {
    case NoSuccess(msg, _) => throw new Exception(msg)
    case Success(result, _) => result
  }
}
