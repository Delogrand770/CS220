
import java.util.Scanner;
import java.util.Stack;

/**
 This file contains static methods to convert infix expressions to
 prefix and postfix expressions and a method to evaluate a postfix
 expression.

 The mathematical expressions can contain Double values, the four binary
 operators for addition, subtraction, multiplication, and division, and
 parentheses for specifying operator precedence. Unary negation of a
 number is supported as long as there is no space between the minus sign
 and the number (i.e., it is parsed by the scanner as a double). Unary
 negation before a parenthesis is not supported.

 Expression trees are created from strings in infix notation in the
 format "( 6 + 2 ) * 5 - 8 / 4" where whitespace separates each number,
 operator, and parenthesis.

 @author Randall.Bower
 @author Jared.Peterson
 @author Gavin.Delphia

 Documentation: C3C Jared Peterson and C3C Gavin Delphia completed this
 assignment together without help from any outside sources except EI with Dr. Bower.
 */
public class ExpressionConverter {

  /**
   This method converts a string containing a mathematical expression in
   infix notation to prefix notation. The tokens in the infix expression
   must be delimited by whitespace.

   @param expression The infix expression to be converted.
   @return A string containing the prefix expression.
   @throws IllegalArgumentException If invalid infix expression
   encountered.
   */
  public static String toPrefix(String expression) throws IllegalArgumentException {
    Scanner infix = new Scanner(expression);
    Stack<String> stack_op = new Stack();
    Stack<String> stack_num = new Stack();

    while (infix.hasNext()) {

      String token = infix.next();
      if (token.equalsIgnoreCase("(")) {
        stack_op.push("(");

      } else if (token.equalsIgnoreCase(")")) {
        while (isOperator(stack_op.peek())) {
          if (stack_num.size() < 2) {
            throw new RuntimeException("Missing numbers");
          }
          if (stack_op.size() < 1) {
            throw new RuntimeException("Missing operator");
          }
          String x = stack_num.pop();
          String y = stack_num.pop();
          String op = stack_op.pop();
          stack_num.push(op + " " + y + " " + x);

        }
        if (stack_op.isEmpty()) {
          throw new RuntimeException("Missing left parenthesis");
        }
        stack_op.pop();

      } else if (isOperator(token)) {
        if (stack_op.isEmpty() || stack_op.peek().equalsIgnoreCase("(")) {
          stack_op.push(token);

        } else {
          while (!stack_op.isEmpty() && isOperator(stack_op.peek()) && precedence(token) <= precedence(stack_op.peek())) {
            if (stack_num.size() < 2) {
              throw new RuntimeException("Missing numbers");
            }
            if (stack_op.size() < 1) {
              throw new RuntimeException("Missing operator");
            }
            String x = stack_num.pop();
            String y = stack_num.pop();
            String op = stack_op.pop();
            stack_num.push(op + " " + y + " " + x);

          }
          stack_op.push(token);

        }
      } else {
        stack_num.push(token);

      }
    }

    while (!stack_op.isEmpty()) {
      String x = stack_num.pop();
      String y = stack_num.pop();
      String op = stack_op.pop();
      stack_num.push(op + " " + y + " " + x);

    }
    if (stack_num.size() > 1) {
      throw new RuntimeException("Missing operator");
    }
    return stack_num.pop();

  }

  /**
   This method converts a string containing a mathematical expression in
   infix notation to postfix notation. The tokens in the infix
   expression must be delimited by whitespace.

   @param expression The infix expression to be converted.
   @return A string containing the postfix expression.
   @throws IllegalArgumentException If invalid infix expression
   encountered.
   */
  public static String toPostfix(String expression) throws IllegalArgumentException {
    // For inline comments, see pseudocode algorithm from Lesson 20.
    String token, postfix = "";
    Scanner infix = new Scanner(expression + " )");
    Stack<String> stack = new Stack();
    stack.push("(");
    while (!stack.isEmpty()) {
      if (infix.hasNext()) {
        token = infix.next();
      } else {
        throw new IllegalArgumentException("Unexpected end of infix expression.");
      }

      if (token.equals("(")) {
        stack.push(token);
      } else if (isOperator(token)) {
        while (isOperator(stack.peek())
               && precedence(stack.peek()) >= precedence(token)) {
          postfix += " " + stack.pop();
        }
        stack.push(token);
      } else if (token.equals(")")) {
        while (isOperator(stack.peek())) {
          postfix += " " + stack.pop();
        }
        stack.pop();
      } else {
        postfix += " " + token;
      }

    }

    if (infix.hasNext()) {
      throw new IllegalArgumentException("Unexpected input on end of infix expression: " + infix.nextLine());
    }
    return postfix.trim();
  }

  /**
   This method evaluates a string containing a mathematical expression
   in postfix notation with each token delimited by whitespace.

   @param expression The postfix expression.
   @return The result of evaluating the expression, as a Double.
   @throws IllegalArgumentException If an invalid operator is
   encountered.
   */
  public static Double evalPostfix(String expression) throws IllegalArgumentException {
    // For inline comments, see pseudocode algorithm from Lesson 20.
    Scanner postfix = new Scanner(expression);
    Stack<Double> stack = new Stack();
    while (postfix.hasNext()) {
      if (postfix.hasNextDouble()) {
        stack.push(postfix.nextDouble());
      } else {
        Double x = stack.pop();
        Double y = stack.pop();
        String op = postfix.next();
        switch (op) {
          case "+":
            stack.push(y + x);
            break;
          case "-":
            stack.push(y - x);
            break;
          case "*":
            stack.push(y * x);
            break;
          case "/":
            stack.push(y / x);
            break;
          default:
            throw new IllegalArgumentException("Invalid operator: " + op);
        }
      }
    }
    return stack.pop();
  }

  /**
   Private method to determine if a token is an operator.

   @param token The token to be tested.
   @return True if the token is an operator; false otherwise.
   */
  public static boolean isOperator(String token) {
    return token.length() == 1 && "+-*/".contains(token);
  }

  /**
   Private method to determine the precedence level of an operator.
   Specific values returned are arbitrary as long as addition and
   subtraction values are less than multiplication and division.

   @param token Token holding an operator.
   @return Precedence value of that operator.
   */
  private static int precedence(String token) throws IllegalArgumentException {
    if (!isOperator(token)) {
      throw new IllegalArgumentException("Invalid operator: " + token);
    }

    switch (token) {
      case "+": // Fall through.
      case "-":
        return 1;
      case "*": // Fall through.
      case "/":
        return 2;
      default:
        return 0; // Should not happen!
    }
  }
}
