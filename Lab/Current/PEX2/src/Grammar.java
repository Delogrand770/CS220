
/**
 Grammar - Contains a list of rules in the grammar.

 @author C14Gavin.Delphia
 @author C14Matthew.Johnson
 @version 1.0 - Feb 13, 2012 at 10:06:08 PM
 */
import java.util.ArrayList;
import java.util.Scanner;

public class Grammar {

  //The list of rules used in this Grammar
  private ArrayList<Rule> rules = new ArrayList<>();

  /**
   Constructor that creates a new Grammar. This reads the fileData
   scanner and populates the list rules with these rules.
   */
  public Grammar(Scanner fileData) {
    fileData.useDelimiter("\\u007B");//unicode for {
    
    //get the first chunk of the file
    String intro = fileData.next();
    
    //assigns the String start based on if the first chunk of the file is usable. 
    //if it is usable we assign start to intro otherwise we move on.
    String start = intro.replace("\r\n", "").endsWith("}") ? intro : fileData.next();
    
    boolean firstrun = true;
    
    while (fileData.hasNext()) {
      String rule = "";
      
      //we only need to use the predetermined start once
      //then firstrun is set to false and fileData.next() is used from now on
      start = firstrun ? start : fileData.next();
      firstrun = false;
      
      // The .split removes random comments throughout the grammar
      // but adds a blank production to the end of every rule.
      Scanner ruleData = new Scanner(start.split("\\u007D")[0]);//unicode for }
      while (ruleData.hasNextLine()) {
        rule += ruleData.nextLine() + "\n";//takes ALL the lines into a string 
      }
      rules.add(new Rule(rule)); //sends the string to create a new rule
    }
  }

  /**
   Returns a rule if the symbol matches the rule

   @param symbol String that contains the desired symbol
   @return the rule that matches the symbol
   */
  public Rule lookup(String symbol) {
    for (int i = 0; i < rules.size(); i++) {
      if (rules.get(i).getSymbol().equals(symbol)) {
        return rules.get(i);
      }
    }
    return null;
  }

  /**
   Creates a string representation of the Grammar.

   @return a string representation of the Grammar.
   */
  @Override
  public String toString() {
    String temp = "";
    for (int i = 0; i < rules.size(); i++) {
      temp += "{\n" + rules.get(i) + "}\n\n";
    }
    return temp;
  }
}