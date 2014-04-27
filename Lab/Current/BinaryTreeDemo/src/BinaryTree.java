
/**
 Description: This class represents a Binary Tree that is kept in sorted
 order.

 NOTE: This code does NOT contain Javadoc or inline comments on purpose
 so the functionality of these methods can be discussed in class!!!

 @author Randall.Bower
 */
public class BinaryTree<T extends Comparable> {

  private class Node<T> {

    T item;
    Node<T> left;
    Node<T> right;

    public Node(T item) {
      this.item = item;
      this.left = null;
      this.right = null;
    }
  }
  private Node root;

  public BinaryTree() {
    this.root = null;
  }

  public void remove(T item) { 
    this.root = this.remove(this.root, item);
  }

  private Node remove(Node curr, T item) { //probably wrong
    if (curr != null) {
      if (item.compareTo(curr.item) < 0) {
        curr.left = remove(curr.left, item);
      } else if (item.compareTo(curr.item) > 0) {
        curr.right = remove(curr.right, item);
      } else {
        if (curr.left == null && curr.right == null) {
          curr = null;
        } else if (curr.left == null && curr.right != null) {
          curr = curr.right;
        } else if (curr.left != null && curr.right == null) {
          curr = curr.left;
        } else {
          curr.item = curr.right.item;
          curr.right = remove(curr.right, (T) curr.item);
        }
      }
    }
    return curr;
  }

  public T smallest() {
    return null;
  }

  public T largest() {
    return null;
  }

  public int size() {
    return 1;
  }

  public int height() {
    return 1;
  }

  public boolean equals() {
    return false;
  }

  public String breadth() {
    return "";
  }

  public boolean isEmpty() {
    return this.root == null;
  }

  public void insert(T item) {//only at leafs
    this.root = this.insert(this.root, item);
  }

  private Node insert(Node curr, T item) {
    if (curr == null) {
      curr = new Node(item);
    } else if (item.compareTo(curr.item) < 0) { //- first less than second, + first greater than second, 0 if equal
      curr.left = insert(curr.left, item);
    } else {
      curr.right = insert(curr.right, item);
    }
    return curr;
  }

  public boolean contains(T item) {
    return this.contains(this.root, item);
  }

  private boolean contains(Node curr, T item) {
    if (curr == null) {
      return false;
    } else if (item.compareTo(curr.item) < 0) {
      return this.contains(curr.left, item);
    } else if (item.compareTo(curr.item) > 0) {
      return this.contains(curr.right, item);
    } else {
      return true;
    }
  }

  public String preOrder() {
    return this.preOrder(this.root);
  }

  private String preOrder(Node curr) {
    if (curr != null) {
      return toString(curr.right) + curr.item + " " + toString(curr.left);
    }
    return "";
  }

  public String postOrder() {
    return this.postOrder(this.root);
  }

  private String postOrder(Node curr) {
    if (curr != null) {
      return toString(curr.left) + toString(curr.right) + " " + curr.item;
    }
    return "";
  }

  public String inOrder() {
    return this.toString(this.root);
  }

  @Override
  public String toString() {
    return toString(this.root);
  }

  private String toString(Node curr) {
    if (curr != null) { //pre                                   //pre
      return toString(curr.left) + curr.item + " " + toString(curr.right);
      //post                       //post
      //swap the pre with pre and post with post to change traversal to that type
      //current mode without swaps is in order traversal
      //in order left item right
      //pre order right item left
      //post order left right item
    }
    return "";
  }

  /**
   A handy way to view the contents of a tree; when viewing, touch your
   left ear to your left shoulder and it looks like a tree!
   */
  public void display() {
    this.display(this.root, 0);
  }

  private void display(Node<T> curr, int depth) {
    if (curr == null) {
      return;
    } else {
      this.display(curr.right, depth + 1);
      this.display(depth * 2, curr.item);
      this.display(curr.left, depth + 1);
    }
  }

  private void display(int n, T item) {
    for (int i = 0; i < n; i++) {
      System.out.print(" ");
    }
    System.out.println(item);
  }
}
