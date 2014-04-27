
/**
 * Description: This application uses a hierarchy of Shape classes to
 * implement a simple shape drawing tool.
 *
 * @author Randall.Bower
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ShapePad
{
  // To add shapes to the ShapePad, create the class in the Shape hierarchy
  // with appropriate functionality and add the name of the class to this array.
  private static final String[] SHAPE_NAMES = { "Ellipse", "Circle", "Rectangle", "Square", "Triangle" };
  // Six possible colors for shapes seems sufficient. To add more, simply add
  // them to this array. None of the code below should need modified.
  private static final Color[] SHAPE_COLORS = { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN, Color.MAGENTA, Color.YELLOW };
    
  private JFrame shapeFrame; // Main application window.
  private ShapePanel shapePanel; // Inner JPanel in the window.
  
  private ArrayList<Shape> shapeList; // Keeps track of all shapes currently drawn.
  private Shape activeShape;          // The shape currently being manipulated.
  private String newShapeName;        // Reflects the current selection in the Shape menu of the GUI.
  private Color newShapeColor;        // Reflects the current selection in the Color menu of the GUI.

  /**
   * The constructor creates the ArrayList to hold the shapes, sets initial values for the other
   * GUI related variables, and of course, builds the GUI.
   */
  public ShapePad()
  {
    shapeList = new ArrayList();
    activeShape = null;
    newShapeName = SHAPE_NAMES[ 0 ];
    newShapeColor = SHAPE_COLORS[ 0 ];
    
    shapeFrame = new JFrame( "Shape Pad" );
    shapeFrame.setLocation( 42, 42 );
    shapeFrame.setSize( 640, 480 );
    shapeFrame.setForeground( Color.WHITE );
    shapeFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
    shapePanel = new ShapePanel();
    ShapePanelListener listener = new ShapePanelListener();
    shapePanel.addMouseListener( listener );
    shapePanel.addMouseMotionListener( listener );
    shapeFrame.add( shapePanel );
    
    createFileMenu();
    createShapeMenu();
    createColorMenu();
    createHelpMenu();
    
    JMenuBar menuBar = new JMenuBar();
    menuBar.add( createFileMenu() );
    menuBar.add( createShapeMenu() );
    menuBar.add( createColorMenu() );
    menuBar.add( createHelpMenu() );
    shapeFrame.setJMenuBar( menuBar );
    
    shapeFrame.setVisible( true );
  }
  
  private JMenu createFileMenu()
  {
    FileMenuListener listener = new FileMenuListener();
    
    JMenu fileMenu = new JMenu( "File" );
    fileMenu.setMnemonic( KeyEvent.VK_F );

    JMenuItem menuItem = new JMenuItem( "New Drawing", KeyEvent.VK_N );
    menuItem.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_N, ActionEvent.ALT_MASK ) );
    menuItem.addActionListener( listener );
    fileMenu.add( menuItem );
   
    fileMenu.addSeparator();
    menuItem = new JMenuItem( "Open Drawing...", KeyEvent.VK_O );
    menuItem.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK ) );
    menuItem.addActionListener( listener );
    fileMenu.add( menuItem );

    menuItem = new JMenuItem( "Save Drawing...", KeyEvent.VK_S );
    menuItem.setAccelerator( KeyStroke.getKeyStroke( KeyEvent.VK_S, ActionEvent.CTRL_MASK ) );
    menuItem.addActionListener( listener );
    fileMenu.add( menuItem );

    fileMenu.addSeparator();
    menuItem = new JMenuItem( "Exit", KeyEvent.VK_X );
    menuItem.addActionListener( listener );
    fileMenu.add( menuItem );
    
    return fileMenu;
  }
  
  private JMenu createShapeMenu()
  {
    ShapeMenuListener listener = new ShapeMenuListener();
    
    JMenu shapeMenu = new JMenu( "Shape" );
    shapeMenu.setMnemonic( KeyEvent.VK_S );

    // Do the first one outside the loop so it can be set as selected.
    ButtonGroup shapeButtonGroup = new ButtonGroup();
    JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem( SHAPE_NAMES[ 0 ] );
    rbMenuItem.addActionListener( listener );
    rbMenuItem.setMnemonic( SHAPE_NAMES[ 0 ].charAt( 0 ) );
    rbMenuItem.setSelected( true );
    shapeButtonGroup.add( rbMenuItem );
    shapeMenu.add( rbMenuItem );
    
    // Start loop at 1 since first item was added above.
    for( int i = 1; i < SHAPE_NAMES.length; i++ )
    {
      rbMenuItem = new JRadioButtonMenuItem( SHAPE_NAMES[ i ] );
      rbMenuItem.addActionListener( listener );
      rbMenuItem.setMnemonic( SHAPE_NAMES[ i ].charAt( 0 ) );
      shapeButtonGroup.add( rbMenuItem );
      shapeMenu.add( rbMenuItem );
    }
    
    return shapeMenu;
  }
  
  private JMenu createColorMenu()
  {
    ColorMenuListener listener = new ColorMenuListener();
    
    JMenu colorMenu = new JMenu( "Color" );
    colorMenu.setMnemonic( KeyEvent.VK_C );

    // Do the first one outside the loop so it can be set as selected.
    ButtonGroup colorButtonGroup = new ButtonGroup();
    JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem( "        " );
    rbMenuItem.addActionListener( listener );
    rbMenuItem.setBackground( SHAPE_COLORS[ 0 ] );
    rbMenuItem.setSelected( true );
    colorButtonGroup.add( rbMenuItem );
    colorMenu.add( rbMenuItem );
    
    // Start loop at 1 since first item was added above.
    for( int i = 1; i < SHAPE_COLORS.length; i++ )
    {
      rbMenuItem = new JRadioButtonMenuItem( "        " );
      rbMenuItem.addActionListener( listener );
      rbMenuItem.setBackground( SHAPE_COLORS[ i ] );
      colorButtonGroup.add( rbMenuItem );
      colorMenu.add( rbMenuItem );
    }
    
    return colorMenu;
  }
  
  private JMenu createHelpMenu()
  {
    HelpMenuListener listener = new HelpMenuListener();
    
    JMenu helpMenu = new JMenu( "Help" );
    helpMenu.setMnemonic( KeyEvent.VK_H );

    JMenuItem menuItem = new JMenuItem( "Creating Shapes...", KeyEvent.VK_C );
    helpMenu.add( menuItem );
    menuItem.addActionListener( listener );
    
    menuItem = new JMenuItem( "Deleting Shapes...", KeyEvent.VK_D );
    helpMenu.add( menuItem );
    menuItem.addActionListener( listener );
    
    menuItem = new JMenuItem( "Moving Shapes...", KeyEvent.VK_M );
    helpMenu.add( menuItem );
    menuItem.addActionListener( listener );
    
    menuItem = new JMenuItem( "Sizing Shapes...", KeyEvent.VK_S );
    helpMenu.add( menuItem );
    menuItem.addActionListener( listener );
    
    menuItem = new JMenuItem( "Coloring Shapes...", KeyEvent.VK_O );
    helpMenu.add( menuItem );
    menuItem.addActionListener( listener );
    
    helpMenu.addSeparator();
    menuItem = new JMenuItem( "About...", KeyEvent.VK_A );
    helpMenu.add( menuItem );
    menuItem.addActionListener( listener );
    
    return helpMenu;
  }

  class ShapePanelListener implements MouseListener, MouseMotionListener
  {
    // The following five methods are necessary to implement MouseListener.
    @Override
    public void mouseClicked( MouseEvent me )
    {
    }

    @Override
    public void mousePressed( MouseEvent me )
    {
      // Find the shape clicked upon.
      activeShape = null;
      for( Shape s : shapeList )
      {
        // If shapes overlap, this may be true more than once resulting
        // in the most recent shape being set as the active shape.
        if( s.contains( me.getPoint() ) )
        {
          activeShape = s;
        }
      }
      
      // Holding shift while clicking on a blank area creates a new shape.
      if( activeShape == null && me.isShiftDown() )
      {
        try
        {
          activeShape = (Shape)Class.forName( newShapeName ).newInstance();
          activeShape.setColor( newShapeColor );
          activeShape.setCenter( me.getPoint() );
          shapeList.add( activeShape );
        }
        catch( ClassNotFoundException ex )
        {
          System.err.println( "Could not find class " + newShapeName + "." );
        }
        catch( InstantiationException ex )
        {
          System.err.println( "Class " + newShapeName + " must not be abstract." );
        }
        catch( IllegalAccessException ex )
        {
          System.err.println( "Class " + newShapeName + " must have a zero-argument constructor." );
        }
      }
      
      // Holding CTRL while clicking on a shape deletes that shape.
      else if( activeShape != null && me.isControlDown() )
      {
        if( activeShape != null )
        {
          shapeList.remove( activeShape );
          activeShape = null;
        }
      }
      shapePanel.repaint();
    }
    
    @Override
    public void mouseReleased( MouseEvent me )
    {
    }

    @Override
    public void mouseEntered( MouseEvent me )
    {
    }

    @Override
    public void mouseExited( MouseEvent me )
    {
    }

    // The following two methods are necessary to implement MouseMotionListener.
    @Override
    public void mouseDragged( MouseEvent me )
    {
      if( activeShape != null )
      {
        if( me.isShiftDown() )
        {
          activeShape.resize( me.getPoint() );
        }
        else
        {
          activeShape.setCenter( me.getPoint() );
        }
        shapePanel.repaint();
      }
    }

    @Override
    public void mouseMoved( MouseEvent me )
    {
    }
  }
  
  class FileMenuListener implements ActionListener
  {
    @Override
    public void actionPerformed( ActionEvent ae )
    {
      // Switch statements with Strings is new in JDK 7.
      switch( ae.getActionCommand() )
      {
        case "New Drawing" :
          activeShape = null;
          shapeList.clear();
          shapePanel.repaint();
          break;
        case "Open Drawing..." :
          loadShapesFromFile();
          shapePanel.repaint();
          break;
        case "Save Drawing..." :
          saveShapesToFile();
          break;
        case "Exit" :
          System.exit( 0 );
          break;
      }
    }
  }

  class ShapeMenuListener implements ActionListener
  {
    @Override
    public void actionPerformed( ActionEvent ae )
    {
      // When a shape is selected from the Shapes menu, set the value of
      // newShapeName to that string so it is ready the next time a new
      // shape is created. Look in mousePressed of ShapePanelListener to
      // see how this is used.
      newShapeName = ae.getActionCommand();
    }
  }
  
  class ColorMenuListener implements ActionListener
  {
    @Override
    public void actionPerformed( ActionEvent ae )
    {
      // When a color is selected from the Colors menu, set the value of
      // newShapeColor to that color so it is ready the next time a new
      // shape is created. Look in mousePressed of ShapePanelListener to
      // see how this is used.
      newShapeColor = ((JRadioButtonMenuItem)ae.getSource()).getBackground();

      // If a shape was set as the active shape when the color was selected,
      // change the shape of that color.
      if( activeShape != null )
      {
        activeShape.setColor( newShapeColor );
        shapePanel.repaint();
      }
    }
  }
  
  class HelpMenuListener implements ActionListener
  {
    @Override
    public void actionPerformed( ActionEvent ae )
    {
      String message = null;
      // Switch statements with Strings is new in JDK 7.
      switch( ae.getActionCommand() )
      {
        case "Creating Shapes..." :
          message = "Select the desired shape from the Shape menu, hold down the\n" +
                    "Shift key, and click in an empty space on the drawing area.\n\n";
          break;
        case "Deleting Shapes..." :
          message = "Hold down the CTRL key and click on a shape.";
          break;
        case "Moving Shapes..." :
          message = "Click on a shape and drag to desired location.";
          break;
        case "Sizing Shapes..." :
          message = "Hold down the Shift key, click on the desired shape,\n" +
                    "and drag to resize the shape.\n\n" +
                    "For Triangles, hold down the Shift key, click on a\n" +
                    "vertex, and drag to move that vertex.\n\n";
          break;
        case "Coloring Shapes..." :
          message = "Click on a shape to select it, then use the Color menu\n" +
                    "to select desired color.\n\n" +
                    "Subsequently created shapes will also be this color.\n\n";
          break;
        case "About..." :
          message = "PEX 5, Shape Pad\nCS 210, Fall 2011\nDr. Randy Bower\n\nDocumentation: None.\n\n";
          break;
      }
      JOptionPane.showMessageDialog( shapeFrame, message, ae.getActionCommand(), JOptionPane.INFORMATION_MESSAGE );
    }
  }
  
  /**
   * This inner class extends JPanel and will draw all of the shapes.
   */
  class ShapePanel extends JPanel
  {
    public ShapePanel()
    {
      super();
      setBackground( Color.WHITE );
    }

    @Override
    public void paintComponent( Graphics g )
    {
      super.paintComponent( g );
      for( Shape s : shapeList )
      {
        s.draw( g );
      }
      if( activeShape != null )
      {
        // Re-draw the active shape, so it will be on top.
        activeShape.draw( g );
        
        // Each shape can highlight itself differently ...
        activeShape.highlight( g );
        
        // ... but also draw a white dot at the shape's center.
        g.setColor( Color.WHITE );
        Point c = activeShape.getCenter();
        g.fillOval( c.x - 2, c.y - 2, 4, 4 );
      }
    }
  }

  public static void main( String[] args )
  {
    // Note: I do NOT expect students to worry about the LookAndFeel!!!
    
    // Make GUI look like normal operating system GUI rather than
    // Java's default six-year-old-with-a-crayon look.
    try
    {
      // Ideally, this would be done with the default system look and feel.
      // Unfortunately, Windows 7 uses the Nimbus look and feel which has its
      // own defaults and a different way to define colors. For details, see:
      // http://stackoverflow.com/questions/3476834/cant-modify-systemlookandfeel-under-windows-7
      // http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
      // http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/color.html
      // 
      // End result is this will not allow the colorMenu to display background
      // colors on the menu items:
      //   UIManager.setLookAndFeel( UIManager.getSystemLookAndFeelClassName() );
      // so we'll use this instead:
      
      UIManager.setLookAndFeel( "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel" );
      
      // For reference, this would show all of the installed options.
      // for( LookAndFeelInfo info : UIManager.getInstalledLookAndFeels() )
      // {
      //   System.out.println( info.getName() + " " + info.getClassName() );
      // }
    }
    catch( Exception e )
    {
      // Ignore exceptions and continue; if this fails for some reason, the GUI
      // will still open with default Java, six-year-old-with-a-crayon look.
      System.err.println( "Problem setting UI." );
    }
    
    // This solution uses an ArrayList, defined at the bottom of the file to
    // hide it from students. These lines are included here just to get rid
    // of the "not used" messages from the declaration above.
    ShapePad s = new ShapePad();
  }

  /**
   * Loads shapes from a file and adds them to whatever shapes have already
   * been drawn on the ShapePad.
   */
  private void loadShapesFromFile()
  {
    Scanner fileScan = selectInputFile();
    if( fileScan != null )
    {
      String className = null;
      try
      {
        while( fileScan.hasNextLine() )
        {
          Scanner lineScan = new Scanner( fileScan.nextLine() );
          className = lineScan.next();
          Shape s = (Shape)Class.forName( className ).newInstance();
          s.fromString( lineScan );
          shapeList.add( s );
        }
      }
      catch( ClassNotFoundException ex )
      {
        System.err.println( "Could not find class " + className + "." );
      }
      catch( InstantiationException ex )
      {
        System.err.println( "Class " + className + " must not be abstract." );
      }
      catch( IllegalAccessException ex )
      {
        System.err.println( "Class " + className + " must have a zero-argument constructor." );
      }
    }
  }
  
  /**
   * Saves all shapes currently on the ShapePad to a file.
   */
  private void saveShapesToFile()
  {
    PrintStream out = selectOutputFile();
    if( out != null )
    {
      for( Shape s : shapeList )
      {
        out.println( s );
      }
      out.close();
    }
  }
  
  /**
   * Use a dialog box to select a Shape Pad file (.pad extension).
   * 
   * @return A Scanner for the selected file, or null if user selects Cancel.
   */
  private static Scanner selectInputFile()
  {
    do
    {
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter( "Shape Pad Files", "pad" );
      chooser.setFileFilter( filter );
      int returnVal = chooser.showOpenDialog( null );
      try
      {
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
          return new Scanner( chooser.getSelectedFile() );
        }
        else
        {
          return null;
        }
      } catch( FileNotFoundException e )
      {
        JOptionPane.showMessageDialog( null, "Invalid file!", "Error", JOptionPane.ERROR_MESSAGE );
      }
    } while( true );
  }
  
  /**
   * Use a dialog box to select a Shape Pad file (.pad extension).
   * 
   * Note: This does NOT enforce the ".pad" extension. By default the file
   * chooser will only show ".pad" files, but the user must include the
   * extension in the file name selected/typed.
   * 
   * @return A PrintStream for the selected file, or null if user selects Cancel.
   */
  private static PrintStream selectOutputFile()
  {
    do
    {
      JFileChooser chooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter( "Shape Pad Files", "pad" );
      chooser.setFileFilter( filter );
      int returnVal = chooser.showSaveDialog( null );
      try
      {
        if( returnVal == JFileChooser.APPROVE_OPTION )
        {
          return new PrintStream( chooser.getSelectedFile() );
        }
        else
        {
          return null;
        }
      } catch( FileNotFoundException e )
      {
        JOptionPane.showMessageDialog( null, "Invalid file!", "Error", JOptionPane.ERROR_MESSAGE );
      }
    } while( true );
  }
}
