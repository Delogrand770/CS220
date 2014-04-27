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
public class TriangleTest {

  public TriangleTest() {
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
   * Test of area method, of class Triangle.
   */
  @Test
  public void testArea() {
    System.out.println("area");
    //x1, y1, x2, y2, x3, y3, area
    double[][] shouldPass = {{0, 2, 3, 4, 5, 6, 1.0},
                             {1, 7, 9, 5, 10, 10, 21.0},
                             {5, 5, 7, 7, 8, 4, 4.0}};
    for (int i = 0; i < shouldPass.length; i++) {
      Triangle instance = new Triangle(new Point((int) shouldPass[i][0], (int) shouldPass[i][1]),
                                       new Point((int) shouldPass[i][2], (int) shouldPass[i][3]),
                                       new Point((int) shouldPass[i][4], (int) shouldPass[i][5]));
      double expResult = shouldPass[i][6];
      double result = instance.area();
      System.out.println(instance + ", " + result);
      assertEquals(expResult, result, 0.0);
    }
  }

  /**
   * Test of contains method, of class Triangle.
   */
  @Test
  public void testContains() {
    System.out.println("contains");
    int[][] shouldPass = {{8, 4}, {7, 1}, {5, 5}, {7, 3}};
    for (int i = 0; i < shouldPass.length; i++) {
      Point p = new Point(shouldPass[i][0], shouldPass[i][1]);
      Triangle instance = new Triangle(new Point(5, 5), new Point(8, 4), new Point(7, 1));
      boolean expResult = true;
      boolean result = instance.contains(p);
      System.out.println(p + ", " + result);
      assertEquals(expResult, result);
    }

    int[][] shouldFail = {{6, 0}, {9, 5}, {4, 3}, {6, 1}};
    for (int i = 0; i < shouldFail.length; i++) {
      Point p = new Point(shouldFail[i][0], shouldFail[i][1]);
      Triangle instance = new Triangle(new Point(5, 5), new Point(8, 4), new Point(7, 1));
      boolean expResult = false;
      boolean result = instance.contains(p);
      System.out.println(p + ", " + result);
      assertEquals(expResult, result);
    }
  }
}
