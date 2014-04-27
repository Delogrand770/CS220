
/**
 * Description: Triangle class extending Shape and containing three points
 * to determine the vertices of a triangle. Center is inherited from Shape,
 * but is determined by the three vertices. Color is also inherited from
 * Shape.
 *
 * @author Randall.Bower
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Scanner;

public class Triangle extends Shape {
  // The center point inherited from Shape will remain the center
  // while these three points represent the vertices of the triangle.

  private Point a, b, c;
  // These two arrays contain redundant information, but are necessary
  // to make drawing easier. Declared here rather than in the draw method
  // to avoid needing to re-allocate them each time draw is called.
  private int[] x;
  private int[] y;

  /**
   * Default constructor creates a triangle with a new point.
   */
  public Triangle() {
    this(new Point());
  }

  /**
   * One parameter constructor creates a right triangle of default size
   * with the given point in the lower-left corner, which is a rather
   * arbitrary choice.
   * 
   * @param p Point to be used as lower-left corner of triangle.
   */
  public Triangle(Point p) {
    // If only one point is specified, start with a reasonable looking right triangle.
    // Double DEFAULT_SIZE just because it seems to look better and make each vertex
    // easier to grab to resize.
    this(new Point(p.x, p.y - DEFAULT_SIZE * 2), new Point(p.x - DEFAULT_SIZE * 2, p.y), new Point(p.x, p.y));
  }

  /**
   * Three parameter constructor specifies each vertex from which the center is calculated.
   * Uses a default color of blue. Why blue? Why not?!
   * 
   * @param a First vertex of this triangle.
   * @param b Second vertex of this triangle.
   * @param c Third vertex of this triangle.
   */
  public Triangle(Point a, Point b, Point c) {
    // If three vertices are given, calculate the center as the average of the x,y coordinates.
    this(Color.BLUE, new Point((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3), a, b, c);
  }

  /**
   * Four parameter constructor specifies color and all three vertices, from which the center
   * will be calculated.
   * 
   * @param color Color of this triangle.
   * @param a First vertex of this triangle.
   * @param b Second vertex of this triangle.
   * @param c Third vertex of this triangle.
   */
  public Triangle(Color color, Point a, Point b, Point c) {
    // If three vertices are given, calculate the center as the average of the x,y coordinates.
    this(color, new Point((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3), a, b, c);
  }

  /**
   * Five parameter constructor specifies color, center, and all three vertices.
   * 
   * @param color Color of this triangle.
   * @param center Center point of this triangle.
   * @param a First vertex of this triangle.
   * @param b Second vertex of this triangle.
   * @param c Third vertex of this triangle.
   */
  public Triangle(Color color, Point center, Point a, Point b, Point c) {
    super(color, center);
    this.a = new Point(a);
    this.b = new Point(b);
    this.c = new Point(c);
    this.x = new int[3];
    this.y = new int[3];
  }

  /**
   * Calculates and returns the area of this triangle.
   * 
   * @return The area of this rectangle.
   */
  @Override
  public double area() {
    return Math.abs((a.x * (b.y - c.y)
                     + b.x * (c.y - a.y)
                     + c.x * (a.y - b.y)) / 2.0);
  }

  /**
   * Determines of the given point is contained by this triangle.
   * 
   * @param p Point to be tested for containment in this triangle.
   * @return True if this triangle contains the point; false otherwise.
   */
  @Override
  public boolean contains(Point p) {
    // If a point P is inside a triangle ABC, then the sum of the areas of
    // PAB, PBC, and PCA must equal the area of ABC (to a tolerance of 0.0001).
    return Math.abs(this.area() - (new Triangle(p, a, b).area()
                                   + new Triangle(p, b, c).area()
                                   + new Triangle(p, c, a).area())) < 0.0001;
  }

  /**
   * Draws this triangle.
   * 
   * @param g Graphics to use to draw this triangle.
   */
  @Override
  public void draw(Graphics g) {
    this.x[ 0] = a.x;
    this.y[ 0] = a.y;
    this.x[ 1] = b.x;
    this.y[ 1] = b.y;
    this.x[ 2] = c.x;
    this.y[ 2] = c.y;

    g.setColor(this.color);
    g.fillPolygon(x, y, 3);
  }

  /**
   * Highlights this triangle.
   * 
   * @param g Graphics to use to highlight this triangle.
   */
  @Override
  public void highlight(Graphics g) {
    this.x[ 0] = a.x;
    this.y[ 0] = a.y;
    this.x[ 1] = b.x;
    this.y[ 1] = b.y;
    this.x[ 2] = c.x;
    this.y[ 2] = c.y;

    g.setColor(Color.BLACK);
    g.drawPolygon(x, y, 3);
  }

  /**
   * Sets the center of this triangle to the given point, moving all three
   * vertices by the same amount the center moves.
   * 
   * @param p The new center of this triangle.
   */
  @Override
  public void setCenter(Point p) {
    int dx = p.x - this.center.x;
    int dy = p.y - this.center.y;
    this.a.translate(dx, dy);
    this.b.translate(dx, dy);
    this.c.translate(dx, dy);
    this.center.translate(dx, dy);
  }

  /**
   * Resizes this triangle with respect to the point p. Each vertex of a triangle
   * can be moved individually to resize the triangle. This method determines the
   * vertex closest to the given point and moves that vertex to the point.
   * 
   * @param p The point to resize this triangle to.
   */
  @Override
  public void resize(Point p) {
    // Find the vertex closest to p and move it to p.
    if (p.distance(a) < p.distance(b) && p.distance(a) < p.distance(c)) {
      a.setLocation(p);
    } else if (p.distance(b) < p.distance(c)) {
      b.setLocation(p);
    } else {
      c.setLocation(p);
    }

    // Re-calculate the center.
    center.setLocation((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3);
  }

  /**
   * Creates and returns a string representation of this triangle.
   * 
   * @return A string representation of this triangle.
   */
  @Override
  public String toString() {
    return super.toString() + a.x + " " + a.y + " " + b.x + " " + b.y + " " + c.x + " " + c.y;
  }

  /**
   * Creates a triangle from a string (actually, a scanner parsing a string.)
   * This method is used when loading shapes from a file.
   * 
   * @param scan Scanner created from a string representation of a triangle.
   */
  @Override
  public void fromString(Scanner scan) {
    super.fromString(scan);
    this.a.setLocation(scan.nextInt(), scan.nextInt());
    this.b.setLocation(scan.nextInt(), scan.nextInt());
    this.c.setLocation(scan.nextInt(), scan.nextInt());
  }
}
