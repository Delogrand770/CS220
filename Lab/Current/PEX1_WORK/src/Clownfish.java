
/**
 Description: Clownfish swim straight across the aquarium. When they meet
 another creature, for a short time they angle downward if they are moving
 left-to-right and upward if they are moving right-to-left.

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:48:17 AM
 */
import java.awt.image.BufferedImage;

public class Clownfish extends Swimming {

  private int angleTime;

  public Clownfish(BufferedImage image) {
    super(image);

    // Initially not swimming at an angle.
    this.angleTime = 0;
  }

  /**
   Controls what happens When an Clownfish meets another entity.
   */
  @Override
  public void meet() {
    // If not already moving at an angle, do so now.
    if (angleTime <= 0) {
      // Random value between 16 and 24. Why 16 and 24? Why not?!
      this.angleTime = (int) (Math.random() * 8) + 16;

      // The delta, or vertical speed, is set to the same as the horizontal
      // speed. The parameter to setDelta uses what is called a ternary
      // operator. If necessary, use that magical Internet thingy to find
      // more details about its use.
      this.setDelta(this.getSpeed() > 0 ? this.getSpeed() : -this.getSpeed());
    }
  }

  /**
   entity move both horizontally and vertically and turn around when
   they reach an edge of the aquarium.
   */
  @Override
  public void move() {
    super.move();

    // Decrease angle time if necessary.
    if (this.angleTime > 0) {
      this.angleTime--;

      // Stop swilling at an angle when angle time runs out.
      if (this.angleTime <= 0) {
        this.setDelta(0.0);
      }
    }
  }
}
