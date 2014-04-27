
/**
 * File: Flashlight.java
 *
 * Description: A flashlight that can be on or off.
 *
 * @author Randall.Bower
 *
 * Created: Jan 7, 2012, 12:30:31 PM
 */
public class Flashlight implements Switchable
{
  public boolean on;
  
  public Flashlight()
  {
    this.on = false;
  }
  
  public Flashlight( boolean on )
  {
    this.on = on;
  }

  @Override
  public void flip()
  {
    this.on = !this.on;
  }

  @Override
  public boolean isOn()
  {
    return this.on;
  }
  
  public double getIntensity()
  {
    return 0.6667;
  }
  
  @Override
  public String toString()
  {
    if( this.isOn() )
    {
      return "Light is on and shining with intensity = " + this.getIntensity();
    }
    else
    {
      return "Light is off.";
    }
  }
}
