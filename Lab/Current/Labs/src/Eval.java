
import java.util.Scanner;

/**
 Description: This class contains several static methods that can
 evaluate an infix expression by first converting it to postfix and then
 evaluating the postfix expression. Both algorithms illustrate the use
 of a Stack.

 @author Randall.Bower
 */
public class Eval {

  /**
   This method evaluates a string containing a mathematical expression
   in postfix notation.

   @param postfix The expression in postfix notation.
   @return A double with the result of evaluating the expression.
   @throws RuntimeException If the string is not a valid postfix
   expression.
   */
  public static Double evalPostfix(String postfix) throws RuntimeException {
    Scanner scan = new Scanner(postfix);
    MyStack<Double> stack = new MyStack();

    while (scan.hasNext()) {
      if (scan.hasNextDouble()) {
        stack.push(scan.nextDouble());

      } else {
        Double x = stack.pop();
        Double y = stack.pop();

        switch (scan.next().charAt(0)) {
          case '+':
            stack.push(y + x);
            break;
          case '-':
            stack.push(y - x);
            break;
          case '*':
            stack.push(y * x);
            break;
          case '/':
            stack.push(y / x);
            break;
          case '%':
            stack.push(y % x);
            break;
          case '^':
            stack.push(Math.pow(y, x));
            break;
        }
      }
    }
    return stack.pop();
  }

  /**
   This methods translates a string containing a mathematical expression
   in infix notation into postfix notation.

   @param infix The mathematical expression in infix notation.
   @return A string with the same mathematical expression in postfix
   notation.
   @throws RuntimeException If the string is not a valid infix
   expression.
   */
  public static String toPostfix(String infix) throws RuntimeException {
    String postfix = "";
    MyStack<String> stack = new MyStack();
    stack.push("[");
    infix += " )";
    Scanner scan = new Scanner(infix);

    while (scan.hasNext()) {
      String token = scan.next();

      if (token.equals("(")) {
        stack.push(token);

      } else if (isOperator(token)) {
        int precedence = precedence(token);
        String peeked = stack.peek();

        while (isOperator(peeked) && (precedence(peeked) >= precedence)) {
          postfix += stack.pop() + " ";
          peeked = stack.peek();
        }
        stack.push(token);

      } else if (token.equals(")")) {
        String peeked = stack.peek();

        while (isOperator(peeked)) {
          postfix += stack.pop() + " ";
          peeked = stack.peek();
        }
        stack.pop();

      } else {
        postfix += token + " ";
      }
    }
    
    return postfix.trim();
  }

  /**
   This method determines if a string contains a mathematical operator
   representing addition, subtraction, multiplication, division,
   modulus, or exponentiation.

   @param token The string to test.
   @return True if the string is a valid operator; false otherwise.
   */
  private static boolean isOperator(String token) {
    return precedence(token) != 0;
//    return token.equals("+") || token.equals("-")
//           || token.equals("*") || token.equals("/")
//           || token.equals("%") || token.equals("^");
  }

  /**
   This method returns a numeric value to represent the precedence level
   of a given mathematical operator. Addition and subtraction will have
   a lower precedence than multiplication, division, and modulus, which
   in turn have a lower precedence than exponentiation.

   @param token The string containing the operator.
   @return The precedence level of the operator.
   */
  private static int precedence(String token) {
    switch (token.charAt(0)) {
      case '+': // Fall through.
      case '-':
        return 1;
      case '*': // Fall through.
      case '/': // Fall through.
      case '%':
        return 2;
      case '^':
        return 3;
      default:
        return 0; // Should not happen!
    }
  }

  /**
   This methods converts a string containing an infix expression to
   postfix and then evaluates the postfix expression, returning the
   result. The infix expression must contain spaces to delimit the
   numbers and operators.

   @param expression String containing the infix expression.
   @return The result of evaluating the expression.
   */
  public static Double evaluate(String expression) {
    return evalPostfix(toPostfix(expression));
  }
}
