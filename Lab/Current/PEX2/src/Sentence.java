
/**
 Sentence - A set of nodes containing a single word each and a pointer
 to the next node. This is also what recursively generates the
 sentences.

 @author C14Gavin.Delphia
 @author C14Matthew.Johnson
 @version 1.0 - Feb 14, 2012 at 1:33:24 PM
 */
public final class Sentence {

  // Node - inner class defining singly linked node structure  
  public class Node {

    private String word; // word stored in the node
    private Node next; // reference to the next node

    // default Node constructor - creates empty node (no item stored)
    private Node() {
      this(null);
    }

    // Node construtor - creates a node holding the given item
    private Node(String word) {
      this.word = word;
      this.next = null;
    }

    /**
     Accessor method for the word

     @return The word
     */
    public String getWord() {
      return this.word;
    }

    /**
     Mutator for the word

     @param word The new word
     */
    public void setWord(String word) {
      this.word = word;
    }

    /**
     Accessor method for the next Node

     @return the next Node
     */
    public Node getNext() {
      return this.next;
    }
  }
  // Node construtor - creates a node holding the given item
  Node head; // reference to first node in list

  /**
   Sentence constructor - creates an empty sentence.
   */
  public Sentence(Grammar grammarData, String symbol) {
    this.head = null;
    createSentence(this, grammarData, symbol);
  }

  /**
   Creates a new Sentence based on a previous sentence, a Grammar file
   and a starting symbol. This method recursively calls the Sentence
   constructor which then calls the createSentence again until all the
   nonTerminal strings have been replaced with terminal productions.

   @param s The sentence data
   @param grammarData The grammarData
   @param symbol The symbol to start with
   */
  public void createSentence(Sentence s, Grammar grammarData, String symbol) {
    //System.out.println(symbol); //For debugging out custom Grammar
    Production start = grammarData.lookup(symbol).getRandomProduction();
    for (int i = 0; i < start.words.size(); i++) {
      s.append(new Node(start.words.get(i)));
    }

    for (Sentence.Node current = s.head; current != null; current = current.next) {
      if (current.word.startsWith("<") && current.word.endsWith(">")) {
        Sentence nextSentence = new Sentence(grammarData, current.word);
        if (nextSentence.size() != 0) {
          s.replace(current, nextSentence);
        } else {
          Sentence blank = new Sentence();
          blank.append(blank.makeNode(""));
          s.replace(current, blank);
        }
      }
    }
  }

  /**
   Sentence constructor - creates an empty sentence. For testing
   purposes
   */
  public Sentence() {
    this.head = null;
  }

  /**
   Returns String representation of the sentence

   @return String representation of the sentence
   */
  @Override
  public String toString() {
    String result = "";
    if (head != null) {
      result = result + head.word.toString();
      for (Sentence.Node current = head.next; current != null; current = current.next) {
        String word = current.word.toString().trim();

        // No need to put space before periods, commas or blank spaces.
        String space = (word.equals(".") || word.equals(",") || word.equals("") || word.equals(":") || word.equals("s")) ? "" : " ";
        result += space + word;
      }
    }
    return result;
  }

  /**
   Determines the number of items in the SinglyLinkedList

   @return number of items in the list
   */
  public int size() {
    int count = 0;
    for (Node current = head; current != null; current = current.next) {
      count++;
    }
    return count;
  }

  /**
   Appends a new word to the end (tail) of the sentence

   @param newWord - word to be appended to the end of the sentence
   */
  public void append(Node newNode) {
    if (head == null) {
      //head is null condition
      head = newNode;
    } else {
      //head is not null condition
      Sentence.Node current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = newNode;
    }
  }

  /**
   Replaces the Node whichNode with a new Sentence s

   @param whichNode The Node to replace
   @param s The sentence to replace the node with
   */
  public void replace(Node whichNode, Sentence s) {
    
    Node current = head;
    //holds the placement for reconnection at the end
    
    Node temp = whichNode.next;
    //System.out.println(whichNode.word + " = search for");
    if (head != whichNode) {
      
      //searches for the node that matches what is to be replaced
      while (current.next != null && current.next != whichNode) {
        current = current.next;
      }

      //System.out.println(current.word + " = found (1 before)");
      current.next = s.head;
      //System.out.println(current.word + " = first replace with " + s.head.word);

    } else {
      head = s.head;
    }

    if (temp != null) {
      
      //attaching the sentence s to the original sentence
      Node temp2 = s.head;
      int size = s.size();
      for (int i = 0; i < size; i++) {
        current.next = temp2;
        current = current.next;
        temp2 = temp2.next;
      }
      
      //reconnecting the end of the newly added parts to the original sentence
      current.next = temp;
      //String result = (temp != null) ? temp.word : "End";
      //System.out.println(current.word + " = final replace with " + result);
    }
  }

  /**
   Returns a new Node with a specified string in that node

   @param word The string to put in the Node
   @return the new node with the specified string data
   */
  public Node makeNode(String word) {
    return new Node(word);
  }
}