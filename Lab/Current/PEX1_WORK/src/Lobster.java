
/**
 Description: Lobsters crawl straight forward and backward in the bottom
 of the aquarium. Each time they move forward or backward in the aquarium,
 they also move slightly left or right with a probability of 0.25. Thus,
 half of the time they only move forward or backward and not horizontally.
 When a lobster meets another creature, it pauses for a short period.

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:51:41 AM
 */
import java.awt.image.BufferedImage;

public class Lobster extends Delayed {

  public Lobster(BufferedImage image) {
    super(image);

    // A lobster is initially moving, so no delay.
    this.setDelay(0);
  }

  /**
   Controls what happens When an Lobster meets another entity.
   */
  @Override
  public void meet() {
    // Don't delay again if already delayed. (This would happen if two
    // lobsters are on top of each other, repeatedly delaying.
    if (this.getDelay() <= 0) {
      // Randomly set delay between 8 and 12. Why 8 and 12? Why not?!
      this.setDelay((int) (Math.random() * 4 + 8));
    }
  }

  /**
   entity move both horizontally and vertically and turn around when
   they reach an edge of the aquarium.
   */
  @Override
  public void move() {
    // A lobster only moves if it is not currently delaying.
    if (this.getDelay() > 0) {
      this.setDelay(this.getDelay() - 1);
    } else {
      super.move();

      // If the lobster moved horizontally, then move it vertically as
      // described above.
      if (this.getDelay() <= 0) {
        // Switch with a random value of [0,3].
        switch ((int) (Math.random() * 4)) {
          case 0: // Move left, but make sure to stay in the aquarium.
            this.setX(Math.max(this.getX() - this.getSpeed(), 0));
            break;
          case 1: // Move right, but make sure to stay above the bottom.
            this.setX(Math.min(this.getX() + this.getSpeed(), Aquarium.WIDTH - this.getWidth()));
            break;
          default: // Do nothing to the horizontal position.
            break;
        }
      }
    }
  }
}
