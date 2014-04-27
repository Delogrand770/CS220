
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

/**
 * Description: Abstract Shape class containing center point and color
 *              to be inherited by all shapes.
 *
 * @author Randall.Bower
 */
public abstract class Shape {

  /** The default size of any shape. */
  protected static final int DEFAULT_SIZE = 8;
  /** The color of a shape. */
  protected Color color;
  /** The center point of a shape. */
  protected Point center;

  /**
   * Default constructor. Why blue? Why not?!
   */
  public Shape() {
    this(Color.BLUE, new Point());
  }

  /**
   * Two parameter constructor allows specification of color and center.
   * 
   * @param c Color of this shape.
   * @param p Center point of this shape.
   */
  public Shape(Color c, Point p) {
    this.color = c;
    this.center = new Point(p);
  }

  /**
   * Accessor method for the color of this shape.
   * 
   * @return The color of this shape.
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Mutator method for the color of this shape.
   * 
   * @param c The new color of this shape; cannot be null.
   */
  public void setColor(Color c) {
    if (c != null) {
      this.color = c;
    }
  }

  /**
   * Accessor method for the center of this shape.
   * 
   * @return The center of this shape.
   */
  public Point getCenter() {
    // Make a copy of the center point so client code cannot
    // actually modify the same point object.
    return new Point(this.center);
  }

  /**
   * Mutator method for the center of this shape.
   * 
   * @param p New center point for this shape; cannot be null.
   */
  public void setCenter(Point p) {
    /*
     * Q: Why does this method make a copy of the Point p, but the setColor
     *    method above does not make a copy of its parameter?
     * A: Because instances of the Color class are immutable (i.e., they do
     *    not have any "set" methods. Thus, client code with a pointer to
     *    this.color cannot change it. In contrast, client code with a pointer
     *    to this.center could change it using the setLocation methods.
     */
    if (p != null) {
      this.center = new Point(p);
    }
  }

  /**
   * All shapes must define how to calculate their area.
   * 
   * @return The area of this shape.
   */
  public abstract double area();

  /**
   * All shapes must determine if they contain a given point.
   * 
   * @param p Point to be tested for containment of this shape.
   * @return True if this shape contains the point; false otherwise.
   */
  public abstract boolean contains(Point p);

  /**
   * All shapes must define how they draw.
   * 
   * @param g The graphics object to use to draw the shape.
   */
  public abstract void draw(Graphics g);

  /**
   * All shapes must define how they highlight.
   * 
   * @param g The graphics object to use to highlight the shape.
   */
  public abstract void highlight(Graphics g);

  /**
   * All shapes must define how they resize with respect to a given point.
   * 
   * @param p The point this shape will resize to.
   */
  public abstract void resize(Point p);

  /**
   * Creates and returns a string representation of this shape.
   * 
   * @return A string representation of this shape.
   */
  @Override
  public String toString() {
    return this.getClass().getName() + " " + this.center.x + " " + this.center.y + " " + this.color.getRGB() + " ";
  }

  /**
   * Creates a shape from a string (actually, a scanner parsing a string.)
   * This method is used when loading shapes from a file.
   * 
   * @param scan Scanner created from a string representation of a shape.
   */
  public void fromString(Scanner scan) {
    this.center = new Point(scan.nextInt(), scan.nextInt());
    this.color = new Color(scan.nextInt());
  }
}
