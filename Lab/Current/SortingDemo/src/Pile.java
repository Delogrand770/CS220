
/**
 Description: A pile of int values used to demonstrate sorting methods.
 Sorting is one of the most studied problems in Computer Science and
 many different algorithms exist. For reference, see:
 http://en.wikipedia.org/wiki/Sorting_algorithm

 @author Randall.Bower
 */
public class Pile {
  // Size of the DrawingPanel; important it is square to show the distribution properly.

  public static final int SIZE = 700;
  // Constants describing the type of data to start with.
  public static final int ASCENDING = 0;  // Pile is initialized with data in ascending order.
  public static final int DESCENDING = 1; // Pile is initialized with data in descending order.
  public static final int ALMOST = 2;     // Pile is initialized with data that is almost sorted.
  public static final int RANDOM = 3;     // Pile is initialized with random data.
  // Information about the pile of data.
  private int[] data;                     // Holds the actual data in this pile.
  // Information about the most recently performed sort.
  private int comps;                      // Counter for number of comparisons.
  private int moves;                      // Counter for number of this.moves.
  private String sortName;                // Name of last sort performed.
  // A few things to display information about this pile.
  private DrawingPanel panel;             // To graphically display the data.
  private java.awt.Graphics2D graphics;   // Graphics object to do the drawing.
  private static final int DELAY = 33;    // In case the animation is too fast.
  private java.text.NumberFormat nf;      // For console output.

  /**
   Constructs a new pile with a drawing panel for animating the sort.
   */
  public Pile() {
    // Create the pile the same size as the drawing panel.
    this(Pile.SIZE);

    // Create the drawing panel the same size as the pile.
    this.panel = new DrawingPanel(Pile.SIZE, Pile.SIZE);
    this.panel.setWindowTitle("Sorting Demo");
    this.graphics = this.panel.getGraphics();
    this.graphics.setColor(java.awt.Color.BLACK);
  }

  /**
   Constructs a new pile with the given size. Does NOT create a drawing
   panel.

   @param size The size of this pile.
   */
  public Pile(int size) {
    // Set up the number format object so the output can be formatted
    // with digit grouping, which makes it much easier to read/compare.
    this.nf = java.text.NumberFormat.getInstance();
    this.nf.setGroupingUsed(true);

    // Allocate and reset the data for this pile.
    this.data = new int[size];
    this.reset();
  }

  /**
   Resets this pile to random data with a seed of 42. Why 42? Why not?!

   Method is final because it is called from the constructor and
   NetBeans shows an annoying light bulb otherwise.
   */
  public final void reset() {
    this.reset(Pile.RANDOM, 42);
  }

  /**
   Resets this pile with the indicated original ordering and the given
   seed.

   @param ordering Initial ordering to use for this pile.
   @param seed Seed for random number generator.
   */
  public final void reset(int ordering, int seed) {
    // Reset all tracking data.
    this.sortName = "None";
    this.comps = 0;
    this.moves = 0;

    if (ordering == Pile.DESCENDING) {
      // Fill the pile with all unique values between 0 and data.length in descending order.
      for (int i = 0; i < this.data.length; i++) {
        this.data[ i] = this.data.length - i;
      }
    } else // All others start with values in ascending order.
    {
      // Fill the pile with all unique values between 0 and data.length in ascending order.
      for (int i = 0; i < this.data.length; i++) {
        this.data[ i] = i;
      }
    }

    // Numbers are swapped to randomize the pile.
    int numSwaps = 0; // No swaps necessary for Pile.ASCENDING or Pile.DESCENDING.
    if (ordering == Pile.ALMOST) {
      numSwaps = this.data.length / 50;  // Just a few swaps ... about 2%.
    } else if (ordering == Pile.RANDOM) {
      numSwaps = this.data.length * 4;   // Lots of swaps.
    }

    // Create a random number generator with the given seed. Using the same seed
    // ensures the exact same data, thus resulting in reliable comparison data.
    java.util.Random rand = new java.util.Random(seed);

    // Now actually swap values at random locations to randomize the pile.
    for (int swap = 0; swap < numSwaps; swap++) {
      int i = rand.nextInt(this.data.length);
      int j = rand.nextInt(this.data.length);
      int temp = this.data[ i];
      this.data[ i] = this.data[ j];
      this.data[ j] = temp;
    }
  }

