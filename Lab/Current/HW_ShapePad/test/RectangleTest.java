/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Color;
import java.awt.Point;
import org.junit.*;
import static org.junit.Assert.*;

/**
 *
 * @author C14Gavin.Delphia
 */
public class RectangleTest {

  public RectangleTest() {
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
   * Test of area method, of class Rectangle.
   */
  @Test
  public void testArea() {
    System.out.println("----area");
    for (int i = 0; i <= 10; i++) {
      for (int j = 0; j <= 10; j++) {
        Rectangle instance = new Rectangle(i, j);
        double expResult = i * j;
        double result = instance.area();
        System.out.println(instance + " has an area of " + result);
        assertEquals(expResult, result, 0);
      }
    }
  }

  /**
   * Test of contains method, of class Rectangle.
   */
  @Test
  public void testContains() {
    System.out.println("----contains");
    Point cp = new Point(3, 3);
    Rectangle instance = new Rectangle(Color.red, cp, 5, 5);
    System.out.println(instance);

    //known pass
    for (int i = cp.x - instance.width / 2; i <= cp.x + instance.width / 2; i++) {
      for (int j = cp.y - instance.height / 2; j <= cp.y + instance.height / 2; j++) {
        Point p = new Point(i, j);
        boolean expResult = true;
        boolean result = instance.contains(p);
        System.out.println("Point" + p + " " + result);
        assertEquals(expResult, result);
      }
    }
    //known fail
    for (int i = cp.x - instance.width / 2 - 5; i < cp.x - instance.width / 2; i++) {
      for (int j = cp.y - instance.height / 2 - 5; j < cp.y - instance.height / 2; j++) {
        Point p = new Point(i, j);
        boolean expResult = false;
        boolean result = instance.contains(p);
        System.out.println("Point" + p + " " + result);
        assertEquals(expResult, result);
      }
    }
    for (int i = cp.x + instance.width / 2 + 1; i < cp.x + instance.width / 2 + 5; i++) {
      for (int j = cp.y + instance.height / 2 + 1; j < cp.y + instance.height / 2 + 5; j++) {
        Point p = new Point(i, j);
        boolean expResult = false;
        boolean result = instance.contains(p);
        System.out.println("Point" + p + " " + result);
        assertEquals(expResult, result);
      }
    }

  }
}
