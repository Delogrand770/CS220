
/**
 File: Pile.java

 Description: A pile of int values.

 @author Randall.Bower
 */
public class Pile {

  private int[] pile;
  private int currentSize;

  public Pile() {
    this(1024);
  }

  public Pile(int maxSize) {
    this.pile = new int[maxSize];
    this.currentSize = 0;
  }

  public void add(int value) {
    if (this.currentSize >= this.pile.length) {
      throw new RuntimeException("Pile is full!");
    }

    // Put the value in the next available spot.
    //this.pile[ this.currentSize++] = value;
    int index = this.currentSize++;
    while (index > 0 && this.pile[index - 1] > value) {
      this.pile[index] = this.pile[index - 1];
      index--;
    }
    this.pile[index] = value;
  }

  public int get() {
    if (this.currentSize <= 0) {
      throw new RuntimeException("Pile is empty!");
    }

    return this.pile[ --this.currentSize];
  }

  public boolean contains(int value) {
    // Do a linear search of the un-sorted values.
//    for (int i = 0; i < this.currentSize; i++) {
//      if (value == this.pile[ i]) {
//        return true;
//      }
//    }
//    return false;
    return this.contains(value, 0, currentSize - 1);
  }

  private boolean contains(int value, int leftI, int rightI) {
    if (leftI > rightI) {
      return false;
    }
    
    int middle = (leftI + rightI) / 2;
    if (this.pile[middle] == value) {
      return true;
    }
    
    if (value < this.pile[middle]) {
      return contains(value, leftI, middle - 1);
    } else {
      return contains(value, middle + 1, rightI);
    }
  }

  @Override
  public String toString() {
    String s = "[";
    for (int i = 0; i < this.currentSize; i++) {
      s += this.pile[ i] + " ";
    }
    return s.trim() + "]";
  }
}
