
import java.text.NumberFormat;
import java.util.Scanner;

/**
 Description: This class represents a mathematical expression as a tree.
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
public class ExpressionTree {

  /**
   Private class to represent nodes in the expression tree.

   Note: One of the two fields, value or operator, will be null in every
   node. Leaf nodes will contain only values, so the operator (and
   child) attributes will be null. Interior nodes contain operators, so
   the value will be null.
   */
  private class Node {

    private Double value; // This field is only used when the node is a leaf.
    private Character operator; // This field is used for binary operator nodes.
    private Node left;  // Left child.
    private Node right; // Right child.

    /**
     Constructs a leaf node with the given Double value.

     @param value The value to be placed in this node.
     */
    public Node(Double value) {
      this.value = value;
      this.operator = null;
      this.left = null;
      this.right = null;
    }

    /**
     Constructs a binary operator node.

     @param operator The operation to be performed when this node is
     evaluated.
     @param left Left child expression.
     @param right Right child expression.
     */
    public Node(Character operator, Node left, Node right) {
      this.value = null;
      this.operator = operator;
      this.left = left;
      this.right = right;
    }
  }
  private Node root; // The root of the expression tree.
  NumberFormat nf; // Formats the number to take off the unneeded decimal places

  /**
   Converts the given infix expression to prefix and builds the
   ExpressionTree from the prefix expression.

   @param infix The infix expression to be parsed.
   */
  public ExpressionTree(String infix) {
    nf = NumberFormat.getInstance(); //get the correct number format
    Scanner expression = new Scanner(ExpressionConverter.toPrefix(infix));
    root = expressionCreator(expression);
  }

  /**
   Private recursive method to turn the scanner with the prefix
   expression into the tree structure.

   @param scan a scanner with the prefix expression
   @return a node
   */
  private Node expressionCreator(Scanner scan) {
    if (scan.hasNextDouble()) {
      return new Node(scan.nextDouble());
    } else {
      Character operator = scan.next().charAt(0);
      Node left = expressionCreator(scan);
      Node right = expressionCreator(scan);
      return new Node(operator, left, right);
    }
  }

  /**
   Calculates and returns the value of the expression stored in this
   tree.

   @return The value of the expression stored in this tree.
   */
  public Double evaluate() {
    return evaluate(this.root);
  }

  /**
   Private recursive method for traversing the tree and calculating its
   value

   @param curr the current node
   @return
   */
  private Double evaluate(Node curr) {
    if (curr != null) {
      if (curr.value != null) {
        return curr.value;
      } else {
        String operator = curr.operator + "";
        Double number1 = evaluate(curr.left);
        Double number2 = evaluate(curr.right);

        //Determine what operator to apply to the two numbers
        if (operator.equalsIgnoreCase("+")) {
          return number1 + number2;
        } else if (operator.equalsIgnoreCase("-")) {
          return number1 - number2;
        } else if (operator.equalsIgnoreCase("*")) {
          return number1 * number2;
        } else if (operator.equalsIgnoreCase("/")) {
          return number1 / number2;
        }
      }
    }
    return 0.0;
  }

  /**
   Creates and returns a string with an in-order traversal of this
   expression tree.

   @return An in-order traversal of this expression tree.
   */
  public String inOrder() {
    return this.toString(this.root).trim();
  }

  /**
   Creates and returns a string with a pre-order traversal of this
   expression tree.

   @return A pre-order traversal of this expression tree.
   */
  public String preOrder() {
    return this.preOrder(this.root).trim();
  }

  /**
   Recursive tree traversal that generates the pre order

   @param curr the current node
   @return the pre-order string
   */
  private String preOrder(Node curr) {
    if (curr != null) {
      String value = (curr.value != null) ? nf.format(curr.value) + "" : curr.operator + "";
      return value + " " + preOrder(curr.left) + preOrder(curr.right);
    }
    return "";
  }

  /**
   Creates and returns a string with a post-order traversal of this
   expression tree.

   @return A post-order traversal of this expression tree.
   */
  public String postOrder() {
    return this.postOrder(this.root).trim();
  }

  /**
   Recursive traversal of the tree to determine the post order.

   @param curr the current node
   @return the postorder string
   */
  private String postOrder(Node curr) {
    if (curr != null) {
      String value = (curr.value != null) ? nf.format(curr.value) + "" : curr.operator + "";
      return postOrder(curr.left) + postOrder(curr.right) + value + " ";
    }
    return "";
  }

  /**
   Creates a string representation of this expression tree, which is an
   in-order traversal of the tree.

   Extra Credit: Re-create the proper parenthesization!!!

   @return A string representation of this expression tree.
   */
  @Override
  public String toString() {
    return toString(this.root).trim();
  }

  /**
   Recursively traverses the tree and generates the in-order to string

   @param curr the current node
   @return the in order string of the tree
   */
  private String toString(Node curr) {
    if (curr != null) {
      String value = (curr.value != null) ? nf.format(curr.value) + "" : curr.operator + "";
      return toString(curr.left) + value + " " + toString(curr.right);
    }
    return "";
  }
}
