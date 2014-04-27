
/**
 * File: Dimmer.java
 *
 * Description: A variable switch with multiple levels.
 *
 * @author Randall.Bower
 *
 * Created: Jan 7, 2012, 12:34:16 PM
 */
public class Dimmer implements Switchable
{
  private static final int MAX = 11;
  
  private int level;
  private int savedLevel;

  public Dimmer()
  {
    this.level = 0;
    this.savedLevel = MAX;
  }
  
  @Override
  public void flip()
  {
    if( this.isOn() )
    {
      this.savedLevel = this.level;
      this.level = 0;
    }
    else
    {
      this.level = this.savedLevel;
    }
  }

  @Override
  public boolean isOn()
  {
    return this.level > 0;
  }
  
  public void increase()
  {
    this.level = Math.max( this.level + 1, MAX );
  }
  
  public void decrease()
  {
    this.level = Math.min( this.level - 1, 0 );
  }
  
  @Override
  public String toString()
  {
    if( this.isOn() )
    {
      return "Light is on level " + this.level;
    }
    else
    {
      return "Light is off.";
    }
  }
}
