
import static org.junit.Assert.*;
import org.junit.Test;

/**
 Tests of the ExpressionTree class.

 @author C3C Jared Peterson
 @author C3C Gavin Delphia
 @author Randall.Bower

 Documentation: C3C Jared Peterson and C3C Gavin Delphia completed this
 assignment together without help from any outside sources except EI with Dr. Bower.
 */
public class ExpressionTreeTest {

  private static double TOLERANCE = 0.0001;
  String[] expressions = {"( 5 * 5 ) * 5",
                          "( 6 + 2 ) * 5 - 8 / 4",
                          "2 - ( 4 + 6 * 8 ) / 10",
                          "2 / 4 * ( 6 - 8 - 10 )",
                          "2 + ( 4 - 6 ) * 8 / 10",
                          "2 + 4 * 6 - 8 / 10",
                          "2 + 4 - 6 * ( 8 / 10 )",
                          "2 * 4 + 6 / 8 - 10",
                          "2 - 4 / 6 * 8 + 10",
                          "2 / 4 + 6 - 8 / 10",
                          "2 + 4 * ( 6 - 8 ) / 10",
                          "6 - 3 - 2",
                          "6 - ( 3 - 2 )",
                          "2 + 4 - 6",
                          "2 - 4 + 6",
                          "2 * 4 / 6 / 8 / 10",
                          "2 / 4 * 6 * 8 * 10",
                          "2 + 4 - 6 - 8 + 10",
                          "1"};
  String[] inorders = {"5 * 5 * 5",
                       "6 + 2 * 5 - 8 / 4",
                       "2 - 4 + 6 * 8 / 10",
                       "2 / 4 * 6 - 8 - 10",
                       "2 + 4 - 6 * 8 / 10",
                       "2 + 4 * 6 - 8 / 10",
                       "2 + 4 - 6 * 8 / 10",
                       "2 * 4 + 6 / 8 - 10",
                       "2 - 4 / 6 * 8 + 10",
                       "2 / 4 + 6 - 8 / 10",
                       "2 + 4 * 6 - 8 / 10",
                       "6 - 3 - 2",
                       "6 - 3 - 2",
                       "2 + 4 - 6",
                       "2 - 4 + 6",
                       "2 * 4 / 6 / 8 / 10",
                       "2 / 4 * 6 * 8 * 10",
                       "2 + 4 - 6 - 8 + 10",
                       "1"};
  String[] preorders = {"* * 5 5 5",
                        "- * + 6 2 5 / 8 4",
                        "- 2 / + 4 * 6 8 10",
                        "* / 2 4 - - 6 8 10",
                        "+ 2 / * - 4 6 8 10",
                        "- + 2 * 4 6 / 8 10",
                        "- + 2 4 * 6 / 8 10",
                        "- + * 2 4 / 6 8 10",
                        "+ - 2 * / 4 6 8 10",
                        "- + / 2 4 6 / 8 10",
                        "+ 2 / * 4 - 6 8 10",
                        "- - 6 3 2",
                        "- 6 - 3 2",
                        "- + 2 4 6",
                        "+ - 2 4 6",
                        "/ / / * 2 4 6 8 10",
                        "* * * / 2 4 6 8 10",
                        "+ - - + 2 4 6 8 10",
                        "1"};
  String[] postorders = {"5 5 * 5 *",
                         "6 2 + 5 * 8 4 / -",
                         "2 4 6 8 * + 10 / -",
                         "2 4 / 6 8 - 10 - *",
                         "2 4 6 - 8 * 10 / +",
                         "2 4 6 * + 8 10 / -",
                         "2 4 + 6 8 10 / * -",
                         "2 4 * 6 8 / + 10 -",
                         "2 4 6 / 8 * - 10 +",
                         "2 4 / 6 + 8 10 / -",
                         "2 4 6 8 - * 10 / +",
                         "6 3 - 2 -",
                         "6 3 2 - -",
                         "2 4 + 6 -",
                         "2 4 - 6 +",
                         "2 4 * 6 / 8 / 10 /",
                         "2 4 / 6 * 8 * 10 *",
                         "2 4 + 6 - 8 - 10 +",
                         "1"};
  double[] evaluates = {125.0,
                        38.0,
                        -3.2,
                        -6.0,
                        0.3999,
                        25.2,
                        1.1999,
                        -1.25,
                        6.6666,
                        5.7,
                        1.2,
                        1.0,
                        5.0,
                        0.0,
                        4.0,
                        0.01666,
                        240.0,
                        2.0,
                        1.0};

  /**
   Test an expression that includes all four operators and parentheses.
   */
  @Test
  //@Ignore
  public void test1() {
    for (int i = 0; i < expressions.length; i++) {
      ExpressionTree tree = new ExpressionTree(expressions[i]);

      // Check the value
      assertEquals(evaluates[i], tree.evaluate(), TOLERANCE);

      // Check in-order, pre-order, and post-order traversals of the tree.
      assertEquals(inorders[i], tree.inOrder());
      assertEquals(preorders[i], tree.preOrder());
      assertEquals(postorders[i], tree.postOrder());
    }
  }

  /**
   Test for an invalid expression
   */
  @Test
  //@Ignore
  public void testException2() {
    try {
      System.out.println(ExpressionConverter.toPrefix("2 4 + 8"));
      fail("no exception thrown");
    } catch (Exception e) {
      System.out.println(e.getClass().getName() + e.getMessage());
    }

    try {
      System.out.println(ExpressionConverter.toPrefix("* 2 4 6 - -"));
      fail("no exception thrown");
    } catch (Exception e) {
      System.out.println(e.getClass().getName() + e.getMessage());
    }

    try {
      System.out.println(ExpressionConverter.toPrefix("( + ) - *"));
      fail("no exception thrown");
    } catch (Exception e) {
      System.out.println(e.getClass().getName() + e.getMessage());
    }

    try {
      System.out.println(ExpressionConverter.toPrefix("( 2 + 4 + ( 1 - 2 )"));
      fail("no exception thrown");
    } catch (Exception e) {
      System.out.println(e.getClass().getName() + e.getMessage());
    }

    try {
      System.out.println(ExpressionConverter.toPrefix(") 9 + 8 * 2"));
      fail("no exception thrown");
    } catch (Exception e) {
      System.out.println(e.getClass().getName() + e.getMessage());
    }

  }
}
