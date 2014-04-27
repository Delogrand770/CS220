
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Description: Demonstrates use of the FileDialogs class.
 *
 * @author Randall.Bower
 */
public class FileDialogsDemo {

  public static void main(String[] args) {
    // Make GUI look like normal operating system GUI rather than
    // Java's default six-year-old-with-a-crayon look.
    try {
      javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());

      // For reference, this would show all of the installed options:
      // for( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() )
      // {
      //   System.out.println( info.getName() + " " + info.getClassName() );
      // }
    } catch (Exception e) {
      // Ignore exceptions and continue; if this fails for some reason, the GUI
      // will still open with default Java, six-year-old-with-a-crayon look.
      System.err.println("Problem setting UI.");
    }

    testInputFile();

    //testOutputFile();
  }

  public static void testInputFile() {
    Scanner fileScanner = FileDialogs.selectInputFile(null);

    // If the user clicks the Cancel button, fileScanner will be null.
    if (fileScanner != null) {
      while (fileScanner.hasNextLine()) {
        System.out.println(fileScanner.nextLine());
      }
    }
  }

  public static void testOutputFile() {
    PrintStream out = FileDialogs.selectOutputFile(null);

    out.println("This is a test.");
    out.println("This is only a test.");
  }
}
