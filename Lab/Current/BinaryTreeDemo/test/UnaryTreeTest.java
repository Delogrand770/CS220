import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit tests for the UnaryTree class.
 *
 * @author Randall.Bower
 */
public class UnaryTreeTest
{
  /**
   * Test of insert, contains, and toString methods, of class UnaryTree.
   * 
   * NOTE: Is this one test sufficient?
   */
  @Test
  public void test()
  {
    String[] alphabet = {
      "Hotel", 
      "Delta", "Bravo", "Alpha", "Charlie", "Foxtrot", "Echo", "Golf",
      "Lima", "Juliette", "India", "Kilo", "November", "Mike", "Oscar" };

    UnaryTree<String> tree = new UnaryTree();
    
    for( int i = 0; i < alphabet.length; i++ )
    {
      tree.insert( alphabet[ i ] );
    }
    
    // If this test passes, insert and toString must be working.
    assertEquals( "Alpha Bravo Charlie Delta Echo Foxtrot Golf Hotel India Juliette Kilo Lima Mike November Oscar", tree.toString().trim() );
    
    // Test contains with the first and last elements as well as one in the middle.
    assertTrue( tree.contains( "Alpha" ) );
    assertTrue( tree.contains( "Hotel" ) );
    assertTrue( tree.contains( "Oscar" ) );
    
    // Test contains with elements that should be first, last, or somewhere in the middle, but aren't.
    assertFalse( tree.contains( "Adam" ) );
    assertFalse( tree.contains( "Hike" ) );
    assertFalse( tree.contains( "Zebra" ) );
  }
}
