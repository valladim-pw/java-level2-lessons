package B_Webinars.A4_Calculator.calc2;

public class CalculatorValery {
	String expr;
  int pos;

  boolean hasNext() {
    return pos < expr.length();
  }

  String getSym() throws Exception {
    if (!hasNext())
      throw new Exception("unexpected end");
    return expr.substring(pos, ++pos);
  }

  String checkSym() throws Exception {
    if (!hasNext())
      throw new Exception("unexpected end");
    return expr.substring(pos,pos+1);
  }

  int getNum() throws Exception {
    return Integer.parseInt(getSym());
  }

  String getOperation() throws Exception {
    return getSym();
  }

  String checkOperation() throws Exception {
    return checkSym();
  }

  public CalculatorValery(String expr) {
    this.expr = expr;
    pos = 0;
  }

  int term2() throws Exception {
    if (checkSym().equals("(")) {
      getSym();
      int num = expression();
      if (!getSym().equals(")"))
        throw new Exception(") expected");
      return num;
    }
    else
      return getNum();
  }

  int term() throws Exception {
    int result = term2();
    while (hasNext()) {
      String op = checkOperation();
      if ("*/".contains(op)) {
        getOperation();
        int num = term2();
        switch (op) {
          case "*":
            result *= num;
            break;
          case "/":
            result /= num;
            break;
        }
      }
      else
        return result;
    }
    return result;
  }

  int expression() throws Exception {
    int result = term();
    while (hasNext()) {
      String op = checkOperation();
      if ("+-".contains(op)) {
        getOperation();
        int num = term();
        switch (op) {
          case "+":
            result += num;
            break;
          case "-":
            result -= num;
            break;
        }
      }
      else
        return result;
    }
    return result;
  }

  // expression ::= number { (+-*/) number }

  // expression ::= term ("+"|"-") term
  // term ::= num ("*"|"/") num

  // expression ::= term ("+"|"-") term
  // term ::= temr2 ("*"|"/") term2
  // term2 ::= "(" expression ")" | number

  static int calculate(String expr)  throws Exception {
    return new CalculatorValery(expr).expression();
  }

  public static void main(String[] args) throws Exception {
    System.out.println(calculate("2+2*2"));
  }
}
