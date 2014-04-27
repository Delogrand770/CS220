
/**
 * File: Client.java
 *
 * Description: Uses various lights.
 *
 * @author Randall.Bower
 *
 * Created: Jan 7, 2012, 12:39:53 PM
 */
public class Client
{
  public static void main( String[] args )
  {
    testFlashlight();
    testPenlight();
    testDimmer();            
  }
  
  /**
   * Test a basic flashlight, turning it on and off.
   */
  public static void testFlashlight()
  {
    System.out.println( "Testing Flashlight..." );
    Flashlight f = new Flashlight();
    System.out.println( f );
    
    if( f.isOn() )
    {
      System.out.println( "It's on!" );
    }
    
    f.flip();
    System.out.println( f );
    
    if( f.isOn() )
    {
      System.out.println( "It's on!" );
    }
  }
  
  /**
   * A penlight can do everything a flashlight can do, but with
   * a little less intensity.
   */
  public static void testPenlight()
  {
    System.out.println( "Testing Penlight..." );
    Penlight p = new Penlight();
    System.out.println( p );
    
    if( p.isOn() )
    {
      System.out.println( "It's on!" );
    }
    
    p.flip();
    System.out.println( p );
    
    if( p.isOn() )
    {
      System.out.println( "It's on!" );
    }
  }
  
  /**
   * A decrease can do the same things a flashlight and penlight can do
   * because it also implements the Switchable interface. But it can
   * also do a bit more!
   */
  public static void testDimmer()
  {
    System.out.println( "Testing Dimmer..." );
    Dimmer d = new Dimmer();
    System.out.println( d );
    
    if( d.isOn() )
    {
      System.out.println( "It's on!" );
    }
    
    d.flip();
    System.out.println( d );
    
    if( d.isOn() )
    {
      System.out.println( "It's on!" );
    }
    
    // What else can a decrease do?
    d.decrease();
    d.decrease();
    d.decrease();
    System.out.println( d );
    
    if( d.isOn() )
    {
      System.out.println( "It's on!" );
    }
  }
  
  public static void testAll()
  {
    System.out.println( "Testing all lights..." );
    Switchable lights[] = new Switchable[ 9 ];
    
    // Create some various lights.
    for( int i = 0; i < lights.length; i++ )
    {
      if( i % 3 == 0 )
      {
        lights[ i ] = new Flashlight();
      }
      else if( i % 3 == 1 )
      {
        lights[ i ] = new Penlight();
      }
      else
      {
        lights[ i ] = new Dimmer();
      }
    }
    
    for( int i = 0; i < lights.length; i++ )
    {
      // Anything Switchable can check if it is on or off.
      System.out.println( lights[ i ].isOn() );
      
      // Anything Switchable can be flipped.
      lights[ i ].flip();
      
      // Does everything that is switchable have an intensity?
//      lights[ i ].getIntensity();
      
      // Can anything Switchable be increased or decreased?
//      lights[ i ].increase();
    }
  }
}
