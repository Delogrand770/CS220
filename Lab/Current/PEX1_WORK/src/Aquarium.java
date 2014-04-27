
/**
 * File: Aquarium.java
 *
 * Description: A virtual aquarium with swimmers (Anglefish, Clownfish,
 * and Seahorses) and crawlers (Crabs, Lobsters, and Snails).
 * 
 * Dr. Bower's Documentation: This code is original, but the
 * inspiration for the virtual aquarium comes from Dr. Schweitzer.
 * 
 * Cadet Documentation: I did not receive help on this assignment.
 *
 * @author Randall.Bower
 * @author Gavin Delphia
 */
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Aquarium {

  /**
   * The dimensions here are the dimensions of a "window" into an
   * aquarium. Creatures are allowed to swim or crawl outside the
   * bounds of this window as if they were just out of view of the
   * window, but will later return.
   * 
   * The width of the aquarium should fit on most laptop screens.
   * For best results, use 800, 1024, or 1200.
   */
  public static final int WIDTH = 800;
  /**
   * The height of the aquarium is based on width to get the
   * visually appealing 4:3 ratio.
   */
  public static final int HEIGHT = WIDTH * 3 / 4;
  /**
   * The depth of the aquarium is the visual depth, front to back,
   * not the water depth from the top. This is rather arbitrarily
   * set to one-half of the height.
   */
  public static final int DEPTH = HEIGHT / 2;

  /**
   * The main method simply creates a new Aquarium object.
   * 
   * @param args Command line arguments; ignored by this application.
   */
  public static void main(String[] args) {
    new Aquarium();
  }

  /**
   * This constructor creates a new aquarium and starts a timer
   * that triggers the AquariumPanel's actionPerformed method
   * which controls the animation.
   */
  public Aquarium() {
    // Create the frame with the title "Aquarium".
    JFrame aquariumFrame = new JFrame("Aquarium");
    aquariumFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Set the location of the frame slightly offset from the
    // upper-left corner of the screen.
    aquariumFrame.setLocation(16, 16);

    // Set the size of the frame with a few extra pixels for the border and
    // title bar so the panel inside the frame is exactly WIDTH x HEIGHT.
    aquariumFrame.setSize(WIDTH + 16, HEIGHT + 38);

    // Create the aquarium panel and add it to the frame.
    AquariumPanel aquariumPanel = new AquariumPanel();
    aquariumFrame.add(aquariumPanel);

    // Make the frame visible.
    aquariumFrame.setVisible(true);

    // Create and start a timer that triggers the actionPerfermed
    // method in the aquarium panel every 50 milliseconds for a 
    // roughly 20 frames-per-second animation.
    new Timer(50, aquariumPanel).start();
  }

  /**
   * Inner class extending JPanel to inherit the paint functionality.
   * This class also implements the ActionListener interface so it
   * can be used with a Timer to create the animation.
   */
  class AquariumPanel extends JPanel implements ActionListener {

    /** Array of Entities contained in the aquarium. */
    private Entity[] entities;
    /** The background image displayed in the aquarium. */
    private BufferedImage background;
    /** The number of each creature type to have in the tanks */
    private static final int N_ANGELFISH = 3;
    private static final int N_CLOWNFISH = 4;
    private static final int N_SEAHORSE = 3;
    private static final int N_CRAB = 4;
    private static final int N_LOBSTER = 3;
    private static final int N_SNAIL = 4;
    /** The total number of creatures in the tank */
    private static final int N_TOTAL = N_ANGELFISH + N_CLOWNFISH + N_SEAHORSE + N_CRAB + N_LOBSTER + N_SNAIL;
    /** The total number of bubbles in the tank */
    private static final int N_BUBBLES = N_TOTAL * 4;

    /**
     * The constructor loads all images and creates arrays of creatures.
     */
    public AquariumPanel() {
      super();

      // Load all images here so there is only one copy of each in memory.
      // Only the background image needs to be held as a class variable;
      // pointers to the other images will be kept in each creature object.
      this.background = loadImage("Background.jpg");
      BufferedImage bubbleImage = loadImage("Bubble.png");
      BufferedImage angelfishImage = loadImage("Angelfish.png");
      BufferedImage clownfishImage = loadImage("Clownfish.png");
      BufferedImage seahorseImage = loadImage("Seahorse.png");
      BufferedImage crabImage = loadImage("Crab.png");
      BufferedImage lobsterImage = loadImage("Lobster.png");
      BufferedImage snailImage = loadImage("Snail.png");


      int entityCount = 0;
      this.entities = new Entity[N_TOTAL + N_BUBBLES];

      // Create the bubbles. Four bubbles for each aquatic creature.
      for (int i = 0; i < N_BUBBLES; entityCount++, i++) {
        this.entities[entityCount] = new Bubble(bubbleImage);
      }

      /*
       * The code below initializes all creatures.
       */
      for (int i = 0; i < N_ANGELFISH; entityCount++, i++) {
        this.entities[entityCount] = new Angelfish(angelfishImage);
      }
      for (int i = 0; i < N_CLOWNFISH; entityCount++, i++) {
        this.entities[entityCount] = new Clownfish(clownfishImage);
      }
      for (int i = 0; i < N_SEAHORSE; entityCount++, i++) {
        this.entities[entityCount] = new Seahorse(seahorseImage);
      }
      for (int i = 0; i < N_SNAIL; entityCount++, i++) {
        this.entities[entityCount] = new Snail(snailImage);
      }
      for (int i = 0; i < N_CRAB; entityCount++, i++) {
        this.entities[entityCount] = new Crab(crabImage);
      }
      for (int i = 0; i < N_LOBSTER; entityCount++, i++) {
        this.entities[entityCount] = new Lobster(lobsterImage);
      }

    }

    /**
     * The actionPerformed event is fired by the timer created in the
     * Aquarium constructor.
     * 
     * @param ae ActionEvent object associated with this event.
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
      // First, ask all the entities to move.
      for (int i = 0; i < this.entities.length; i++) {
        this.entities[i].move();
      }

      // See if any of the aquatic creatures have met any other creatures.
      for (int i = 0; i < this.entities.length; i++) {
        for (int j = 0; j < this.entities.length; j++) {
          if (met(this.entities[i], this.entities[j])) {
            this.entities[i].meet();
            this.entities[j].meet();
          }
        }
      }

      // Sort the entities so some will appear "behind" others.
      // See comment on the sort method below.
      sortAll(this.entities);

      // Finally, after all entities have moved and all meetings are
      // taken care of, repaint the entire aquarium. NOTE: None of the
      // above code tried to paint anything!! Everything is moved at
      // once and only then is the entire aquarium repainted.
      repaint();
    }

    /**
     * This method paints the aquarium.
     * 
     * @param g The Graphics object to use to paint.
     */
    @Override
    public void paintComponent(Graphics g) {
      // Make sure the JPanel does whatever it needs to do when painted.
      super.paintComponent(g);

      // Draw the background image.
      g.drawImage(this.background, 0, 0,
                  this.getWidth(), this.getHeight(), null);

      // Paint all the entities.
      for (int i = 0; i < this.entities.length; i++) {
        this.entities[i].paint(g);
      }
    }

    /**
     * This method sorts the array of entities so those with the smallest
     * z positions are first. This means when the entities are drawn, the
     * entities with smaller z values (closer to the back of the tank)
     * will appear to be behind the other entities when they overlap.
     * The method uses a simple selection sort algorithm.
     */
    private void sortAll(Entity[] array) {
      Entity temp; // Temporary varuable to use when swapping.
      for (int i = 0; i < array.length - 1; i++) {
        int smallest = i;
        for (int j = i + 1; j < array.length; j++) {
          if (array[j].getZ() < array[smallest].getZ()) {
            smallest = j;
          }
        }

        temp = array[i];
        array[i] = array[smallest];
        array[smallest] = temp;
      }
    }

    /**
     * Determine if the two given aquatic creatures have met.
     * 
     * @param a First creature of the pair to be checked.
     * @param b Second creature of the pair to be checked.
     * @return true of the two creatures have met; false otherwise.
     */
    private boolean met(Entity a, Entity b) {
      /**
       * Return false if either entity is a bubble so creatures have 
       * no chance of meeting with a bubble */
      if (a instanceof Bubble || b instanceof Bubble) {
        return false;
      }
      
      double dx = Math.abs(a.getX() - b.getX());
      if (a.getX() <= b.getX() && dx <= a.getWidth()
          || b.getX() <= a.getX() && dx <= b.getWidth()) {
        double dy = Math.abs(a.getY() - b.getY());
        if (a.getY() <= b.getY() && dy <= a.getHeight()
            || b.getY() <= a.getY() && dy <= b.getHeight()) {
          double dz = Math.abs(a.getZ() - b.getZ());
          if (a.getZ() <= b.getZ() && dz <= a.getDepth()
              || b.getZ() <= a.getZ() && dz <= b.getDepth()) {
            // Creatures only "meet" if they are going in opposite directions.
            if (a.getSpeed() < 0 && b.getSpeed() > 0
                || a.getSpeed() > 0 && b.getSpeed() < 0) {
              return true;
            }
          }
        }
      }
      return false;
    }

    /**
     * This method loads an image with the given file name from the images
     * folder of the project.
     * 
     * @param name File name of the image.
     * @return BufferedImage object representing the image;
     *         null if there was an error loading the image.
     */
    private BufferedImage loadImage(String name) {
      BufferedImage image = null;
      try {
        image = ImageIO.read(new File("images/" + name));
      } catch (IOException e) {
        System.err.println("Error loading image: " + name);
        System.exit(1);
      }
      return image;
    }
  }
}
