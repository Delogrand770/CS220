
/**
 * Description: Rectangle class containing width and height characteristics
 * of a rectangle with center point and color inherited from Shape.
 *
 * @author Randall.Bower
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

public class Rectangle extends Shape {

  protected int width;  // Width of this rectangle.
  protected int height; // Height of this rectangle.

  /**
   * Default constructor creates a blue rectangle of default size.
   * Why blue? Why not?!
   */
  public Rectangle() {
    this(Color.BLUE, new Point(), DEFAULT_SIZE, DEFAULT_SIZE);
  }

  /**
   * Two parameter constructor specifying the width and height of this rectangle.
   * Uses blue as the default color. Why blue? Why not?!
   * 
   * @param w Width of this rectangle.
   * @param h Height of this rectangle.
   */
  public Rectangle(int w, int h) {
    this(Color.BLUE, new Point(), w, h);
  }

  /**
   * Four parameter constructor specifying color, center, width, and height.
   * 
   * @param c Color of this rectangle.
   * @param p Center of this rectangle.
   * @param w Width of this rectangle.
   * @param h Height of this rectangle.
   */
  public Rectangle(Color c, Point p, int w, int h) {
    super(c, p);
    this.width = w;
    this.height = h;
  }

  /**
   * Calculates and returns the area of this rectangle.
   * 
   * @return The area of this rectangle.
   */
  @Override
  public double area() {
    //return this.width * this.height;
    return Math.abs(this.width * this.height);
  }

  /**
   * Determines of the given point is contained by this rectangle.
   * 
   * @param p Point to be tested for containment in this rectangle.
   * @return True if this rectangle contains the point; false otherwise.
   */
  @Override
  public boolean contains(Point p) {
//    return p.x >= this.center.x - this.width / 2 && p.x <= this.center.x + this.width / 2 &&
//           p.y >= this.center.y - this.width / 2 && p.y <= this.center.y + this.width / 2;

    
    //when checking the y axis width was used instead of height
    return p.x >= this.center.x - this.width / 2 && p.x <= this.center.x + this.width / 2
           && p.y >= this.center.y - this.height / 2 && p.y <= this.center.y + this.height / 2;
  }

  /**
   * Draws this rectangle.
   * 
   * @param g Graphics to use to draw this rectangle.
   */
  @Override
  public void draw(Graphics g) {
    g.setColor(this.color);
    g.fillRect(this.center.x - this.width / 2, this.center.y - this.height / 2, this.width, this.height);
  }

  /**
   * Highlights this rectangle.
   * 
   * @param g Graphics to use to highlight this rectangle.
   */
  @Override
  public void highlight(Graphics g) {
    g.setColor(Color.BLACK);
    g.drawRect(this.center.x - this.width / 2, this.center.y - this.height / 2, this.width, this.height);
  }

  /**
   * Resizes this rectangle with respect to the point p. The width and height are
   * set based on the distance of the given point from the rectangle's center.
   * 
   * @param p The point to resize this rectangle to.
   */
  @Override
  public void resize(Point p) {
    this.width = Math.abs(this.center.x - p.x) * 2;
    this.height = Math.abs(this.center.y - p.y) * 2;
  }

  /**
   * Creates and returns a string representation of this rectangle.
   * 
   * @return A string representation of this rectangle.
   */
  @Override
  public String toString() {
    return super.toString() + this.width + " " + this.height;
  }

  /**
   * Creates a rectangle from a string (actually, a scanner parsing a string.)
   * This method is used when loading shapes from a file.
   * 
   * @param scan Scanner created from a string representation of a rectangle.
   */
  @Override
  public void fromString(Scanner scan) {
    super.fromString(scan);
    this.width = scan.nextInt();
    this.height = scan.nextInt();
  }
}
