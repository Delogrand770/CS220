
/**
 * File: Bubble.java
 *
 * Description: Bubbles exist in the aquarium and float straight up.
 *
 * @author Randall.Bower
 * @author YOUR NAME HERE
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bubble {

  /** Image representing this bubble to be drawn on the aquarium. */
  private BufferedImage image;
  /** Current x position of this bubble. */
  private double x;
  /** Current y position of this bubble. */
  private double y;
  /** Current z position of this bubble. */
  private double z;
  /**
   * The scale of a bubble is determined by its distance from the front
   * of the aquarium window. See note in Aquarium about Aquarium.DEPTH.
   * It is determined by the z position of the bubble and is set whenever
   * the z position is changed. It must be between 0.5 and 1.0.
   */
  private double scale;
  /**
   * Speed this bubble is moving.
   */
  private double speed;

  /**
   * This constructor creates a new Bubble with the given image. It is
   * not possible or desirable to create a Bubble without an image, so
   * there is no zero-parameter constructor. Bubble properties are set
   * to random values within the bounds of the aquarium, but the speed
   * of all bubbles is the same.
   * 
   * @param image BufferedImage object representing this bubble.
   */
  public Bubble(BufferedImage image) {
    this.image = image;

    // The x, y, and z coordinates are random within the aquarium.
    // Subclasses may impose further restrictions.
    this.x = Math.random() * Aquarium.WIDTH - this.image.getWidth();
    this.y = Math.random() * Aquarium.HEIGHT - this.image.getHeight();
    this.z = Math.random() * Aquarium.DEPTH;

    // Scale depends on the z coordinate. See comment above.
    this.scale = 0.5 + this.z / Aquarium.DEPTH / 2.0;

    // All bubbles move at the same speed.
    this.speed = 2.0;
  }

  /**
   * Bubbles move straight up and start over at a random location
   * on the bottom of the aquarium when the reach the top.
   */
  public void move() {
    // Bubbles move straight up, faster than anything else.
    this.setY(this.getY() - this.getSpeed() * 2.0);

    // When a bubble reaches the top, reset it to a random location
    // at the bottom of the aquarium.
    if (this.getY() < 0) {
      this.setX(Math.random() * Aquarium.WIDTH);
      this.setY(Aquarium.HEIGHT); // Bottom of the aquarium.
      this.setZ(Math.random() * Aquarium.DEPTH);
    }
  }

  /**
   * This method paints a bubble using the given Graphics object and
   * the bubble's BufferedImage object.
   * 
   * @param g The Graphics object to use to paint this bubble.
   */
  public void paint(Graphics g) {
    g.drawImage(this.image, (int) x, (int) y,
                (int) (x + this.image.getWidth() * scale),
                (int) (y + this.image.getHeight() * scale),
                0, 0, this.image.getWidth(), this.image.getHeight(), null);
  }

  /**
   * Sets the x position of this bubble, which must be between
   * -Aquarium.WIDTH and Aquarium.WIDTH * 2. The dimensions of the aquarium
   * are actually a "window" into an aquarium. Bubbles are allowed to
   * float outside this window before being reset.
   * The value is still error checked to ensure nothing accidentally
   * escapes the aquarium completely.
   * 
   * @param x New x position for this bubble.
   */
  public void setX(double x) {
    if (x < -Aquarium.WIDTH || x > Aquarium.WIDTH * 2) {
      throw new IllegalArgumentException("x is out of range: " + x);
    }
    this.x = x;
  }

  /**
   * Sets the y position of this bubble, which must be between
   * -Aquarium.HEIGHT and Aquarium.HEIGHT * 2. The dimensions of the aquarium
   * are actually a "window" into an aquarium. Bubbles are allowed to
   * float outside this window before being reset.
   * The value is still error checked to ensure nothing accidentally
   * escapes the aquarium completely.
   * 
   * @param y New y position for this bubble.
   */
  public void setY(double y) {
    if (y < -Aquarium.HEIGHT || y > Aquarium.HEIGHT * 2) {
      throw new IllegalArgumentException("y is out of range: " + y);
    }
    this.y = y;
  }

  /**
   * Sets the y position of this bubble, which must be between
   * -Aquarium.HEIGHT and Aquarium.HEIGHT * 2. The dimensions of the aquarium
   * are actually a "window" into an aquarium. Bubbles are allowed to
   * float outside this window before being reset.
   * The value is still error checked to ensure nothing accidentally
   * escapes the aquarium completely.
   * 
   * NOTE: The depth of the aquarium is the distance from the back
   * to the front of the aquarium, not the water depth. Thus, this
   * value indicates how close to the front of the aquarium a bubble
   * is, which determines the scale to be used when drawing the
   * bubble's image.
   * 
   * @param z New z position for this bubble.
   */
  public void setZ(double z) {
    if (z < -Aquarium.DEPTH || z > Aquarium.DEPTH * 2) {
      throw new IllegalArgumentException("z is out of range: " + z);
    }
    this.z = z;
    this.scale = 0.5 + this.z / Aquarium.DEPTH / 2.0;
  }

  /**
   * Sets the speed of this bubble, which must be between 0.5 and 2.0,
   * positive or negative.
   *
   * @param speed New speed for this bubble.
   */
  public void setSpeed(double speed) {
    if (Math.abs(speed) < 0.5 || Math.abs(speed) > 2.0) {
      throw new IllegalArgumentException("speed is out of range: " + speed);
    }
    this.speed = speed;
  }

  /**
   * Accessor method for the x position of this bubble. This value will
   * be between -image.getWidth() and Aquarium.WIDTH.
   * 
   * @return The current x position of this bubble.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Accessor method for the y position of this bubble. This value will
   * be between 0 and Aquarium.HEIGHT. Subclasses may restrict this range.
   * 
   * @return The current y position of this bubble.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Accessor method for the z position of this bubble. This value will
   * be between 0 and Aquarium.DEPTH.
   * 
   * @return The current z position of this bubble.
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Accessor method for the speed of this bubble. This value will be set
   * to 4.0 for all bubbles, twice the speed of the fastest creature.
   *
   * @return The current speed of this bubble.
   */
  public double getSpeed() {
    return this.speed;
  }

  /**
   * Accessor method for the scale of this bubble. This value is based
   * on the z position of the bubble and will be between 0.0 and 1.0.
   * 
   * @return The current scale of this bubble.
   */
  public double getScale() {
    return this.scale;
  }

  /**
   * Accessor method for the width of this bubble. It is determined by
   * the current scale of the bubble and the width of the bubble's image.
   * 
   * @return The current width of this bubble.
   */
  public int getWidth() {
    return (int) (this.image.getWidth() * scale);
  }

  /**
   * Accessor method for the height of this bubble. It is determined by
   * the current scale of the bubble and the height of the bubble's image.
   * 
   * @return The current height of this bubble.
   */
  public int getHeight() {
    return (int) (this.image.getHeight() * scale);
  }

  /**
   * Accessor method for the depth of this bubble. It is determined by
   * the current scale of the bubble and is somewhat arbitrarily set
   * to one-half of the height of the height of the bubble.
   * 
   * NOTE: The depth of a bubble is its "thickness" or the amount of
   * space it occupies front to back in the aquarium.
   * 
   * @return The current width of this bubble.
   */
  public int getDepth() {
    return (int) (this.image.getHeight() / 2);
  }

  /**
   * Creates and returns a string representation of this bubble that
   * includes the (x,y,z) position and the scale and speed of the bubble.
   * 
   * @return A string representation of this bubble.
   */
  @Override
  public String toString() {
    return this.getClass().getName() + " is at ("
           + this.x + ", " + this.y + ", " + this.z + "), scale: "
           + this.scale + ", speed: " + this.speed;
  }
}
