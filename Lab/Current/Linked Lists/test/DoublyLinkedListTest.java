/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.*;
import static org.junit.Assert.*;

/**

 @author C14Gavin.Delphia
 */
public class DoublyLinkedListTest {
  
  public DoublyLinkedListTest() {
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
   * Test of isEmpty method, of class DoublyLinkedList.
   */
  @Test
  public void testIsEmpty() {
    System.out.println("isEmpty");
    SinglyLinkedList<Integer> instance = new SinglyLinkedList<Integer>();
    assertTrue(instance.isEmpty());
    instance.addToFront(1);
    assertFalse(instance.isEmpty());
  }

  /**
   * Test of size method, of class DoublyLinkedList.
   */
  @Test
  public void testSize() {
    System.out.println("size");
    SinglyLinkedList<Character> instance = new SinglyLinkedList<Character>();
    assertEquals(instance.size(), 0);
    instance.addToFront('a');
    assertEquals(instance.size(), 1);
    instance.addToFront('b');
    assertEquals(instance.size(), 2);
  }

  /**
   * Test of contains method, of class DoublyLinkedList.
   */
  @Test
  public void testContains() {
    System.out.println("contains");
    SinglyLinkedList<Integer> instance = new SinglyLinkedList<Integer>();
    assertFalse(instance.contains(1));
    instance.addToFront(2);
    assertFalse(instance.contains(1));
    assertTrue(instance.contains(2));
    instance.addToFront(3);
    assertFalse(instance.contains(1));
    assertTrue(instance.contains(2));
    assertTrue(instance.contains(3));
  }

  /**
   * Test of addToFront method, of class DoublyLinkedList.
   */
  @Test
  public void testAddToFront() {
    System.out.println("addToFront");
    SinglyLinkedList<Character> instance = new SinglyLinkedList<Character>();
    instance.addToFront('a');
    assertEquals(instance.toString(), "[a]");
    instance.addToFront('b');
    assertEquals(instance.toString(), "[b, a]");
    instance.addToFront('c');
    assertEquals(instance.toString(), "[c, b, a]");
  }

  /**
   * Test of toString method, of class DoublyLinkedList.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    DoublyLinkedList instance = new DoublyLinkedList();
    String expResult = "";
    String result = instance.toString();
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of append method, of class DoublyLinkedList.
   */
  @Test
  public void testAppend() {
    System.out.println("append");
    Object newItem = null;
    DoublyLinkedList instance = new DoublyLinkedList();
    instance.append(newItem);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of removeFirst method, of class DoublyLinkedList.
   */
  @Test
  public void testRemoveFirst() {
    System.out.println("removeFirst");
    Object itemToRemove = null;
    DoublyLinkedList instance = new DoublyLinkedList();
    boolean expResult = false;
    boolean result = instance.removeFirst(itemToRemove);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of removeAll method, of class DoublyLinkedList.
   */
  @Test
  public void testRemoveAll() {
    System.out.println("removeAll");
    Object itemToRemove = null;
    DoublyLinkedList instance = new DoublyLinkedList();
    int expResult = 0;
    int result = instance.removeAll(itemToRemove);
    assertEquals(expResult, result);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }
}
