
/**
 ClientCode -

 @author C14Gavin.Delphia
 @version 1.0 - Feb 14, 2012 at 2:11:02 PM
 */
public class ClientCode {

  public static void main(String[] args) {
    DoublyLinkedList<Integer> list = new DoublyLinkedList<Integer>();
    list.addToFront(3);
    list.addToFront(2);
    list.addToFront(1);
    System.out.println(list);
    //System.out.println(list.toStringReverse());
  }
}