  /**
   Determines if the data in this pile is sorted. For use in JUnit
   tests.

   @return True if the data in this pile is sorted; false otherwise.
   */
  public boolean isSorted() {
    for (int i = 0; i < this.data.length - 1; i++) {
      // If adjacent elements are out of order, pile is not sorted.
      if (this.data[ i] > this.data[ i + 1]) {
        return false;
      }
    }
    return true; // None found out of order, so pile must be sorted.
  }

  /**
   Gets the number of comparisons performed by the most recent sort.
   Only comparisons of data values are counted, not comparisons of loop
   control variables.

   @return The number of comparisons performed by the most recent sort.
   */
  public int getComparisons() {
    return this.comps;
  }

  /**
   Gets the number of this.moves performed by the most recent sort. Only
   this.moves of data values are counted.

   @return The number of this.moves performed by the most recent sort.
   */
  public int getMoves() {
    return this.moves;
  }

  /**
   Gets the name of the sort most recently performed on this pile.

   @return The name of the sort most recently performed on this pile.
   */
  public String getSortName() {
    return this.sortName;
  }

  /**
   Gets the size of this pile.

   @return The number of items in this pile.
   */
  public int getSize() {
    return this.data.length;
  }

  /**
   Creates and returns a string representation of the results of the
   most recent sort of this pile.

   @return A string representing the most recent sort of this pile.
   */
  @Override
  public String toString() {
    return this.sortName + ": "
           + nf.format(this.comps) + " comparisons, "
           + nf.format(this.moves) + " moves, "
           + nf.format(this.data.length) + " elements.";
  }

  /**
   Graphically displays the current pile.
   */
  public void show() {
    if (this.panel != null) {
      // Clear the entire panel and show current status info in the title bar.
      this.panel.setBackground(java.awt.Color.WHITE);
      this.panel.setWindowTitle(this.toString());

      // Draw a 2x2 rectangle for each data item.
      for (int i = 0; i < this.data.length; i++) {
        this.graphics.fillRect(i, this.data.length - this.data[ i], 2, 2);
      }

      // Copy graphics to the screen and delay.
      this.panel.copyGraphicsToScreen();
      this.panel.sleep(DELAY);

      // Pause if the user has pressed a key.
      if (this.panel.keyHasBeenHit(DrawingPanel.ANY_KEY)) {
        this.panel.waitForKeyHit(); // Wait for another key hit to continue.
        this.panel.getKeyHitCode(); // Consume the key hit.
      }
    }
  }

  /**
   Performs a bubble sort on this pile.
   http://en.wikipedia.org/wiki/Bubble_sort
   */
  public void bubble() {
    this.sortName = "Bubble";
    this.bubble(0, this.data.length - 1);
    this.show();
  }

  private void bubble(int left, int right) {
    // This variable allows the sort to stop if it ever
    // makes a pass without swapping at least one value.
    boolean swappedOne = true; // True so the loop can start.

    // Each pass "bubbles" the largest element to the right.
    for (int pass = left; pass < right && swappedOne; pass++) {
      swappedOne = false; // Haven't swapped one yet on this pass.

      // Why does this loop stop at (right - pass)?
      for (int i = left; i < right - pass; i++) {
        comps++;
        if (this.data[ i] > this.data[ i + 1]) {
          moves += 3;
          int temp = this.data[ i];
          this.data[ i] = this.data[ i + 1];
          this.data[ i + 1] = temp;
          swappedOne = true;
        }
      }
      this.show();
    }
  }

  /**
   Performs a selection sort on this pile.
   http://en.wikipedia.org/wiki/Selection_sort
   */
  public void selection() {
    this.sortName = "Selection";
    this.selection(0, this.data.length - 1);
    this.show();
  }

