
import java.util.Scanner;

/**
 WordCounter -

 @author C14Gavin.Delphia
 @version 1.0 - Apr 4, 2012 at 1:56:51 PM
 */
public class WordCounter {

  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.err.println("Problem setting UI.");
    }

    Scanner data = FileDialogs.selectInputFile(null);
    java.util.TreeMap<String, Integer> map = new java.util.TreeMap();

    if (data != null) {
      while (data.hasNext()) {
        //if any chars are not a-z or A-Z remove them
        String next = data.next().replaceAll("[^a-zA-Z]", "").toLowerCase();

        if (next.length() > 0) {
          int count = 0;

          if (map.containsKey(next)) {
            count = map.get(next);
          }

          map.put(next, count + 1);
        }
      }

      int totalWords = 0;
      for (java.util.Map.Entry<String, Integer> entry : map.entrySet()) {
        System.out.println(entry.getKey() + ": " + entry.getValue());
        totalWords += entry.getValue();
      }
      System.out.println("\nTotal words: " + totalWords);
      System.out.println("Unique words: " + map.size());

    }
  }
}