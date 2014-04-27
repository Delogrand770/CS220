
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 SpellCheck -

 @author C14Gavin.Delphia
 @version 1.0 - Apr 6, 2012 at 1:45:17 PM
 */
public class SpellCheck {

  public static void main(String[] args) {
    try {
      javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
      System.err.println("Problem setting UI.");
    }

    Scanner dictData = FileDialogs.selectInputFile(null);
    TreeSet<String> dict = new TreeSet();

    if (dictData != null) {

      while (dictData.hasNext()) {
        dict.add(dictData.next());
      }
    }

    Scanner fileData = FileDialogs.selectInputFile(null);
    TreeMap<String, Integer> missData = new TreeMap();

    if (fileData != null) {
      while (fileData.hasNext()) {
        //if any chars are not a-z or A-Z remove them
        String next = fileData.next().replaceAll("[^a-zA-Z]", "").toLowerCase();

        if (next.length() > 0) {

          int count = 0;
          if (!dict.contains(next)) {

            if (missData.containsKey(next)) {
              count = missData.get(next);
            }

            missData.put(next, count + 1);
          }
        }
      }

      int totalWords = 0;
      for (Map.Entry<String, Integer> entry : missData.entrySet()) {
        String time = entry.getValue() > 1 ? " times" : " time";
        System.out.println("Mistake: " + entry.getKey() + " - " + entry.getValue() + time);
        totalWords += entry.getValue();
      }

      System.out.println("\nTotal mistakes: " + totalWords);
      System.out.println("Unique mistakes: " + missData.size());
    }
  }
}