
/**
 Description: Various recursive examples.

 @author Randall.Bower
 */
public class Recursion {

  public static long factorial(int n) {
    if (n <= 1) {
      return 1;
    } else {
      return n * factorial(n - 1);
    }
  }

  public static long fibonacci(int n) { //insanely inneficient
    if (n <= 2) {
      return 1;
    } else {
      return fibonacci(n - 1) + fibonacci(n - 2);
    }
  }

  public static long fibNonRecursive(int n) { //iterative not recursive
    if (n <= 2) {
      return 1;
    } else {
      int previousPrevious = 1;
      int previous = 1;
      int curr = previous + previousPrevious;
      for (int i = 3; i < n; i++) {
        previousPrevious = previous;
        previous = curr;
        curr = previous + previousPrevious;
      }
      return curr;
    }
  }

  public static String toBinary(int n) {
    if (n < 2) {
      return Integer.toString(n);
    } else {
      return toBinary(n / 2) + Integer.toString(n % 2);
    }
  }

  public static String reverse(String str) {
    if (str.length() <= 1) {
      return str;
    } else {
      //return str.charAt(str.length() - 1) + reverse(str.substring(0, str.length() - 1));
      return str.substring(str.length() - 1) + reverse(str.substring(0, str.length() - 1));
    }
  }

  public static String reverse2(String str) {
    if (str.length() <= 1) {
      return str;
    } else {
      return reverse(str.substring(1)) + str.charAt(0);
    }
  }

  public static boolean isPalindrome(String str) {
    if (str.length() <= 1) {
      return true;
    } else {
      int last = str.length() - 1;
      return str.charAt(0) == str.charAt(last) && isPalindrome(str.substring(1, last));
    }
  }
}
