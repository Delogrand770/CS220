
/**
 * File: Switchable.java
 *
 * Description: Anything that can change states between On and Off must
 * provide functionality to change the current state and determine the
 * current state.
 *
 * @author Randall.Bower
 *
 * Created: Jan 7, 2012, 12:17:55 PM
 */
public interface Switchable
{
  /**
   * Calling this method must change the state of the switchable object.
   */
  public void flip();
  
  /**
   * This method determines the current state of the switchable object.
   * 
   * @return True if the switchable object is on; false otherwise.
   */
  public boolean isOn();
}
