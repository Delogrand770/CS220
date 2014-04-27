
/**
 Rule - Contains a list of productions in the rule.

 @author C14Gavin.Delphia
 @author C14Matthew.Johnson
 @version 1.0 - Feb 13, 2012 at 10:06:18 PM
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Rule {

  private String symbol;
  //All the data for the current rule
  private ArrayList<Production> productions = new ArrayList<>();

  /**
   Constructor for a new rule. Creates the productions based on the
   grammarData provided and populates the list productions with these
   productions.

   */
  public Rule(String grammarData) {
    Scanner data = new Scanner(grammarData);
    data.useDelimiter("<");//looks for the beginning of a rule
    data.next();

    // fixes the semicoln after the symbol problem
    this.symbol = data.nextLine().split(";")[0].trim();
    
    //looks for productions
    while (data.hasNext()) {
      data.useDelimiter(";");
      String temp = data.next().trim();
      if (!temp.contains("}")) {
        productions.add(new Production(temp));
      }
    }
    
    // removes the blank production at the end of every rule added by
    // the .split that removes the random comments
    this.productions.remove(productions.size() - 1);
  }

  /**
   Returns a random production in the rule.

   @return - a random production in the rule.
   */
  public Production getRandomProduction() {
    Random rand = new Random();
    return productions.get(rand.nextInt(productions.size()));
  }

  /**
   Access method for symbol

   @return symbol
   */
  public String getSymbol() {
    return this.symbol;
  }

  /**
   Creates a string representation of the Rule.

   @return a string representation of the Rule.
   */
  @Override
  public String toString() {
    String temp = this.symbol + "\n";
    for (int i = 0; i < productions.size(); i++) {
      temp += productions.get(i).toString().trim() + " ;\n";
    }
    return temp;
  }
}