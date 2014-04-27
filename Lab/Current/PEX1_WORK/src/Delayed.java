
/**
 Description: The parent class of all entities that have delayed movement.

 @author C14Gavin.Delphia
 @version 1.0 - Jan 31, 2012 at 3:32:27 PM
 */
import java.awt.image.BufferedImage;

public class Delayed extends Crawling {

  private int delay;

  public Delayed(BufferedImage image) {
    super(image);
  }

  /**
   Sets the delay, or time this crawling entity should stutter.

   @param delay New delay for this stutterer.
   */
  public void setDelay(int delay) {
    this.delay = delay;
  }

  /**
   Accessor method for the delay, or time this crawling entity should
   stutter.

   @return The current delay for this stutterer.
   */
  public int getDelay() {
    return this.delay;
  }

  /**
   entity move both horizontally and vertically and turn around when
   they reach an edge of the aquarium.
   */
  @Override
  public void move() {
    super.move();
  }
}