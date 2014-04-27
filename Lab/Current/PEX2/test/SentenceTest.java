/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;
import static org.junit.Assert.*;
import org.junit.*;

/**
 @author David.Gibson
 @author C14Gavin.Delphia
 @author C14Matthew.Johnson
 */
public class SentenceTest {

  public SentenceTest() {
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
   Test of Sentence method, of class Sentence
   */
  @Test
  public void testSentence() {
    
    System.out.println("sentence test");
    
    //creates a small predictable grammar for testing
    String data = " {\n<start>\n"
                  + "<letter> <number>;\n}\n"
                  + "{\n<letter>\n"
                  + "aaa <number>;\n}\n"
                  + "{\n<number>\n"
                  + "111;\n}";

    Grammar g = new Grammar(new Scanner(data));
    Sentence s = new Sentence(g, "<start>");
    assertEquals(s.toString(), "aaa 111 111");

    //Check to make sure the random Production is working
    System.out.println("random production test");
    data = " {\n<start>\n"
           + "<letters>;\n}\n"
           + "{\n<letters>\n"
           + "aaa;\nbbb;\nccc;\n}";

    g = new Grammar(new Scanner(data));
    int[] counts = new int[4];
    
    //check against a large data set for errors
    for (int i = 0; i < 1024; i++) {
      s = new Sentence(g, "<start>");
      switch (s.toString()) {
        case "aaa":
          counts[0]++;
          break;
        case "bbb":
          counts[1]++;
          break;
        case "ccc":
          counts[2]++;
          break;
        default:
          counts[3]++;
          break;
      }
    }

    assertTrue("aaa count", counts[0] > 0);
    assertTrue("bbb count", counts[1] > 0);
    assertTrue("ccc count", counts[2] > 0);
    assertTrue("ERROR count", counts[3] == 0);

    System.out.println("\taaa: " + counts[0] + "\n\tbbb: " + counts[1]
                       + "\n\tccc: " + counts[2] + "\n\tERRORS: " + counts[3]);
  }

  /**
   Test of size method, of class Sentence
   */
  @Test
  public void testSize() {
    //increments size to ensure size method functions 
    
    System.out.println("size");
    
    Sentence instance = new Sentence();
    instance.append(instance.makeNode("a"));
    assertEquals(instance.size(), 1);

    instance.append(instance.makeNode("b"));
    assertEquals(instance.size(), 2);

    instance.append(instance.makeNode("c"));
    assertEquals(instance.size(), 3);
  }

  /**
   Test of append method, of class Sentence
   */
  @Test
  public void testAppend() {

    System.out.println("append");

    Sentence instance = new Sentence();

    instance.append(instance.makeNode("a"));
    assertEquals(instance.toString(), "a");

    instance.append(instance.makeNode("b"));
    assertEquals(instance.toString(), "a b");

    instance.append(instance.makeNode("c"));
    assertEquals(instance.toString(), "a b c");
  }

  /**
   Test of replace method, of class Sentence
   */
  @Test
  public void testReplace() {

    System.out.println("replace");

    Sentence instance = new Sentence();
    instance.append(instance.makeNode("a"));
    instance.append(instance.makeNode("b"));
    instance.append(instance.makeNode("c"));

    Sentence second = new Sentence();
    second.append(second.makeNode("1"));
    second.append(second.makeNode("2"));
    
    //this is the case of replacing the last item
    instance.replace(instance.head.getNext().getNext(), second);
    assertEquals("a b 1 2", instance.toString());

    Sentence third = new Sentence();
    third.append(third.makeNode("X"));
    third.append(third.makeNode("Y"));
    
    //replacing the case of the 2nd item
    instance.replace(instance.head.getNext(), third);
    assertEquals("a X Y 1 2", instance.toString());

    Sentence fourth = new Sentence();
    fourth.append(fourth.makeNode("7"));
    fourth.append(fourth.makeNode("8"));
    
    //replacing the head. checks the head==null possibility 
    instance.replace(instance.head, fourth);
    assertEquals("7 8 X Y 1 2", instance.toString());
  }
}
