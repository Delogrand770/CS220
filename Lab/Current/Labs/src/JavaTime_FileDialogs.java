
import java.util.Scanner;

/**
 * JavaTime_FileDialogs - 
 *
 * @author C14Gavin.Delphia
 *
 * @version 1.0 - Jan 23, 2012 at 2:05:01 PM
 */
public class JavaTime_FileDialogs {

  public static void main(String[] args) {
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
    JT_nameage a = getInputFile();
    JT_nameage b = getInputFile();
    System.out.println(a);
    System.out.println(b);
  }

  public static JT_nameage getInputFile() {
    Scanner fileScanner = FileDialogs.selectInputFile(null);

    // If the user clicks the Cancel button, fileScanner will be null.
    String name = "";
    int age = -1;
    if (fileScanner != null) {
      name = fileScanner.next();
      age = fileScanner.nextInt();
    }
    return new JT_nameage(age, name);
  }
}
