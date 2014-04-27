
/**
 Production - Contains a list of the words in a production.

 @author C14Gavin.Delphia
 @author C14Matthew.Johnson
 @version 1.0 - Feb 14, 2012 at 1:44:46 PM
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Production {

  public ArrayList<String> words = new ArrayList<>();

  /**
   Constructor for a new production. Takes the ruleData and creates a
   list of words and populates the list words with these words.
   */
  public Production(String ruleData) {
    Scanner data = new Scanner(ruleData);
    while (data.hasNext()) {
      words.add(data.next().trim());
    }
  }

  /**
   Creates a string representation of the Production.

   @return a string representation of the Production.
   */
  @Override
  public String toString() {
    String temp = "";
    for (int i = 0; i < words.size(); i++) {
      temp += words.get(i) + " ";
    }
    return temp;
  }
}