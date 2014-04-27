
/**
 GR_testing -

 @author C14Gavin.Delphia
 @version 1.0 - Feb 27, 2012 at 9:59:23 PM
 */
public class GR_testing {

  public static void main(String[] args) {
    int N = 6;
    int count = 0;
    for (int i = 1; i <= N; i++) {
      for (int j = 1; j <= i; j++) {
        count++;
      }
    }
    System.out.println("N = " + N + " and it ran " + count + " times");
  }
}