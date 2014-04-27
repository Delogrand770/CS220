
/**
 * EX0_08 - 
 *
 * @author C14Gavin.Delphia
 *
 * @version 1.0 - Jan 5, 2012 at 2:02:57 PM
 */
import java.util.Scanner;

public class EX0_08 {

  public static final String DELIM = " ";

  public static void main(String[] args) {
    substringToo();
  }

  public static void substringToo() { //not working correctly
    Scanner ask = new Scanner(System.in);

    System.out.print("Enter a sentence: ");
    String phrase = ask.nextLine();

    System.out.print("Enter a word to replace: ");
    String token = ask.next();

    System.out.print("Enter new word: ");
    String replacement = ask.next();

    Scanner search = new Scanner(phrase);
    String result = "";

    while (search.hasNext()) {
      String currentToken = search.next();

      if (currentToken.contains(token)) {
        result += replacement + DELIM;
      } else {
        result += currentToken + DELIM;
      }

    }

    System.out.println(result);
  }

  public static void wordOnly() { //working correctly
    Scanner ask = new Scanner(System.in);

    System.out.print("Enter a sentence: ");
    String phrase = ask.nextLine();

    System.out.print("Enter a word to replace: ");
    String token = ask.next();

    System.out.print("Enter new word: ");
    String replacement = ask.next();

    Scanner search = new Scanner(phrase);
    String result = "";

    while (search.hasNext()) {
      String currentToken = search.next();

      if (currentToken.equalsIgnoreCase(token)) {
        result += replacement + DELIM;
      } else {
        result += currentToken + DELIM;
      }

    }

    System.out.println(result);
  }
}
