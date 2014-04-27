
/**
 * Lamp - 
 *
 * @author C14Gavin.Delphia
 *
 * @version 1.0 - Jan 7, 2012 at 2:21:30 PM
 */
public class Lamp extends Dimmer {

  private static final int MAX = 11;
  private static final int CHANGE = MAX / 3;
  private int level;

  public Lamp() {
    level = 0;
  }

  @Override
  public void flip() {
    if (level <= MAX - CHANGE) {
      level += CHANGE;
    } else {
      level = 0;
    }
  }

  @Override
  public void increase() {
  }

  @Override
  public void decrease() {
  }

  @Override
  public boolean isOn() {
    return level > 0;
  }

  @Override
  public String toString() {
    if (this.isOn()) {
      return "Lamp is on level " + level;
    } else {
      return "Lamp is off.";
    }
  }
}
