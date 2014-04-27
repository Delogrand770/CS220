
/**
 File: PileDriver.java

 Description: Driver program to test the pile.

 @author Randall.Bower
 */
public class PileDriver {

  public static void main(String[] args) {
    Pile p = new Pile(8);
    p.add(42);
    p.add(13);
    p.add(75);
    p.add(88);
    p.add(25);
    p.add(37);
    p.add(67);
    System.out.println(p);
    System.out.println("Should be true");
    System.out.println(p.contains(42));
    System.out.println(p.contains(13));
    System.out.println(p.contains(88));
    System.out.println(p.contains(67));
    System.out.println(p.contains(25));
    System.out.println(p.contains(37));
    System.out.println(p.contains(75));
    System.out.println("Should be false");
    System.out.println(p.contains(50));
    System.out.println(p.contains(11));
    System.out.println(p.contains(99));
    System.out.println(p.contains(20));
  }
}
