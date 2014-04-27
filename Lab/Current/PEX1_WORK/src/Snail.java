
/**
 Description: Snails crawl straight forward and backward in the bottom
 of the aquarium, ignoring all other creatures. (Life in a shell!)

 @author C14Gavin.Delphia

 @version 1.0 - Jan 20, 2012 at 10:53:19 AM
 */
import java.awt.image.BufferedImage;

public class Snail extends Crawling {

  public Snail(BufferedImage image) {
    super(image);
  }

  /**
   Controls what happens When an snail meets another entity.
   */
  @Override
  public void meet() {
    //left blank
  }
}
