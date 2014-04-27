
/**
 * Description: Square class extending Rectangle and overriding the resize
 * method to ensure the square's width and height are always equal.
 *
 * @author Randall.Bower
 */
import java.awt.Color;
import java.awt.Point;

public class Square extends Rectangle
{
  /**
   * Default constructor creates a blue square of default size.
   * Why blue? Why not?!
   */
  public Square()
  {
    this( Color.BLUE, new Point(), DEFAULT_SIZE );
  }
  
  /**
   * One parameter constructor specifying the size of this square.
   * Uses blue as the default color. Why blue? Why not?!
   * 
   * @param size Size of this square.
   */
  public Square( int size )
  {
    this( Color.BLUE, new Point(), size );
  }
  
  /**
   * Three parameter constructor specifying color, center, and size.
   * 
   * @param c Color of this square.
   * @param p Center of this square.
   * @param size Size of this square.
   */
  public Square( Color c, Point p, int size )
  {
    super( c, p, size, size );
  }

  /**
   * Resizes this square with respect to the point p. The width and height are
   * set to always remain the same ensuring the squareness of this shape.
   * 
   * @param p The point to resize this square to.
   */
  @Override
  public void resize( Point p )
  {
    this.width = Math.max( Math.abs( this.center.x - p.x ) * 2, Math.abs( this.center.y - p.y ) * 2 );
    this.height = this.width;
  }
}
