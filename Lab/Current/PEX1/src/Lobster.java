
/**
 * File: Lobster.java
 *
 * Description: Lobsters crawl straight forward and backward in the bottom
 * of the aquarium. Each time they move forward or backward in the aquarium,
 * they also move slightly left or right with a probability of 0.25. Thus,
 * half of the time they only move forward or backward and not horizontally.
 * When a lobster meets another creature, it pauses for a short period.
 *
 * @author Randall.Bower
 * @author YOUR NAME HERE
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Lobster {

  /** Image representing this lobster to be drawn on the aquarium. */
  private BufferedImage image;
  /** Current x position of this lobster. */
  private double x;
  /** Current y position of this lobster. */
  private double y;
  /** Current z position of this lobster. */
  private double z;
  /**
   * The scale of a lobster is determined by its distance from the front
   * of the aquarium window. See note in Aquarium about Aquarium.DEPTH.
   * It is determined by the z position of the lobster and is set whenever
   * the z position is changed. It must be between 0.5 and 1.0.
   */
  private double scale;
  /**
   * Speed this lobster is moving; positive values indicate lobster is
   * moving left-to-right, negative values indicate lobster is moving
   * right-to-left. Absolute value of speed must be between 0.5 and 2.0.
   */
  private double speed;
  /** Amount of time to delay moving when stuttering. */
  private int delay;

  /**
   * This constructor creates a new Lobster with the given image. It is
   * not possible or desirable to create an Lobster without an image, so
   * there is no zero-parameter constructor. Lobster properties are set
   * to random values within the bounds of the aquarium.
   * 
   * @param image BufferedImage object representing this lobster.
   */
  public Lobster(BufferedImage image) {
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

    // Randomly, with 0.5 probability, invert speed so some lobsters
    // are moving left-to-right and some are moving right-to-left.
    if (Math.random() < 0.5) {
      this.speed *= -1;
    }

    // All lobsters move from the front to the back of the tank, so their
    // y position is determined by their z position. The z position was
    // set randomly above, so call the local version of setZ here in
    // order to ensure Y is set properly.
    this.setZ(this.getZ());

    // A lobster is initially moving, so no delay.
    this.delay = 0;
  }

  /**
   * When a lobster meets another creature, it pauses for a short time.
   */
  public void meet() {
    // Don't delay again if already delayed. (This would happen if two
    // lobsters are on top of each other, repeatedly delaying.
    if (this.getDelay() <= 0) {
      // Randomly set delay between 8 and 12. Why 8 and 12? Why not?!
      this.setDelay((int) (Math.random() * 4 + 8));
    }
  }

  /**
   * Lobsters crawl straight forward and backward in the bottom
   * of the aquarium. Each time they move forward or backward in the aquarium,
   * they also move slightly left or right with a probability of 0.25. Thus,
   * half of the time they only move forward or backward and not horizontally.
   */
  public void move() {
    // A lobster only moves if it is not currently delaying.
    if (this.delay > 0) {
      this.delay--;
    } else {
      // Move the crawler forward or backward based on its speed.
      this.setZ(this.getZ() + this.getSpeed());

      // If the lobster has moved out of the aquarium's window, turns around.
      if (this.getZ() < 0 || this.getZ() > Aquarium.DEPTH) {
        this.setSpeed(this.getSpeed() * -1);
      }

      // If it has gone off the bottom of the aquarium's window, it moves
      // randomly to the right or left.
      if (this.getZ() > Aquarium.DEPTH) {
        // The change in x position will be between 1 and 2 widths
        // of the lobster.
        double dx = this.getWidth() * (1 + Math.random() * 2);
        if (Math.random() < 0.5) {
          // Move right, but no more than the width of the aquarium.
          this.setX(Math.min(this.getX() + dx, Aquarium.WIDTH));
        } else {
          // Move left, but no less than the back of the aquarium.
          this.setX(Math.max(this.getX() - dx, 0));
        }
      }

      // If the lobster moved horizontally, then move it vertically as
      // described above.
      if (this.getDelay() <= 0) {
        // Switch with a random value of [0,3].
        switch ((int) (Math.random() * 4)) {
          case 0: // Move left, but make sure to stay in the aquarium.
            this.setX(Math.max(this.getX() - this.getSpeed(), 0));
            break;
          case 1: // Move right, but make sure to stay above the bottom.
            this.setX(Math.min(this.getX() + this.getSpeed(),
                               Aquarium.WIDTH - this.getWidth()));
            break;
          default: // Do nothing to the horizontal position.
            break;
        }
      }
    }
  }

  /**
   * This method paints a lobster using the given Graphics object and
   * the lobster's BufferedImage object.
   *
   * @param g The Graphics object to use to paint this lobster.
   */
  public void paint(Graphics g) {
    // A lobster's speed determines if it is facing left or right.
    if (this.speed > 0) {
      // Positive speed indicates the lobster is facing right.
      g.drawImage(this.image, (int) x, (int) y,
                  (int) (x + this.image.getWidth() * scale),
                  (int) (y + this.image.getHeight() * scale), 0, 0,
                  this.image.getWidth(), this.image.getHeight(), null);
    } else {
      // Negative speed indicates the lobster is facing left. Notice the
      // second and fourth paramters, which are the x-coordinates of the
      // image being drawn, are inverted from the right-facing image above.
      g.drawImage(this.image, (int) (x + this.image.getWidth() * scale),
                  (int) y, (int) x, (int) (y + this.image.getHeight() * scale),
                  0, 0, this.image.getWidth(), this.image.getHeight(), null);
    }
  }

  /**
   * Sets the x position of this lobster, which must be between
   * -Aquarium.WIDTH and Aquarium.WIDTH * 2. The dimensions of the aquarium
   * are actually a "window" into an aquarium. Lobsters are allowed to
   * swim or crawl outside this window before turning around to return.
   * The value is still error checked to ensure nothing accidentally
   * escapes the aquarium completely.
   *
   * @param x New x position for this lobster.
   */
  public void setX(double x) {
    if (x < -Aquarium.WIDTH || x > Aquarium.WIDTH * 2) {
      throw new IllegalArgumentException("x is out of range: " + x);
    }
    this.x = x;
  }

  /**
   * A lobster's y position is determined by its z position and is set by setZ.
   *
   * @param y This value is ignored.
   */
  public void setY(double y) {
    // Do nothing.
  }

  /**
   * Sets the y position of this lobster, which must be between
   * -Aquarium.HEIGHT and Aquarium.HEIGHT * 2. The dimensions of the aquarium
   * are actually a "window" into an aquarium. Lobsters are allowed to
   * swim or crawl outside this window before turning around to return.
   * The value is still error checked to ensure nothing accidentally
   * escapes the aquarium completely.
   *
   * NOTE: The depth of the aquarium is the distance from the back
   * to the front of the aquarium, not the water depth. Thus, this
   * value indicates how close to the front of the aquarium a lobster
   * is, which determines the scale to be used when drawing the
   * lobster's image.
   *
   * @param z New z position for this lobster.
   */
  public void setZ(double z) {
    if (z < -Aquarium.DEPTH || z > Aquarium.DEPTH * 2) {
      throw new IllegalArgumentException("z is out of range: " + z);
    }
    this.z = z;

    // The z position determines the scale.
    this.scale = 0.5 + this.z / Aquarium.DEPTH / 2.0;

    // The y position of a lobster must be in the bottom quarter of the
    // aquarium. Where in the bottom quarter is relative to the z position.
    this.y = Aquarium.HEIGHT * 0.75 + Aquarium.HEIGHT * 0.25
                                      * this.getZ() / Aquarium.DEPTH;
  }

  /**
   * Sets the speed of this lobster, which must be between 0.5 and 2.0,
   * positive or negative.
   *
   * @param speed New speed position for this lobster.
   */
  public void setSpeed(double speed) {
    if (Math.abs(speed) < 0.5 || Math.abs(speed) > 2.0) {
      throw new IllegalArgumentException("speed is out of range: " + speed);
    }
    this.speed = speed;
  }

  /**
   * Sets the delay, or time this stutterer should stutter.
   *
   * @param delay New delay for this stutterer.
   */
  public void setDelay(int delay) {
    this.delay = delay;
  }

  /**
   * Accessor method for the x position of this lobster. This value will
   * be between -image.getWidth() and Aquarium.WIDTH.
   *
   * @return The current x position of this lobster.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Accessor method for the y position of this lobster. This value will
   * be between 0 and Aquarium.HEIGHT. Subclasses may restrict this range.
   *
   * @return The current y position of this lobster.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Accessor method for the z position of this lobster. This value will
   * be between 0 and Aquarium.DEPTH.
   *
   * @return The current z position of this lobster.
   */
  public double getZ() {
    return this.z;
  }

  /**
   * Accessor method for the speed of this lobster. This value will
   * be between 0.5 and 2.0, positive or negative.
   *
   * @return The current speed of this lobster.
   */
  public double getSpeed() {
    return this.speed;
  }

  /**
   * Accessor method for the scale of this lobster. This value is based
   * on the z position of the lobster and will be between 0.0 and 1.0.
   *
   * @return The current scale of this lobster.
   */
  public double getScale() {
    return this.scale;
  }

  /**
   * Accessor method for the delay, or time this stutterer should stutter.
   *
   * @return The current delay for this stutterer.
   */
  public int getDelay() {
    return this.delay;
  }

  /**
   * Accessor method for the width of this lobster. It is determined by
   * the current scale of the lobster and the width of the lobster's image.
   *
   * @return The current width of this lobster.
   */
  public int getWidth() {
    return (int) (this.image.getWidth() * scale);
  }

  /**
   * Accessor method for the height of this lobster. It is determined by
   * the current scale of the lobster and the height of the lobster's image.
   *
   * @return The current height of this lobster.
   */
  public int getHeight() {
    return (int) (this.image.getHeight() * scale);
  }

  /**
   * Accessor method for the depth of this lobster. It is determined by
   * the current scale of the lobster and is somewhat arbitrarily set
   * to one-half of the height of the height of the lobster.
   *
   * NOTE: The depth of a lobster is its "thickness" or the amount of
   * space it occupies front to back in the aquarium.
   *
   * @return The current width of this lobster.
   */
  public int getDepth() {
    return (int) (this.image.getHeight() / 2);
  }

  /**
   * Creates and returns a string representation of this lobster that
   * includes the (x,y,z) position and the scale and speed of the lobster.
   *
   * @return A string representation of this lobster.
   */
  @Override
  public String toString() {
    return this.getClass().getName() + " is at ("
           + this.x + ", " + this.y + ", " + this.z + "), scale: "
           + this.scale + ", speed: " + this.speed;
  }
}
