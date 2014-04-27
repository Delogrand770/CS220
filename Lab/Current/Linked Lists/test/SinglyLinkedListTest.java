/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**

 @author David.Gibson
 */
public class SinglyLinkedListTest {

  public SinglyLinkedListTest() {
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
   Test of isEmpty method, of class SinglyLinkedList.
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
   Test of size method, of class SinglyLinkedList.
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
   Test of contains method, of class SinglyLinkedList.
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
   Test of addToFront method, of class SinglyLinkedList.
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
   Test of toString method, of class SinglyLinkedList.
   */
  @Test
  public void testToString() {
    System.out.println("toString");
    SinglyLinkedList<Integer> instance = new SinglyLinkedList<Integer>();
    assertEquals(instance.toString(), "[]");
    instance.addToFront(7);
    assertEquals(instance.toString(), "[7]");
    instance.addToFront(11);
    assertEquals(instance.toString(), "[11, 7]");
    instance.addToFront(21);
    assertEquals(instance.toString(), "[21, 11, 7]");
  }

  /**
   Test of append method, of class SinglyLinkedList.
   */
  @Test
  public void testAppend() {
    System.out.println("append");
    SinglyLinkedList<Character> instance = new SinglyLinkedList<Character>();
    instance.append('a');
    assertEquals(instance.toString(), "[a]");
    instance.append('b');
    assertEquals(instance.toString(), "[a, b]");
    instance.append('c');
    assertEquals(instance.toString(), "[a, b, c]");
  }

  /**
   Test of removeFirst method, of class SinglyLinkedList.
   */
  @Test
  public void testRemoveFirst() {
    System.out.println("removeFirst");
    SinglyLinkedList<Character> instance = new SinglyLinkedList<Character>();
    instance.removeFirst('a');
    assertEquals(instance.toString(), "[]");
    instance.addToFront('a');
    assertEquals(instance.toString(), "[a]");
    instance.removeFirst('a');
    assertEquals(instance.toString(), "[]");
    instance.addToFront('a');
    instance.addToFront('c');
    instance.addToFront('a');
    instance.addToFront('b');
    instance.addToFront('a');
    instance.removeFirst('d');
    assertEquals(instance.toString(), "[a, b, a, c, a]");
    instance.removeFirst('a');
    assertEquals(instance.toString(), "[b, a, c, a]");
    instance.removeFirst('a');
    assertEquals(instance.toString(), "[b, c, a]");
    instance.removeFirst('a');
    assertEquals(instance.toString(), "[b, c]");
    instance.removeFirst('a');
    assertEquals(instance.toString(), "[b, c]");
    instance.removeFirst('c');
    assertEquals(instance.toString(), "[b]");
    instance.removeFirst('b');
    assertEquals(instance.toString(), "[]");
    instance.removeFirst('b');
    assertEquals(instance.toString(), "[]");
  }

  /**
   Test of removeAll method, of class SinglyLinkedList.
   */
  @Test
  public void testRemoveAll() {
    System.out.println("removeAll");
    SinglyLinkedList<Character> instance = new SinglyLinkedList<Character>();
    int numRemoved = instance.removeAll('a');
    assertEquals(instance.toString(), "[]");
    assertEquals(numRemoved, 0);
    instance.addToFront('a');
    instance.addToFront('c');
    instance.addToFront('a');
    instance.addToFront('b');
    instance.addToFront('a');
    assertEquals(instance.toString(), "[a, b, a, c, a]");
    numRemoved = instance.removeAll('a');
    assertEquals(instance.toString(), "[b, c]");
    assertEquals(numRemoved, 3);
    numRemoved = instance.removeAll('a');
    assertEquals(instance.toString(), "[b, c]");
    assertEquals(numRemoved, 0);
  }
}
