
/**
 Description: Driver program to graphically display a pile as it is
 sorted.

 @author Randall.Bower
 */
public class PileDriver {

  public static void main(String[] args) {
    // A default pile will display the sort graphically.
    Pile p = new Pile();
    p.bubble();
    //p.selection();
    //p.insertion();
    //p.shell();//left off on
    //p.quick();
    //p.merge();
    //p.heap();

    // A pile can also be declared with a larger size, reset with
    // different initial orderings and seeds, and results viewed.
//    Pile p = new Pile( (int)Math.pow( 2, 10 ) );
//
//    p.reset( Pile.ASCENDING, 37 );
//    p.bubble();
//    System.out.println( p.toString() );
//    
//    p.reset( Pile.DESCENDING, 37 );
//    p.bubble();
//    System.out.println( p.toString() );
//    
//    p.reset( Pile.ALMOST, 37 );
//    p.bubble();
//    System.out.println( p.toString() );
//    
//    p.reset( Pile.RANDOM, 37 );
//    p.bubble();
//    System.out.println( p.toString() );
  }
}
