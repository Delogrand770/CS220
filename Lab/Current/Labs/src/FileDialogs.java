
import java.awt.Component;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 Description: This file contains two utility methods for selecting files
 for input or output via the JFileChooser dialog.

 @author Randall.Bower
 */
public class FileDialogs {

  public static JFileChooser chooser = new JFileChooser(".");

  /**
   Use a dialog box to select a text or java file for input.

   @return A Scanner for the selected file, or null if user selects
   Cancel.
   */
  public static Scanner selectInputFile(Component parent) {
    do {
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showOpenDialog(parent);
      try {
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          return new Scanner(chooser.getSelectedFile());
        } else {
          return null;
        }
      } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(parent, "Invalid file!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    } while (true);
  }

  /**
   Use a dialog box to select a text file for output.

   Note: This does NOT enforce the given extension. By default the file
   chooser will only show ".g" files, but the user must include the
   extension in the file name selected/typed.

   @return A PrintStream for the selected file, or null if user selects
   Cancel.
   */
  public static PrintStream selectOutputFile(Component parent) {
    do {
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Grammar Files", "g");
      chooser.setFileFilter(filter);
      int returnVal = chooser.showSaveDialog(parent);
      try {
        if (returnVal == JFileChooser.APPROVE_OPTION) {
          return new PrintStream(chooser.getSelectedFile());
        } else {
          return null;
        }
      } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(parent, "Invalid file!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    } while (true);
  }
}
