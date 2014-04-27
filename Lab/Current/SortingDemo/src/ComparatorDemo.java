
import java.util.Arrays;
import java.util.Comparator;

/**
 Description: A demonstration of the Comparator interface.

 @author Randall.Bower
 */
public class ComparatorDemo {
  // A comparator for String objects that uses string length.

  static final Comparator<String> STRING_LENGTH = new Comparator<String>() {

    @Override
    public int compare(String s1, String s2) {
      return s1.length() - s2.length();
    }
  };
  // A comparator for String objects that uses string number.
  static final Comparator<String> STRING_NUMBER = new Comparator<String>() {

    @Override
    public int compare(String s1, String s2) {
      return Integer.parseInt(s1) - Integer.parseInt(s2);
    }
  };
  // A comparator for Pile objects that uses the number of comparisons performed.
  static final Comparator<Pile> COMPS = new Comparator<Pile>() {

    @Override
    public int compare(Pile p1, Pile p2) {
      return p1.getComparisons() - p2.getComparisons();
    }
  };
  // A comparator for Pile objects that uses the number of moves performed.
  static final Comparator<Pile> MOVES = new Comparator<Pile>() {

    @Override
    public int compare(Pile p1, Pile p2) {
      return p1.getMoves() - p2.getMoves();
    }
  };

  public static void main(String[] args) {
    String[] words = {"Cat", "Apple", "Doolie", "Book", "Eh"};
    String[] nums = {"123", "1", "-10", "0"};
    // Default sorting method is alphabetical.
    Arrays.sort(words);
    System.out.println(Arrays.toString(words));

    // Using the STRING_LENGTH comparator sorts the strings by length.
    Arrays.sort(words, STRING_LENGTH);
    System.out.println(Arrays.toString(words));

    // Using the STRING_NUMBER comparator sorts the string as if they were integers.
    Arrays.sort(nums, STRING_NUMBER);
    System.out.println(Arrays.toString(nums));

    // Create an array of Pile objects that call different sort methods.
    Pile[] piles = new Pile[6];
    piles[0] = new Pile(1024);
    piles[0].bubble();
    piles[1] = new Pile(1024);
    piles[1].selection();
    piles[2] = new Pile(1024);
    piles[2].insertion();
    piles[3] = new Pile(1024);
    piles[3].shell();
    piles[4] = new Pile(1024);
    piles[4].quick();
    piles[5] = new Pile(1024);
    piles[5].merge();

    // Note: Put a newline character at the end of the toString method
    // in Pile to make the output below look a bit nicer.

    // Sort the piles based on comparisons.
    Arrays.sort(piles, COMPS);
    System.out.println(Arrays.toString(piles));

    // Sort the piles based on moves.
    Arrays.sort(piles, MOVES);
    System.out.println(Arrays.toString(piles));
  }
}
