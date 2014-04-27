
import java.util.ArrayList;

/**
 Thing -

 @author C14Gavin.Delphia
 @version 1.0 - Feb 6, 2012 at 2:01:09 PM
 */
public class Thing {

  public static int N = 10000;

  public static void main(String[] args) {
    ArrayList<Integer> aList = new ArrayList(N);
    for (int i = 0; i < N * 2; i++) {
      aList.add((int) (Math.random() * 100));
    }
    for (int i = 0; i < 100; i++) {
      
    }
  }
}