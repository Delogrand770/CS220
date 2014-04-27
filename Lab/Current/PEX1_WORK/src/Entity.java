
/**
 Description: The parent class of all creatures.

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:33:40 AM
 */
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Entity {

  private BufferedImage image;
  private double x;
  private double y;
  private double z;
  private double scale;
  private double speed;

  public Entity(BufferedImage image) {
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

    // Randomly, with 0.5 probability, invert speed so some swimming entities
    // are moving left-to-right and some are moving right-to-left.
    if (Math.random() < 0.5) {
      this.speed *= -1;
    }
  }

  /**
   Controls what happens When an entity meets another entity.
   */
  public void meet() {
  }

  /**
   entity move both horizontally and vertically and turn around when they
   reach an edge of the aquarium.
   */
  public void move() {
    //Do Nothing
  }

  /**
   This method paints an entity using the given Graphics object and the
   entities BufferedImage object.

   @param g The Graphics object to use to paint this entity.
   */
  public void paint(Graphics g) {
    // A entitys speed determines if it is facing left or right.
    if (this.speed > 0) {
      // Positive speed indicates the entity is facing right.
      g.drawImage(this.image, (int) x, (int) y,
                  (int) (x + this.image.getWidth() * scale),
                  (int) (y + this.image.getHeight() * scale), 0, 0,
                  this.image.getWidth(), this.image.getHeight(), null);
    } else {
      // Negative speed indicates the entity is facing left. Notice the
      // second and fourth paramters, which are the x-coordinates of the
      // image being drawn, are inverted from the right-facing image above.
      g.drawImage(this.image, (int) (x + this.image.getWidth() * scale),
                  (int) y, (int) x, (int) (y + this.image.getHeight() * scale),
                  0, 0, this.image.getWidth(), this.image.getHeight(), null);
    }
  }

  /**
   Sets the x position of this entity, which must be between
   -Aquarium.WIDTH and Aquarium.WIDTH * 2. The dimensions of the
   aquarium are actually a "window" into an aquarium. entity are allowed
   to swim or crawl outside this window before turning around to return.
   The value is still error checked to ensure nothing accidentally
   escapes the aquarium completely.

   @param x New x position for this entity.
   */
  public void setX(double x) {
    if (x < -Aquarium.WIDTH || x > Aquarium.WIDTH * 2) {
      throw new IllegalArgumentException("x is out of range: " + x);
    }
    this.x = x;
  }

  /**
   Sets the y position of this entity, which must be between
   -Aquarium.HEIGHT and Aquarium.HEIGHT * 2. The dimensions of the
   aquarium are actually a "window" into an aquarium. entity are allowed
   to swim or crawl outside this window before turning around to return.
   The value is still error checked to ensure nothing accidentally
   escapes the aquarium completely.

   @param y New y position for this entity.
   */
  public void setY(double y) {
    if (y < -Aquarium.HEIGHT || y > Aquarium.HEIGHT * 2) {
      throw new IllegalArgumentException("y is out of range: " + y);
    }
    this.y = y;
  }

  /**
   Sets the y position of this entity, which must be between
   -Aquarium.HEIGHT and Aquarium.HEIGHT * 2. The dimensions of the
   aquarium are actually a "window" into an aquarium. entity are allowed
   to swim or crawl outside this window before turning around to return.
   The value is still error checked to ensure nothing accidentally
   escapes the aquarium completely.

   NOTE: The depth of the aquarium is the distance from the back to the
   front of the aquarium, not the water depth. Thus, this value
   indicates how close to the front of the aquarium an entity is, which
   determines the scale to be used when drawing the entity image.

   @param z New z position for this entity.
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
   Sets the speed of this entity, which must be between 0.5 and 2.0,
   positive or negative.

   @param speed New speed position for this entity.
   */
  public void setSpeed(double speed) {
    if (Math.abs(speed) < 0.5 || Math.abs(speed) > 2.0) {
      throw new IllegalArgumentException("speed is out of range: " + speed);
    }
    this.speed = speed;
  }

  /**
  Sets the scale of this entity
  @param scale  New scale for this entity.
  */
  public void setScale(double scale) {
    this.scale = scale;
  }

  /**
   Accessor method for the x position of this entity. This value will be
   between -image.getWidth() and Aquarium.WIDTH.

   @return The current x position of this entity.
   */
  public double getX() {
    return this.x;
  }

  /**
   Accessor method for the y position of this entity. This value will be
   between 0 and Aquarium.HEIGHT. Subclasses may restrict this range.

   @return The current y position of this entity.
   */
  public double getY() {
    return this.y;
  }

  /**
   Accessor method for the z position of this entity. This value will be
   between 0 and Aquarium.DEPTH.

   @return The current z position of this entity.
   */
  public double getZ() {
    return this.z;
  }

  /**
   Accessor method for the speed of this entity. This value will be
   between 0.5 and 2.0, positive or negative.

   @return The current speed of this entity.
   */
  public double getSpeed() {
    return this.speed;
  }

  /**
   Accessor method for the width of this entity. It is determined by the
   current scale of the entity and the width of the entity image.

   @return The current width of this entity.
   */
  public int getWidth() {
    return (int) (this.image.getWidth() * scale);
  }

  /**
   Accessor method for the height of this entity. It is determined by the
   current scale of the entity and the height of the entity image.

   @return The current height of this entity.
   */
  public int getHeight() {
    return (int) (this.image.getHeight() * scale);
  }

  /**
   Accessor method for the depth of this entity. It is determined by the
   current scale of the entity and is somewhat arbitrarily set to
   one-half of the height of the height of the entity.

   NOTE: The depth of an entity is its "thickness" or the amount of space
   it occupies front to back in the aquarium.

   @return The current width of this entity.
   */
  public int getDepth() {
    return (int) (this.image.getHeight() / 2);
  }

  /**
   Accessor method for the scale of this entity. This value is based on
   the z position of the entity and will be between 0.0 and 1.0.

   @return The current scale of this entity.
   */
  public double getScale() {
    return this.scale;
  }

  /**
   Creates and returns a string representation of this entity that
   includes the (x,y,z) position and the scale and speed of the entity.

   @return A string representation of this entity.
   */
  @Override
  public String toString() {
    return this.getClass().getName() + " is at ("
           + this.x + ", " + this.y + ", " + this.z + "), scale: "
           + this.scale + ", speed: " + this.speed;
  }
}
