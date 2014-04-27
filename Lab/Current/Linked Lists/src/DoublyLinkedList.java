
/**
 CS220 Lesson 14 Generic Singly Linked List Example EXERCISES: implement
 append(E), removeFirst(E), and removeAll(E)

 @author david.gibson Documentation: based on LtCol Jeff Boleng's code
 */
public class DoublyLinkedList<E> {

// Node - inner class defining singly linked node structure  
  private class Node {

    private E item;       // item stored in the node
    private Node next;       // reference to the next node
    private Node prev;

    // default Node constructor - creates empty node (no item stored)
    private Node() {
      this(null);
    }

    // Node construtor - creates a node holding the given item
    private Node(E item) {
      this.item = item;
      this.next = null;
      this.prev = null;
    }
  }
  // SinglyLinkedList representation
  Node head;       // reference to first node in list
  Node tail;

  /**
   SinglyLinkedList constructor - creates an empty list.
   */
  public DoublyLinkedList() {
    this.head = null;
    this.tail = null;
  }

  /**
   Determines if the SinglyLinkedList is empty or not

   @return true if and only if the list contains no items
   */
  public boolean isEmpty() {
    return (head == null);
  }

  /**
   Determines the number of items in the SinglyLinkedList

   @return number of items in the list
   */
  public int size() {
    int count = 0;
    for (Node current = head; current != null; current = current.next) {
      count++;
    }
    return count;
  }

  /**
   Determines is an item is contained in the list NOTE: Class E must
   implement equals() for correct functionality

   @param targetItem - item searched for in the list
   @return true if and only if targetItem is in the list
   */
  public boolean contains(E targetItem) {
    for (Node current = head; current != null; current = current.next) {
      if (current.item.equals(targetItem)) {
        return true; // item found
      }
    }
    return false;    // item not found
  }

  /**
   Adds item to the front (head) of the list

   @param newItem - item to be added to the front of the list
   */
  public void addToFront(E newItem) {
    Node temp = new Node(newItem);
    temp.next = head;
    temp.prev = null;
    if (head != null) {
      head.prev = temp;
    }
    head = temp;
  }

  /**
   Returns String representation of the list

   @return String representation of the list
   */
  @Override
  public String toString() {
    String result = "[";
    if (head != null) {
      result = result + head.item.toString();
      for (Node current = head.next; current != null; current = current.next) {
        result = result + ", " + current.item.toString();
      }
    }
    result = result + "]";
    return result;
  }

  /**
   Returns String representation of the list

   @return String representation of the list
   */
  public String toStringReverse() {
    String result = "[";
    if (tail != null) {
      result = result + tail.item.toString();
      for (Node current = tail.prev; current != null; current = current.prev) {
        result = result + ", " + current.item.toString();
      }
    }
    result = result + "]";
    return result;
  }

  /**
   Appends a new item to the end (tail) of the list

   @param newItem - item to be appended to the end of the list
   */
  public void append(E newItem) {
    if (head == null) {
      head = new Node(newItem);
    } else {
      Node current = head;
      while (current.next != null) {
        current = current.next;
      }
      current.next = new Node(newItem);
    }
  }

  /**
   Removes first occurrence of an item from the list

   @param itemToRemove - item to remove from the list
   @return true if and only if an item was removed form the list
   */
  public boolean removeFirst(E itemToRemove) {
    if (head == null) {
      return false;
    } else if (head.item.equals(itemToRemove)) {
      head = head.next;
      return true;
    } else {
      Node old = head;
      for (Node current = head.next; current != null; current = current.next) {
        if (current.item.equals(itemToRemove)) {
          old.next = current.next;
          return true;
        }
        old = current;
      }
    }
    return false;
  }

  /**
   Removes all occurrences of an item from the list

   @param itemToRemove - item to remove from the list
   @return number of items removed from the list
   */
  public int removeAll(E itemToRemove) {
    int numRemoved = 0;
    while (removeFirst(itemToRemove)) {
      numRemoved++;
    }
    return numRemoved;
  }
}