  private void selection(int left, int right) {
    // Each pass puts the smallest item exactly where it belongs.
    for (int pass = left; pass < right; pass++) {
      int small = this.indexOfSmallest(pass, right);
      moves += 3;
      int temp = this.data[ pass];
      this.data[ pass] = this.data[ small];
      this.data[ small] = temp;
      this.show();
    }
  }

  private int indexOfSmallest(int left, int right) {
    int smallest = left;
    for (int i = left + 1; i <= right; i++) {
      comps++;
      if (this.data[ i] < this.data[ smallest]) {
        smallest = i;
      }
    }
    return smallest;
  }

  /**
   Performs an insertion sort on this pile.
   http://en.wikipedia.org/wiki/Insertion_sort
   */
  public void insertion() {
    this.sortName = "Insertion";
    this.insertion(0, this.data.length - 1);
    this.show();
  }

  private void insertion(int left, int right) {
    // Do one selection to put the smallest element in left so the
    // inner loop below doesn't have to repeatedly check i > 0.
    int i = this.indexOfSmallest(left, right);
    moves += 3;
    int temp = this.data[ left];
    this.data[ left] = this.data[ i];
    this.data[ i] = temp;
    this.show();

    // Each pass puts one more elemented into the sorted portion of the data.
    for (int pass = left + 1; pass <= right; pass++) {
      i = pass;
      moves++;
      temp = this.data[ i];
      comps++;
      while (temp < this.data[ i - 1]) {
        moves++;
        comps++;
        this.data[ i] = this.data[ i - 1];
        i--;
      }
      moves++;
      this.data[ i] = temp;
      this.show();
    }
  }

  /**
   Performs a shell sort on this pile.
   http://en.wikipedia.org/wiki/Shell_sort
   */
  public void shell() {
    this.sortName = "Shell";
    this.shell(0, this.data.length - 1);
    this.show();
  }

  private void shell(int left, int right) {
    // Calculate initial value for h.
    int h = 1;
    while (h <= (right - left) / 9) {
      h = 3 * h + 1;
    }

    for ( /*
             h initialized above
             */; h > 0; h /= 3) {
      // The inner part of this sort is an insertion sort.
      // So when h=1, this will completely sort the data.
      for (int pass = left + h; pass <= right; pass++) {
        int i = pass;
        int temp = this.data[ i];

        while (i >= left + h && temp <= this.data[ i - h]) {
          this.data[ i] = this.data[ i - h];
          i -= h;
        }
        this.data[ i] = temp;
        this.show();
      }
    }
  }

  /**
   Performs a quick sort on this pile.
   http://en.wikipedia.org/wiki/Quicksort
   */
  public void quick() {
    this.sortName = "Quick";
    this.quick(0, this.data.length - 1);
    this.show();
  }

  private void quick(int left, int right) {
    // If the segment of data to be sorted is small, the overhead of
    // recursion isn't worth the benefit, so just do an insertion sort.
    if (right - left > 7) {
      int pivot = this.partition(left, right);
      this.show();
      this.quick(left, pivot - 1);
      this.quick(pivot + 1, right);
    } else if (right - left > 0) // At least two to sort.
    {
      this.insertion(left, right);
      this.show();
    }
  }

  // Use the rightmost element as the pivot.
  private int partition(int left, int right) {
    int pivot = this.data[ right]; // Use the rightmost element as the pivot.
    int i = left;
    int j = right - 1; // Subtract one because the rightmost is the pivot.
    int temp; // Temporary variable used for swapping.

    while (i < j) {
      // The purpose of this loop is to increment i to the index of the
      // next value that needs to be moved to the other side of the pivot.
      while (this.data[ i] < pivot) // Why doesn't this need to check i < right?
      {
        i++; // The only purpose of this loop is to increment i to the next value
      }

      // The purpose of this loop is to decrement j to the index of the
      // next value that needs to be moved to the other side of the pivot.
      while (pivot < this.data[ j] && j > left) {
        j--;
      }

      // Only swap if i and j are still on opposite sides of the pivot.
      if (i < j) {
        temp = this.data[ i];
        this.data[ i] = this.data[ j];
        this.data[ j] = temp;
      }
    }

    // Put the pivot element between the two partitions.
    temp = this.data[ i];
    this.data[ i] = this.data[ right];
    this.data[ right] = temp;

    // Return the index of the pivot element so the
    // recursive calls can be made on either side.
    return i;
  }

