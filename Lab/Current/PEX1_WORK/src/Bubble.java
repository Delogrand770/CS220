
/**
 Description: Bubbles exist in the aquarium and float straight up.

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:47:26 AM
 */
import java.awt.image.BufferedImage;

public class Bubble extends Entity {

  public Bubble(BufferedImage image) {
    // All bubbles move at the same speed.
    super(image);

    // All bubbles move at the same speed.
    this.setSpeed(2.0);
    //Overide the random facing direction
    if (Math.random() < 0.5) {
    }
  }

  /**
   entity move both horizontally and vertically and turn around when
   they reach an edge of the aquarium.
   */
  @Override
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
   Controls what happens When an bubble meets another entity.
   */
  @Override
  public void meet() {
    //left blank
  }
}
