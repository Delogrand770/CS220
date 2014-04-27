
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Description: Tests of various recursive examples.
 * 
 * @author Randall.Bower
 */
public class RecursionTest {

  /**
   * Test of factorial method, of class Recursion.
   */
  @Test
  public void testFactorial() {
    System.out.println("factorial");
    assertEquals(Recursion.factorial(0), 1);
    assertEquals(Recursion.factorial(1), 1);
    assertEquals(Recursion.factorial(5), 120);
    assertEquals(Recursion.factorial(10), 3628800);
  }

  /**
   * Test of getBinary method, of class Recursion.
   */
  @Test
  public void testGetBinary() {
    System.out.println("getBinary");
    assertEquals(Recursion.toBinary(0), "0");
    assertEquals(Recursion.toBinary(1), "1");
    assertEquals(Recursion.toBinary(2), "10");
    assertEquals(Recursion.toBinary(3), "11");
    assertEquals(Recursion.toBinary(4), "100");
    assertEquals(Recursion.toBinary(5), "101");
    assertEquals(Recursion.toBinary(15), "1111");
  }

  /**
   * Test of reverse method, of class Recursion.
   */
  @Test
  public void testReverse() {
    System.out.println("reverse");
    assertEquals(Recursion.reverse(""), "");
    assertEquals(Recursion.reverse("a"), "a");
    assertEquals(Recursion.reverse("ab"), "ba");
    assertEquals(Recursion.reverse("abc"), "cba");
    assertEquals(Recursion.reverse("abcd"), "dcba");
    assertEquals(Recursion.reverse("dogeeseseegod"), "dogeeseseegod");
  }

  /**
   * Test of reverse method, of class Recursion.
   */
  @Test
  public void testReverse2() {
    System.out.println("reverse2");
    assertEquals(Recursion.reverse2(""), "");
    assertEquals(Recursion.reverse2("a"), "a");
    assertEquals(Recursion.reverse2("ab"), "ba");
    assertEquals(Recursion.reverse2("abc"), "cba");
    assertEquals(Recursion.reverse2("abcd"), "dcba");
    assertEquals(Recursion.reverse2("dogeeseseegod"), "dogeeseseegod");

  }

  /**
   * Test of isPallindrome method, of class Recursion.
   */
  @Test
  public void testIsPallindrome() {
    System.out.println("isPallindrome");
    assertTrue(Recursion.isPalindrome(""));
    assertTrue(Recursion.isPalindrome("a"));
    assertTrue(Recursion.isPalindrome("aa"));
    assertFalse(Recursion.isPalindrome("ab"));
    assertFalse(Recursion.isPalindrome("ba"));
    assertTrue(Recursion.isPalindrome("pop"));
    assertTrue(Recursion.isPalindrome("poop"));
    assertTrue(Recursion.isPalindrome("dogeeseseegod"));
    assertTrue(Recursion.isPalindrome("amanaplanacanalpanama"));
  }
}
