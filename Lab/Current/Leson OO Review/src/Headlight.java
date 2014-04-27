
/**
 * Headlight - 
 *
 * @author C14Gavin.Delphia
 *
 * @version 1.0 - Jan 7, 2012 at 2:12:18 PM
 */
public class Headlight extends Flashlight {

  public boolean levelHigh;
  
  public Headlight(){
    levelHigh = false; //low beam default
  }

  public void levelChange() {
    levelHigh = !levelHigh;
  }

  public String getLevel() {
    return levelHigh ? "High Beam" : "Low Beam";
  }

  @Override
  public String toString() {
    if (this.isOn()) {
      return "Headlight is on and the level = " + this.getLevel();
    } else {
      return "Headlight is off.";
    }
  }
}
