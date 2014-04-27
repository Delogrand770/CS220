
import org.junit.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 Tests of the sort methods of the Pile class.

 @author Randall.Bower
 */
public class PileTest {

  private Pile pile;                  // The pile to be tested.
  private java.text.NumberFormat nf;  // For console output.
  private long startTime, endTime;    // For timing of each test.

  public PileTest() {
    // Create a large pile with 2^12 items (4096).
    this.pile = new Pile((int) Math.pow(2, 12));

    // Set up the number format object so the nanoseconds output can be formatted
    // with digit grouping, which makes them exponentially easier to read/compare.
    this.nf = java.text.NumberFormat.getInstance();
    this.nf.setGroupingUsed(true);
  }

  @Before
  public void setUp() {
    // Reset the pile before each test.
    this.pile.reset();

    // Can also specify the original ordering of data and a seed for the
    // random number generator when resetting the pile for each test.
    // Interesting things happen with data that is already in ascending
    // order, in descending order, or almost sorted already.
//    this.pile.reset( Pile.ASCENDING, 37 );
//    this.pile.reset( Pile.DESCENDING, 2014 );
//    this.pile.reset( Pile.ALMOST, 2015 );
  }

  @After
  public void tearDown() {
    // Report the results of each test.
    System.out.printf("%10s: %8s elements %12s comparisons %12s moves %12s nanoseconds\n",
                      this.pile.getSortName(),
                      nf.format(this.pile.getSize()),
                      nf.format(this.pile.getComparisons()),
                      nf.format(this.pile.getMoves()),
                      nf.format(this.endTime - this.startTime));
  }

  /**
   Test of bubble method, of class Pile.
   */
  @Test
  public void testBubble() {
    this.startTime = System.nanoTime();
    this.pile.bubble();
    this.endTime = System.nanoTime();
    assertTrue(this.pile.isSorted());
  }

  /**
   Test of selection method, of class Pile.
   */
  @Test
  public void testSelection() {
    this.startTime = System.nanoTime();
    this.pile.selection();
    this.endTime = System.nanoTime();
    assertTrue(this.pile.isSorted());
  }

  /**
   Test of insertion method, of class Pile.
   */
  @Test
  public void testInsertion() {
    this.startTime = System.nanoTime();
    this.pile.insertion();
    this.endTime = System.nanoTime();
    assertTrue(this.pile.isSorted());
  }

  /**
   Test of shell method, of class Pile.
   */
  @Test
  public void testShell() {
    this.startTime = System.nanoTime();
    this.pile.shell();
    this.endTime = System.nanoTime();
    assertTrue(this.pile.isSorted());
  }

  /**
   Test of quick method, of class Pile.
   */
  @Test
  public void testQuick() {
    this.startTime = System.nanoTime();
    this.pile.quick();
    this.endTime = System.nanoTime();
    assertTrue(this.pile.isSorted());
  }

  /**
   Test of merge method, of class Pile.
   */
  @Test
  public void testMerge() {
    this.startTime = System.nanoTime();
    this.pile.merge();
    this.endTime = System.nanoTime();
    assertTrue(this.pile.isSorted());
  }

  /**
   Test of heap method, of class Pile.
   */
  @Test
  @Ignore // Heap sort not yet implemented.
  public void testHeap() {
    this.startTime = System.nanoTime();
    this.pile.heap();
    this.endTime = System.nanoTime();
    assertTrue(this.pile.isSorted());
  }
}
