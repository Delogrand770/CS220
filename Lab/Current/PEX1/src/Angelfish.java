
/**
 * File: Angelfish.java
 *
 * Description: Angelfish swim across the aquarium always moving at a slight
 * angle either upward or downward. When they meet another creature, they
 * reverse direction.
 *
 * @author Randall.Bower
 * @author YOUR NAME HERE
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Angelfish {

  /** Image representing this angelfish to be drawn on the aquarium. */
  private BufferedImage image;
  /** Current x position of this angelfish. */
  private double x;
  /** Current y position of this angelfish. */
  private double y;
  /** Current z position of this angelfish. */
  private double z;
  /**
   * The scale of an angelfish is determined by its distance from the front
   * of the aquarium window. See note in Aquarium about Aquarium.DEPTH.
   * It is determined by the z position of the angelfish and is set whenever
   * the z position is changed. It must be between 0.5 and 1.0.
   */
  private double scale;
  /**
   * Speed this angelfish is moving; positive values indicate angelfish is
   * moving left-to-right, negative values indicate angelfish is moving
   * right-to-left. Absolute value of speed must be between 0.5 and 2.0.
   */
  private double speed;
  /**
   * Change in position for swimming at an angle.
   */
  private double delta;

  /**
   * This constructor creates a new Angelfish with the given image. It is
   * not possible or desirable to create an Angelfish without an image, so
   * there is no zero-parameter constructor. Angelfish properties are set
   * to random values within the bounds of the aquarium and the delta, or
   * vertical speed, is set to a small value to give a slight angle to
   * the Anglefish's movement.
   * 
   * @param image BufferedImage object representing this angelfish.
   */
  public Angelfish(BufferedImage image) {
    this.image = image;

    // The x, y, and z coordinates are random within the aquarium.
    // Subclasses may impose further restrictions.
    this.x = Math.random() * Aquarium.WIDTH - this.image.getWidth();
    this.y = Math.random() * Aquarium.HEIGHT - this.image.getHeight();
    this.z = Math.random() * Aquarium.DEPTH;

    // Scale depends on the z coordinate. See comment above.
    this.scale = 0.5 + this.z / Aquarium.DEPTH / 2.0;

    // Speed is a random value between 0.5 and 2.0.
    this.speed = 0.5 + Math.random() * 1.5;

    // Randomly, with 0.5 probability, invert speed so some angelfish
    // are moving left-to-right and some are moving right-to-left.
    if (Math.random() < 0.5) {
      this.speed *= -1;
    }

    // All angelfish are in the top 3/4 of tank. Subtract this 
    // height to ensure the bottom of the angelfish doesn't go below the
    // upper two-thirds of the aquarium. Use Math.max to ensure angelfish
    // is not off the top of the aquarium after subtracting the height.
    this.setY(Math.max(Math.random() * Aquarium.HEIGHT * 0.75
                       - this.getHeight(), 0));

    // Start swimming horizontally.
    this.delta = 0.0;

    // Random value between 0.25 and 0.5.
    this.setDelta(Math.random() * 0.25 + 0.25);

    // With a probability of 0.5, invert delta.
    if (Math.random() < 0.5) {
      this.setDelta(this.getDelta() * -1.0);
    }
  }

  /**
   * When an Anglefish meets another creature, it reverses direction.
   */
  public void meet() {
    // Inverting the speed reverses the direction.
    this.setSpeed(this.getSpeed() * -1.0);
  }

  /**
   * Angelfish move both horizontally and vertically and turn around
   * when they reach an edge of the aquarium.
   */
  public void move() {
    // Move the swimmer horizontally, based on its speed.
    this.setX(this.getX() + this.getSpeed());

    // If the angelfish has moved out of the aquarium's window, it turns
    // around and re-enters either at a different depth, front to back.
    if (this.getX() < -this.getWidth() || this.getX() > Aquarium.WIDTH) {
      // Inverting the speed turns the swimmer around.
      this.setSpeed(this.getSpeed() * -1);

      // The change in depth will be between 1 and 2 "thicknesses" of
      // the angelfish. See comment in Aquarium about depth.
      double dz = this.getDepth() * (1 + Math.random() * 2);
      if (Math.random() < 0.5) {
        // Move toward the front, but no more than the depth of the aquarium.
        this.setZ(Math.min(this.getZ() + dz, Aquarium.DEPTH));
      } else {
        // Move toward the back, but no less than the back of the aquarium.
        this.setZ(Math.max(this.getZ() - dz, 0));
      }
    }

    // If angelfish is at the top and moving up or at the bottom and
    // moving down, invert delta before moving y.
    if (this.getY() < 0 && this.getDelta() < 0
        || this.getY() + this.getHeight() > Aquarium.HEIGHT * 0.75) {
      this.setDelta(this.getDelta() * -1.0);
    }

    // Now go ahead and move the y position.
    this.setY(this.getY() + this.getDelta());
  }

  /**
   * This method paints an angelfish using the given Graphics object and
   * the angelfish's BufferedImage object.
   *
   * @param g The Graphics object to use to paint this angelfish.
   */
  public void paint(Graphics g) {
    // An angelfish's speed determines if it is facing left or right.
    if (this.speed > 0) {
      // Positive speed indicates the angelfish is facing right.
      g.drawImage(this.image, (int) x, (int) y,
                  (int) (x + this.image.getWidth() * scale),
                  (int) (y + this.image.getHeight() * scale), 0, 0,
                  this.image.getWidth(), this.image.getHeight(), null);
    } else {
      // Negative speed indicates the angelfish is facing left. Notice the
      // second and fourth paramters, which are the x-coordinates of the
      // image being drawn, are inverted from the right-facing image above.
      g.drawImage(this.image, (int) (x + this.image.getWidth() * scale),
                  (int) y, (int) x, (int) (y + this.image.getHeight() * scale),
                  0, 0, this.image.getWidth(), this.image.getHeight(), null);
    }
  }

  /**
   * Sets the x position of this angelfish, which must be between
   * -Aquarium.WIDTH and Aquarium.WIDTH * 2. The dimensions of the aquarium
   * are actually a "window" into an aquarium. Angelfish are allowed to
   * swim or crawl outside this window before turning around to return.
   * The value is still error checked to ensure nothing accidentally
   * escapes the aquarium completely.
   *
   * @param x New x position for this angelfish.
   */
  public void setX(double x) {
    if (x < -Aquarium.WIDTH || x > Aquarium.WIDTH * 2) {
      throw new IllegalArgumentException("x is out of range: " + x);
    }
    this.x = x;
  }

  /**
   * Sets the y position of this angelfish, which must be between
   * -Aquarium.HEIGHT and Aquarium.HEIGHT * 2. The dimensions of the aquarium
   * are actually a "window" into an aquarium. Angelfish are allowed to
   * swim or crawl outside this window before turning around to return.
   * The value is still error checked to ensure nothing accidentally
   * escapes the aquarium completely.
   *
   * @param y New y position for this angelfish.
   */
  public void setY(double y) {
    if (y < -Aquarium.HEIGHT || y > Aquarium.HEIGHT * 2) {
      throw new IllegalArgumentException("y is out of range: " + y);
    }
    this.y = y;
  }

  /**
   * Sets the y position of this angelfish, which must be between
   * -Aquarium.HEIGHT and Aquarium.HEIGHT * 2. The dimensions of the aquarium
   * are actually a "window" into an aquarium. Angelfish are allowed to
   * swim or crawl outside this window before turning around to return.
   * The value is still error checked to ensure nothing accidentally
   * escapes the aquarium completely.
   *
   * NOTE: The depth of the aquarium is the distance from the back
   * to the front of the aquarium, not the water depth. Thus, this
   * value indicates how close to the front of the aquarium an angelfish
   * is, which determines the scale to be used when drawing the
   * angelfish image.
   *
   * @param z New z position for this angelfish.
   */
  public void setZ(double z) {
    if (z < -Aquarium.DEPTH || z > Aquarium.DEPTH * 2) {
      throw new IllegalArgumentException("z is out of range: " + z);
    }
    this.z = z;

    // The z position determines the scale.
    this.scale = 0.5 + this.z / Aquarium.DEPTH / 2.0;
  }

  /**
   * Sets the speed of this angelfish, which must be between 0.5 and 2.0,
   * positive or negative.
   *
   * @param speed New speed position for this angelfish.
   */
  public void setSpeed(double speed) {
    if (Math.abs(speed) < 0.5 || Math.abs(speed) > 2.0) {
      throw new IllegalArgumentException("speed is out of range: " + speed);
    }
    this.speed = speed;
  }

  /**
   * Sets the delta, or vertical speed, of this angelfish.
   *
   * @param delta New delta, or vertical speed, for this angelfish.
   */
  public void setDelta(double delta) {
    this.delta = delta;
  }

  /**
   * Accessor method for the x position of this angelfish. This value will
   * be between -image.getWidth() and Aquarium.WIDTH.
   *
   * @return The current x position of this angelfish.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Accessor method for the y position of this angelfish. This value will
   * be between 0 and Aquarium.HEIGHT. Subclasses may restrict this range.
   *
   * @return The current y position of this angelfish.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Accessor method for the z position of this angelfish. This value will
   * be between 0 and Aquarium.DEPTH.
   *
   * @return The current z position of this angelfish.
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Accessor method for the speed of this angelfish. This value will
   * be between 0.5 and 2.0, positive or negative.
   *
   * @return The current speed of this angelfish.
   */
  public double getSpeed() {
    return this.speed;
  }

  /**
   * Accessor method for the scale of this angelfish. This value is based
   * on the z position of the angelfish and will be between 0.0 and 1.0.
   *
   * @return The current scale of this angelfish.
   */
  public double getScale() {
    return this.scale;
  }

  /**
   * Accessor method for the delta, or vertical speed, of this angelfish.
   *
   * @return The current delta, or vertical speed, of this angelfish.
   */
  public double getDelta() {
    return this.delta;
  }

  /**
   * Accessor method for the width of this angelfish. It is determined by
   * the current scale of the angelfish and the width of the angelfish image.
   *
   * @return The current width of this angelfish.
   */
  public int getWidth() {
    return (int) (this.image.getWidth() * scale);
  }

  /**
   * Accessor method for the height of this angelfish. It is determined by
   * the current scale of the angelfish and the height of the angelfish image.
   *
   * @return The current height of this angelfish.
   */
  public int getHeight() {
    return (int) (this.image.getHeight() * scale);
  }

  /**
   * Accessor method for the depth of this angelfish. It is determined by
   * the current scale of the angelfish and is somewhat arbitrarily set
   * to one-half of the height of the height of the angelfish.
   *
   * NOTE: The depth of an angelfish is its "thickness" or the amount of
   * space it occupies front to back in the aquarium.
   *
   * @return The current width of this angelfish.
   */
  public int getDepth() {
    return (int) (this.image.getHeight() / 2);
  }

  /**
   * Creates and returns a string representation of this angelfish that
   * includes the (x,y,z) position and the scale and speed of the angelfish.
   *
   * @return A string representation of this angelfish.
   */
  @Override
  public String toString() {
    return this.getClass().getName() + " is at ("
           + this.x + ", " + this.y + ", " + this.z + "), scale: "
           + this.scale + ", speed: " + this.speed;
  }
}
