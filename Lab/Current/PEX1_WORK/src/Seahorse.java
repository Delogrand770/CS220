
/**
 Description: Seahorses swim across the aquarium in an up and down,
 zig-zag motion. Seahorses ignore all other creatures.

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:49:58 AM
 */
import java.awt.image.BufferedImage;

public class Seahorse extends Swimming {

  private double startY;

  public Seahorse(BufferedImage image) {
    super(image);

    // Save the starting position so the Seahorse knows when to zig or zag.
    this.startY = this.getY();

    // Set the delta, or vertical speed, to the same as the horizontal speed.
    this.setDelta(this.getSpeed());
  }

  /**
   entity move both horizontally and vertically and turn around when
   they reach an edge of the aquarium.
   */
  @Override
  public void move() {
    super.move();

    // The size of the zigzags is equal to one-half of the seahorse's height.
    if (Math.abs(this.getY() - this.startY) > this.getHeight() / 2.0) {
      this.setDelta(this.getDelta() * -1.0);
    }
  }
}
