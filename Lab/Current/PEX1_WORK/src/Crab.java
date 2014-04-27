
/**
 Description: Crabs crawl straight forward and backward in the bottom
 of the aquarium, randomly pausing for a short period. When they meet
 another creature, they scurry away quickly, moving horizontally at
 twice their normal speed for a short period.

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:50:38 AM
 */
import java.awt.image.BufferedImage;

public class Crab extends Delayed {

  private int scurryTime;
  private double savedSpeed;

  public Crab(BufferedImage image) {
    super(image);

    // A crab is initially moving, so no delay.
    this.setDelay(0);

    // A crab is initially not scurrying, so no scurry time.
    this.scurryTime = 0;
  }

  /**
   Controls what happens When an Crab meets another entity.
   */
  @Override
  public void meet() {
    // If it's not already scurrying, it's time to scurry!
    if (scurryTime <= 0) {
      // Randomly set the time the crab will scurry away at double
      // speed between 32 and 48. Why 32 and 48? Why not?!
      this.scurryTime = (int) (Math.random() * 16 + 32);

      // Save the current speed for when the crab is done scurrying.
      this.savedSpeed = this.getSpeed();

      // Invert and double the speed, but do not exceed the speed limit.
      if (this.getSpeed() > 0) {
        this.setSpeed(Math.max(this.getSpeed() * -2.0, -2.0));
      } else {
        this.setSpeed(Math.min(this.getSpeed() * -2.0, 2.0));
      }
    }
  }

  /**
   entity move both horizontally and vertically and turn around when
   they reach an edge of the aquarium.
   */
  @Override
  public void move() {

    if (this.getDelay() > 0) {
      this.setDelay(this.getDelay() - 1);
    } else {
      super.move();

      // If the crab is scurrying, it needs to move horizontally.
      if (this.scurryTime > 0) {
        // Move the swimmer horizontally, based on its speed.
        this.setX(this.getX() + this.getSpeed());

        // If the crab has moved out of the aquarium's window, turns around.
        if (this.getX() < -this.getWidth() || this.getX() > Aquarium.WIDTH) {
          this.setSpeed(this.getSpeed() * -1);
        }

        // Decrement scurry time and reset speed to normal if done scurrying.
        this.scurryTime--;
        if (scurryTime == 0) {
          this.setSpeed(this.savedSpeed);
        }
      } else { // If not scurrying, pause about one out of every twenty moves.
        if (Math.random() < 0.05) {
          // Randomly set delay between 12 and 16. Why 12 and 16? Why not?!
          this.setDelay((int) (Math.random() * 4 + 12));
        }
      }
    }
  }
}
