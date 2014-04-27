
/**
 Description: The parent class of all entities that swim in the upper half of the tank.

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:43:46 AM
 */
import java.awt.image.BufferedImage;

public class Swimming extends Entity {

  private double delta;

  public Swimming(BufferedImage image) {
    super(image);

    // All swimming entities are in the top 3/4 of tank. Subtract this
    // height to ensure the bottom of the swimming entitiy doesn't go below the
    // upper two-thirds of the aquarium. Use Math.max to ensure swimming entity
    // is not off the top of the aquarium after subtracting the height.
    this.setY(Math.max(Math.random() * Aquarium.HEIGHT * 0.75
                       - this.getHeight(), 0));

    // Start swimming horizontally.
    this.delta = 0.0;
  }

  /**
   entity move both horizontally and vertically and turn around when
   they reach an edge of the aquarium.
   */
  @Override
  public void move() {
    // Move the swimmer horizontally, based on its speed.
    this.setX(this.getX() + this.getSpeed());

    // If the swimming entity has moved out of the aquarium's window, it turns
    // around and re-enters either at a different depth, front to back.
    if (this.getX() < -this.getWidth() || this.getX() > Aquarium.WIDTH) {
      // Inverting the speed turns the swimmer around.
      this.setSpeed(this.getSpeed() * -1);

      // The change in depth will be between 1 and 2 "thicknesses" of
      // the swimming entity. See comment in Aquarium about depth.
      double dz = this.getDepth() * (1 + Math.random() * 2);
      if (Math.random() < 0.5) {
        // Move toward the front, but no more than the depth of the aquarium.
        this.setZ(Math.min(this.getZ() + dz, Aquarium.DEPTH));
      } else {
        // Move toward the back, but no less than the back of the aquarium.
        this.setZ(Math.max(this.getZ() - dz, 0));
      }
    }

    // If swimming entity is at the top and moving up or at the bottom and
    // moving down, invert delta before moving y.
    if (this.getY() < 0 && this.getDelta() < 0
        || this.getY() + this.getHeight() > Aquarium.HEIGHT * 0.75) {
      this.setDelta(this.getDelta() * -1.0);
    }

    // Now go ahead and move the y position.
    this.setY(this.getY() + this.getDelta());
  }

  /**
   Sets the delta, or vertical speed, of this swimming entity.

   @param delta New delta, or vertical speed, for this swimming entity.
   */
  public void setDelta(double delta) {
    this.delta = delta;
  }

  /**
   Accessor method for the delta, or vertical speed, of this swimming
   entity.

   @return The current delta, or vertical speed, of this swimming entity.
   */
  public double getDelta() {
    return this.delta;
  }
}
