
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**


 @author Randall.Bower
 */
public class EvalTest {

  private static double TOLERANCE = 0.0001;

  @Test
  public void testToPostfix() {
    System.out.println("ToPostfix");
    assertEquals(Eval.toPostfix("( 6 + 2 ) * 5 - 8 / 4"), "6 2 + 5 * 8 4 / -");
  }

  @Test
  public void testEvalPostfix() {
    System.out.println("EvalPostfix");
    assertEquals(Eval.evalPostfix("6 2 + 5 * 8 4 / - "), 38.0, TOLERANCE);
  }

  @Test
  public void testEvaluate() {
    System.out.println("Evaluate");
    assertEquals(Eval.evaluate("( 6 + 2 ) * 5 - 8 / 4"), 38.0, TOLERANCE);
  }
}
