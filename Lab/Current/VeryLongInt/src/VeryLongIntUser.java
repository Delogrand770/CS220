
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VeryLongIntUser {

  public static void main(String[] args) {
    new VeryLongIntUser().run();
  } // method main

  public void run() {
    final String SENTINEL = "exit";
    final String INPUT_PROMPT = ">";

    Scanner sc = new Scanner(System.in), lineScanner;

    String line, method, argument;

    VeryLongInt veryLong = null, otherVeryLong;

    while (true) {
      try {
        System.out.println(INPUT_PROMPT);
        line = sc.nextLine();
        if (line.equals(SENTINEL)) {
          break;
        }
        veryLong = process(line, veryLong);
        //System.out.println(VERY_LONG_MESSAGE + veryLong + "\n\n");
      } // try
      catch (Exception e) {
        System.out.println(e + "\n\n");
      } // catch Exception              
    } // while
  } // method run

  /**
   Parses the given line and calls the method specified in the line.

   @param line - the given line
   @param veryLong - the VeryLongInt object that calls the method
   specified.

   @return the VeryLongInt object resulting from the method call.

   @throws IllegalArgumentException - if the method is not legal or if
   the method's argument has no digit characters
   @throws NoSuchElementException - if the line is a string with fewer
   than two tokens
   @throws NullPointerException - if line is null or if veryLong is null
   and the add method is called with a legal argument
   */
  public VeryLongInt process(String line, VeryLongInt veryLong) {
    final String RESULT = ">>";

    final String STRING_CONSTRUCTOR = "VLI";

    final String TO_STRING = "toString";

    final String ADD = "add";

    final String SIZE = "size";

    final String LESS = "less";

    final String GREATER = "greater";
    
    final String EQUALS = "equals";

    final String BAD_METHOD = "The line entered does not represent a legal method.";

    VeryLongInt otherVeryLong;
    String method, argument;
    Scanner lineScanner = new Scanner(line);
    method = lineScanner.next();

    if (method.equals(STRING_CONSTRUCTOR)) {
      argument = lineScanner.next();
      veryLong = new VeryLongInt(argument);
      System.out.println(RESULT + veryLong.toString());
    } // string constructor
    else if (method.equals(TO_STRING)) {

      System.out.println(RESULT + veryLong);

    } // toString method        
    else if (method.equals(ADD)) {
      argument = lineScanner.next();
      otherVeryLong = new VeryLongInt(argument);
      veryLong.add(otherVeryLong);
      System.out.println(veryLong);

    } // add method     
    else if (method.equals(SIZE)) {
      System.out.println(RESULT + veryLong.size());

    } // less method
    else if (method.equals(LESS)) {
      argument = lineScanner.next();
      otherVeryLong = new VeryLongInt(argument);
      System.out.println(RESULT + veryLong.less(otherVeryLong));

    } // euqals method
    else if (method.equals(EQUALS)) {
      argument = lineScanner.next();
      otherVeryLong = new VeryLongInt(argument);
      System.out.println(RESULT + veryLong.equals(otherVeryLong));
      
    } // greater method
    else if (method.equals(GREATER)) {
      argument = lineScanner.next();
      otherVeryLong = new VeryLongInt(argument);
      System.out.println(RESULT + veryLong.greater(otherVeryLong));

    } else {
      throw new IllegalArgumentException(BAD_METHOD);
    }
    return veryLong;
  } // method process
} // class VeryLongIntUser
