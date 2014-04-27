
import java.awt.image.BufferedImage;

/**
 Description: Angelfish swim across the aquarium always moving at a slight
 angle either upward or downward. When they meet another creature, they
 reverse direction.

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:49:24 AM
 */
public class Angelfish extends Swimming {

  public Angelfish(BufferedImage image) {
    super(image);

    // Random value between 0.25 and 0.5.
    this.setDelta(Math.random() * 0.25 + 0.25);

    // With a probability of 0.5, invert delta.
    if (Math.random() < 0.5) {
      this.setDelta(this.getDelta() * -1.0);
    }
  }

  /**
   Controls what happens When an Angelfish meets another entity.
   */
  @Override
  public void meet() {
    // Inverting the speed reverses the direction.
    this.setSpeed(this.getSpeed() * -1.0);
  }
}
