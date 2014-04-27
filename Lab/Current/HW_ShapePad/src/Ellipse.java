
/**
 * Description: Ellipse class containing axis characteristics
 * of an ellipse with center point and color inherited from Shape.
 *
 * @author Randall.Bower
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

public class Ellipse extends Shape {

  protected int horizontalAxis, verticalAxis;

  /**
   * Default constructor creates a blue ellipse of default size.
   * Why blue? Why not?!
   */
  public Ellipse() {
    this(Color.BLUE, new Point(), DEFAULT_SIZE, DEFAULT_SIZE);
  }

  /**
   * Two parameter constructor specifying the width and height of this ellipse.
   * Uses blue as the default color. Why blue? Why not?!
   * 
   * @param w Width of this ellipse.
   * @param h Height of this ellipse.
   */
  public Ellipse(int w, int h) {
    this(Color.BLUE, new Point(), w, h);
  }

  /**
   * Four parameter constructor specifying color, center, width, and height.
   * 
   * @param c Color of this ellipse.
   * @param p Center of this ellipse.
   * @param w Width of this ellipse.
   * @param h Height of this ellipse.
   */
  public Ellipse(Color c, Point p, int w, int h) {
    super(c, p);
    this.horizontalAxis = w;
    this.verticalAxis = h;
  }

  /**
   * Calculates and returns the area of this ellipse.
   * 
   * @return The area of this ellipse.
   */
  @Override
  public double area() {
    return Math.PI * this.horizontalAxis / 2 * this.verticalAxis / 2;
  }

  /**
   * Determines of the given point is contained by this ellipse.
   * 
   * @param p Point to be tested for containment in this ellipse.
   * @return True if this ellipse contains the point; false otherwise.
   */
  @Override
  public boolean contains(Point p) {
    // From: http://mathforum.org/library/drmath/view/63045.html
    // A point x,y is inside an ellipse if the ellipse is in standard position and
    // (x^2 / a^2) + (y^2 / b^2) < 1 where a is the semi-major axis and b is the semi-minor axis.
    double a = horizontalAxis / 2.0;
    double b = verticalAxis / 2.0;
    // Standard position assumes the ellipse is at 0,0.
    double x = p.x - center.x;
    double y = p.y - center.y;
    //return ((x * x) / (a * a)) + ((y * y) / (b * b)) < 2.0;
    
    //Incorrect equation. Changed < 2.0 to < 1.0
    return ((x * x) / (a * a)) + ((y * y) / (b * b)) < 1.0;
  }

  /**
   * Draws this ellipse.
   * 
   * @param g Graphics to use to draw this ellipse.
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(this.color);
    g.fillOval(this.center.x - this.horizontalAxis / 2, this.center.y - this.verticalAxis / 2, this.horizontalAxis, this.verticalAxis);
  }

  /**
   * Highlights this ellipse.
   * 
   * @param g Graphics to use to highlight this ellipse.
   */
  @Override
  public void highlight(Graphics g) {
    g.setColor(Color.BLACK);
    g.drawOval(this.center.x - this.horizontalAxis / 2, this.center.y - this.verticalAxis / 2, this.horizontalAxis, this.verticalAxis);
  }

  /**
   * Resizes this rectangle with respect to the point p. The width and height are
   * set based on the distance of the given point from the rectangle's center.
   * 
   * @param p The point to resize this rectangle to.
   */
  @Override
  public void resize(Point p) {
    this.horizontalAxis = Math.abs(center.x - p.x) * 2;
    this.verticalAxis = Math.abs(center.y - p.y) * 2;
  }

  /**
   * Creates and returns a string representation of this ellipse.
   * 
   * @return A string representation of this ellipse.
   */
  @Override
  public String toString() {
    return super.toString() + this.horizontalAxis + " " + this.verticalAxis;
  }

  /**
   * Creates an ellipse from a string (actually, a scanner parsing a string.)
   * This method is used when loading shapes from a file.
   * 
   * @param scan Scanner created from a string representation of an ellipse.
   */
  @Override
  public void fromString(Scanner scan) {
    super.fromString(scan);
    this.horizontalAxis = scan.nextInt();
    this.verticalAxis = scan.nextInt();
  }
}
