
/**
 Description: The parent class of all entities that crawl in the lower half of the tank.

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:43:51 AM
 */
import java.awt.image.BufferedImage;

public class Crawling extends Entity {

  public Crawling(BufferedImage image) {
    super(image);

    // All crawling entities move from the front to the back of the tank, so their
    // y position is determined by their z position. The z position was
    // set randomly above, so call the local version of setZ here in
    // order to ensure Y is set properly.
    this.setZ(this.getZ());
  }

  /**
   entity move both horizontally and vertically and turn around when
   they reach an edge of the aquarium.
   */
  @Override
  public void move() {
    // Move the crawler forward or backward based on its speed.
    this.setZ(this.getZ() + this.getSpeed());

    // If the crab has moved out of the aquarium's window, it turns around.
    if (this.getZ() < 0 || this.getZ() > Aquarium.DEPTH) {
      this.setSpeed(this.getSpeed() * -1);
    }

    // If it has gone off the bottom of the aquarium's window, it moves
    // randomly to the right or left.
    if (this.getZ() > Aquarium.DEPTH) {
      // The change in x position will be between 1 and 2 widths
      // of the crab.
      double dx = this.getWidth() * (1 + Math.random() * 2);
      if (Math.random() < 0.5) {
        // Move right, but no more than the width of the aquarium.
        this.setX(Math.min(this.getX() + dx, Aquarium.WIDTH));
      } else {
        // Move left, but no less than the back of the aquarium.
        this.setX(Math.max(this.getX() - dx, 0));
      }
    }
  }

  /**
   Sets the y position of this crawling entity, which must be between
   -Aquarium.HEIGHT and Aquarium.HEIGHT * 2. The dimensions of the
   aquarium are actually a "window" into an aquarium. crawling entities
   are allowed to swim or crawl outside this window before turning
   around to return. The value is still error checked to ensure nothing
   accidentally escapes the aquarium completely.

   NOTE: The depth of the aquarium is the distance from the back to the
   front of the aquarium, not the water depth. Thus, this value
   indicates how close to the front of the aquarium a crawling entity
   is, which determines the scale to be used when drawing the crawling
   entity's image.

   @param z New z position for this crawling entity.
   */
  @Override
  public void setZ(double z) {
    if (z < -Aquarium.DEPTH || z > Aquarium.DEPTH * 2) {
      throw new IllegalArgumentException("z is out of range: " + z);
    }
    super.setZ(z);

    // The z position determines the scale.
    this.setScale(0.5 + this.getZ() / Aquarium.DEPTH / 2.0);

    // The y position of a lobster must be in the bottom quarter of the
    // aquarium. Where in the bottom quarter is relative to the z position.
    super.setY(Aquarium.HEIGHT * 0.75 + Aquarium.HEIGHT * 0.25 * this.getZ() / Aquarium.DEPTH);
  }

  /**
  y for crawling creatures is not ever set this way so it is overridden.
   @param y
   */
  public void setY(double y) {
    //left blank
  }
}