  // Select a random pivot element, move it to the rightmost
  // location, and then call the regular partition method.
  private int partition2(int left, int right) {
    int temp, i = new java.util.Random().nextInt(right - left) + left;
    temp = this.data[ i];
    this.data[ i] = this.data[ right];
    this.data[ right] = temp;
    return this.partition(left, right);
  }

  // Select the pivot element as the medial of the leftmost,
  // rightmost, and middle elements, move it to the rightmost
  // location, and then call the regular partition method.
  private int partition3(int left, int right) {
    int temp, middle = (left + right) / 2;

    // The leftmost element is the median.
    if (this.data[ left] < this.data[ middle] && this.data[ left] > this.data[ right]
        || this.data[ left] > this.data[ middle] && this.data[ left] < this.data[ right]) {
      temp = this.data[ left];
      this.data[ left] = this.data[ right];
      this.data[ right] = temp;
    } // The middle element is the median.
    else if (this.data[ middle] < this.data[ left] && this.data[ middle] > this.data[ right]
             || this.data[ middle] > this.data[ left] && this.data[ middle] < this.data[ right]) {
      temp = this.data[ middle];
      this.data[ middle] = this.data[ right];
      this.data[ right] = temp;
    }
    // No need for the last else here because it would mean 
    // the rightmost element was the median, so just leave it.

    return this.partition(left, right);
  }

  /**
   Performs a merge sort on this pile.
   http://en.wikipedia.org/wiki/Merge_sort
   */
  public void merge() {
    // Declare an auxiliary array and pass it along to merge.
    int aux[] = new int[data.length];

    this.sortName = "Merge";
    this.merge(0, this.data.length - 1, aux);
    this.show();
  }

  private void merge(int left, int right, int[] aux) {
    // When left >= right, there's  nothing to sort.
    // Notice the recursive calls are before the merge, so the
    // first actual merge only merges one element segments.
    if (left < right) {
      int middle = (right + left) / 2;
      this.merge(left, middle, aux);        // Recursive call.
      this.merge(middle + 1, right, aux);   // Recursive call.
      this.merge(left, middle, right, aux); // Actually do the merge!
      this.show();
    }
  }

  private void merge(int left, int middle, int right, int aux[]) {
    int i, j, k;

    // Copies the left portion of the data being merged into the auxiliary array.
    // Note: This is done by counting down so i is equal to left when done.
    for (i = middle + 1; i > left; i--) {
      aux[ i - 1] = this.data[ i - 1];
    }

    // Copies the right portion of the data being merged into the auxiliary array.
    // Note: The items are copied in REVERSED ORDER so the largest are in the middle.
    // Note: When done with this loop, j is equal to right.
    for (j = middle; j < right; j++) {
      aux[ right + middle - j] = this.data[ j + 1];
    }

    // Merges the left and right portions together in sorted order.
    // Note: since the right portion is in reverse order, there is no
    // need to worry about going out of bounds!
    for (k = left; k <= right; k++) {
      if (aux[ j] < aux[ i]) {
        this.data[ k] = aux[ j--];
      } else {
        this.data[ k] = aux[ i++];
      }
    }
  }

  /**
   Performs a heap sort on this pile.
   http://en.wikipedia.org/wiki/Heapsort
   */
  public void heap() {
    this.sortName = "Heap";

    // Heap sort will be discussed later in the semester!
    // Feel free to attempt an implementation over spring break.
  }
}
