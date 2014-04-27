
/**
 * Description: Circle class extending Ellipse and overriding the resize
 * method to ensure the circle's width and height are always equal.
 *
 * @author Randall.Bower
 */
import java.awt.Color;
import java.awt.Point;

public class Circle extends Ellipse
{
  /**
   * Default constructor creates a blue circle of default size.
   * Why blue? Why not?!
   */
  public Circle()
  {
    this( Color.BLUE, new Point(), DEFAULT_SIZE );
  }
  
  /**
   * One parameter constructor specifying the size of this circle.
   * Uses blue as the default color. Why blue? Why not?!
   * 
   * @param size Size of this circle.
   */
  public Circle( int size )
  {
    this( Color.BLUE, new Point(), size );
  }
  
  /**
   * Three parameter constructor specifying color, center, and size.
   * 
   * @param c Color of this circle.
   * @param p Center of this circle.
   * @param size Size of this circle.
   */
  public Circle( Color c, Point p, int size )
  {
    super( c, p, size, size );
  }

  /**
   * Resizes this circle with respect to the point p. The width and height are
   * set to always remain the same ensuring the circleness of this shape.
   * 
   * @param p The point to resize this circle to.
   */
  @Override
  public void resize( Point p )
  {
    this.horizontalAxis = Math.max( Math.abs( center.x - p.x ) * 2, Math.abs( center.y - p.y ) * 2 );
    this.verticalAxis = this.horizontalAxis;
  }
}
