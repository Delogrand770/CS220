
/**
 Description: This class represents a Unary Tree (aka, a Linked List)
 that is kept in sorted order.

 NOTE: This code does NOT contain Javadoc or inline comments on purpose
 so the functionality of these methods can be discussed in class!!!

 @author Randall.Bower
 */
public class UnaryTree<T extends Comparable> {

  private class Node<T> {

    private T item;
    private Node<T> next;

    public Node(T item, Node next) {
      this.item = item;
      this.next = next;
    }
  }
  private Node<T> head;

  public UnaryTree() {
    this.head = null;
  }

  public boolean isEmpty() {
    return this.head == null;
  }

  public void insert(T item) {
    this.head = this.insert(this.head, item);
  }

  private Node insert(Node curr, T item) {
    if (curr == null) {
      curr = new Node(item, null);
    } else if (item.compareTo(curr.item) < 0) { //- first less than second, + first greater than second, 0 if equal
      curr = new Node(item, curr);
    } else {
      curr.next = insert(curr.next, item);
    }

    return curr;
  }

  public boolean contains(T item) {
    return this.contains(this.head, item);
  }

  private boolean contains(Node curr, T item) {
    if (curr == null) {
      return false;
    } else if (item.compareTo(curr.item) < 0) {
      return false;
    } else if (item.compareTo(curr.item) > 0) {
      return this.contains(curr.next, item);
    } else {
      return true;
    }
  }

  @Override
  public String toString() {
    return this.toString(this.head);
  }

  private String toString(Node curr) {
    if (curr == null) {
      return "";
    } else {
      return curr.item + " " + this.toString(curr.next);
    }
  }
}
