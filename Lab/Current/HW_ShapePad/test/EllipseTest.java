/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Point;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author C14Gavin.Delphia
 */
public class EllipseTest {

  public EllipseTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }

  @Before
  public void setUp() {
  }

  @After
  public void tearDown() {
  }

  /**
   * Test of area method, of class Ellipse.
   */
  @Test
  public void testArea() {
    System.out.println("area");
    for (int i = 0; i <= 5; i++) {
      for (int j = 0; j <= 5; j++) {
        Ellipse instance = new Ellipse(i, j);
        double expResult = Math.PI * instance.horizontalAxis / 2 * instance.verticalAxis / 2;
        double result = instance.area();
        System.out.println(instance + ", AREA=" + result);
        assertEquals(expResult, result, 0.0);
      }
    }
  }

  /**
   * Test of contains method, of class Ellipse.
   */
  @Test
  public void testContains() {
    System.out.println("contains");
    int[][] shouldPass = {{0, 2}, {0, 3}, {0, 1}, {3, 0}, {2, 0}, {1, 0}, {3, 3}};
    for (int i = 0; i < shouldPass.length; i++) {
      Point p = new Point(shouldPass[i][0], shouldPass[i][1]);
      Ellipse instance = new Ellipse(8, 10);
      boolean expResult = true;
      boolean result = instance.contains(p);
      System.out.println(p + ", " + result);
      assertEquals(expResult, result);
    }

    int[][] shouldFail = {{10, 20}, {4, 5}, {5, 4}, {0, 8}, {10, 0}, {8, 10}};
    for (int i = 0; i < shouldFail.length; i++) {
      Point p = new Point(shouldFail[i][0], shouldFail[i][1]);
      Ellipse instance = new Ellipse(8, 10);
      boolean expResult = false;
      boolean result = instance.contains(p);
      System.out.println(p + ", " + result);
      assertEquals(expResult, result);
    }
  }
}
