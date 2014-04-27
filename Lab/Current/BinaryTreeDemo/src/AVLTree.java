
/**
 Description: This class represents an AVL tree. All nodes in the tree
 satisfy the property that the value in the node is larger than all
 values in nodes in the left sub-tree and smaller than all values in
 nodes in the right sub-tree. Additionally, all nodes in the tree
 satisfy the property that the height of the nodes left and right
 sub-trees differs by at most one.

 @author Randall.Bower
 */
public class AVLTree<T extends Comparable> {

  private class Node<T extends Comparable> {

    private T item;
    private Node<T> left;
    private Node<T> right;
    private int height; // A node in an AVL tree keeps track of its height,
    // updating every time an insert or remove is done.

    public Node() {
      this(null);
    }

    public Node(T item) {
      this.item = item;
      this.left = null;  // Not really necessary.
      this.right = null; // Not really necessary.
      this.height = 0;   // Not really necessary.
    }
  }
  // A node reference for the root of the tree is the only attribute needed.
  // Note: Since object references are set to null by default, there is no
  // constructor needed to create a new, empty, AVL tree.
  private Node<T> root;

  /**
   Inserts an item into this tree, ensuring the property that all values
   to the left of a given node are smaller and all values to the right
   of a given node are larger. Also ensures the tree remains balanced.

   @param item Item to be inserted.
   */
  public void insert(T item) {
    this.root = this.insert(this.root, item);
  }

  // Recursive helper method for insert.
  private Node<T> insert(Node<T> curr, T item) {
    // All nodes are inserted into a tree as a leaf.
    if (curr == null) {
      // Create the new leaf node and return it to the previous call to
      // insert that is waiting to set the parent's left or right child.
      return new Node<T>(item);
    }
    if (item.compareTo(curr.item) < 0) {
      // Item is smaller than the item in the current node, so go left.
      curr.left = this.insert(curr.left, item);
    } else {
      // Item is larger than the item in the current node, so go right.
      curr.right = this.insert(curr.right, item);
    }

    // Re-balance the sub-tree rooted at curr before returning.
    curr = this.balance(curr);
    return curr;
  }

  // Return the balance factor of a node.
  private int balanceFactor(Node<T> curr) {
    // This return value uses what is called a "ternary operator". For reference, see:
    // http://marxsoftware.blogspot.com/2010/09/how-i-learned-to-stop-worrying-and-love.html
    return (curr == null) ? 0 : height(curr.left) - height(curr.right);
  }

  // Balance the subtree rooted at curr, updating the height of each node,
  // and returning the new root in case the tree was rotated.
  private Node<T> balance(Node<T> curr) {
    if (balanceFactor(curr) == 2) {
      if (balanceFactor(curr.left) == 1) {
        curr = this.leftLeftRotation(curr);
      } else {
        curr = this.leftRightRotation(curr);
      }
    } else if (balanceFactor(curr) == -2) {
      if (balanceFactor(curr.right) == -1) {
        curr = this.rightRightRotation(curr);
      } else {
        curr = this.rightLeftRotation(curr);
      }
    }
    curr.height = 1 + Math.max(height(curr.left), height(curr.right));
    return curr;
  }

  // Perform the left rotation, update heights, return new root of this subtree.
  private Node<T> leftLeftRotation(Node<T> curr) {
    Node pivot = curr.left;
    curr.left = pivot.right;
    pivot.right = curr;
    curr.height = 1 + Math.max(height(curr.left), height(curr.right));
    pivot.height = 1 + Math.max(height(pivot.left), height(pivot.right));
    return curr;
  }

  // Perform the right rotation, update heights, return new root of this subtree.
  private Node<T> rightRightRotation(Node<T> curr) {
    Node pivot = curr.right;
    curr.right = pivot.left;
    pivot.left = curr;
    curr.height = 1 + Math.max(height(curr.left), height(curr.right));
    pivot.height = 1 + Math.max(height(pivot.left), height(pivot.right));
    return curr;
  }

  // Perform the right-left double rotation, update heights, return new root of this subtree.
  private Node<T> rightLeftRotation(Node<T> curr) {
    curr.right = this.rightRightRotation(curr.right);
    curr.left = this.leftLeftRotation(curr.left);
    return curr;
  }

  // Perform the left-right double rotation, update heights, return new root of this subtree.
  private Node<T> leftRightRotation(Node<T> curr) {
    curr.left = this.leftLeftRotation(curr.left);
    curr.right = this.rightRightRotation(curr.right);
    return curr;
  }

  /**
   Determines if this tree contains the given item.

   @param item The item to be searched for.
   @return True if this tree contains the item; false otherwise.
   */
  public boolean contains(T item) {
    return this.contains(this.root, item);
  }

  // Recursive helper method for contains.
  private boolean contains(Node<T> curr, T item) {
    if (curr == null) // Reached a left ...
    {
      return false;    // ... item is not in the tree.
    } else if (item.compareTo(curr.item) < 0) // Item is smaller than current item,
    {
      return this.contains(curr.left, item);  // look left.
    } else if (item.compareTo(curr.item) > 0) // Item is larger than current item,
    {
      return this.contains(curr.right, item); // look right.
    } else // Item must be equal to the current item,
    {
      return true; // Found it!
    }
  }

  /**
   Removes an item from this tree, ensuring the property that all values
   to the left of a given node are smaller and all values to the right
   of a given node are larger. Also ensures the tree remains balanced.

   @param item Item to be removed.
   */
  public void remove(T item) {
    this.root = this.remove(this.root, item);
  }

  // Recusrive helper method for remove.
  private Node<T> remove(Node<T> curr, T item) {
    // The base case is curr == null, but there's nothing to do
    // so the code tests the opposite before continuing.
    if (curr != null) {
      /*
       Code for remove goes here...
       */

      // Ensure the tree is balanced before returning.
      curr = this.balanceAfterRemove(curr);
    }
    return curr;
  }

  // Balance the subtree rooted at curr after removal.
  // This code is very similar to balancing after insertion, but the
  // conditions to trigger the rotations are slightly different, as
  // noted in the code below with comments.
  private Node<T> balanceAfterRemove(Node<T> curr) {
    // Left subtree is taller than right subtree, so rotate it.
    if (balanceFactor(curr) == 2) {
      // If the left subtree's balance factor is 0 or 1 (i.e., it is taller),
      // do a single rotation; otherwise do a double rotation.
      if (balanceFactor(curr.left) >= 0) // == 1 ) // THIS IS SLIGHTLY DIFFERENT!
      {
        curr = leftLeftRotation(curr);
      } else //if( balanceFactor( curr.left ) == -1 )   // THIS IS SLIGHTLY DIFFERENT!
      {
        curr = leftRightRotation(curr);
      }
    } // Right subtree is taller than right subtree, so rotate it.
    else if (balanceFactor(curr) == -2) {
      // If the right subtree's balance factor is 0 or -1 (i.e., it is taller),
      // do a single rotation; otherwise do a double rotation.
      if (balanceFactor(curr.right) <= 0) // == -1 ) // THIS IS SLIGHTLY DIFFERENT!
      {
        curr = rightRightRotation(curr);
      } else //if( balanceFactor( curr.right ) == 1 )     // THIS IS SLIGHTLY DIFFERENT!
      {
        curr = rightLeftRotation(curr);
      }
    }
    curr.height = Math.max(height(curr.left), height(curr.right)) + 1;
    return curr;
  }

  /**
   Returns the size of the tree (the number of nodes in the tree).

   @return The size of this tree.
   */
  public int size() {
    return this.size(this.root);
  }

  // Recursive helper method to calculate the size of a sub-tree
  // rooted at a given node.
  private int size(Node<T> curr) {
    return curr == null ? 0 : 1 + this.size(curr.left) + this.size(curr.right);
  }

  /**
   Returns the height of the tree; the number of branches between the
   root and the deepest leaf. The height of the root is zero and the
   height of an empty tree is -1.

   @return The height of this tree.
   */
  public int height() {
    return this.height(this.root);
  }

  // Private helper method for height of a tree. Unlike a binary tree, the
  // height of each node is kept in the node, so it can be returned direcly.
  private int height(Node<T> curr) {
    return (curr == null) ? -1 : curr.height;
  }

  /**
   Finds the smallest item in this tree.

   @return The smallest item in this tree; null if the tree is empty.
   */
  public T smallest() {
    return this.smallest(this.root);
  }

  // Helper method not really necessary since this is not recursive,
  // but the helper method is used in the remove method.
  private T smallest(Node<T> curr) {
    // The leftmost item is smallest.
    while (curr.left != null) {
      curr = curr.left;
    }
    return curr.item;
  }

  /**
   Finds the largest item in this tree.

   @return The largest item in this tree; null if the tree is empty.
   */
  public T largest() {
    return this.largest(this.root);
  }

  // Helper method not really necessary since this is not recursive,
  // but the helper method is used in the remove method.
  private T largest(Node<T> curr) {
    // The rightmost item is largest.
    while (curr.right != null) {
      curr = curr.right;
    }
    return curr.item;
  }

  /**
   Determines if two trees contain the same items AND are structurally
   equal.

   @param obj The AVLTree to compare this AVLTree to.
   @return True if the trees are equal; false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    return false;
  }

  /**
   Determines if the tree is empty.

   @return True if this tree contains no elements; false otherwise.
   */
  public boolean isEmpty() {
    return this.root == null;
  }

  /**
   Creates and returns a string representation of this tree, which is an
   in-order traversal of the tree's nodes.

   @return A string containing an in-order traversal of this tree's
   nodes.
   */
  @Override
  public String toString() {
    // Use the inOrder traversal of the tree.
    return this.inOrder(this.root).trim();
  }

  /**
   Creates and returns a string with an in-order traversal of this tree.

   @return An in-order traversal of this tree.
   */
  public String inOrder() {
    return this.inOrder(this.root).trim();
  }

  // Recursive helper method for inOrder.
  private String inOrder(Node<T> curr) {
    if (curr == null) {
      return "";
    } else {
      return this.inOrder(curr.left) + curr.item + " " + this.inOrder(curr.right);
    }
  }

  /**
   Creates and returns a string with a pre-order traversal of this tree.

   @return A pre-order traversal of this tree.
   */
  public String preOrder() {
    return this.preOrder(this.root).trim();
  }

  // Recursive helper method for preOrder.
  private String preOrder(Node<T> curr) {
    if (curr == null) {
      return "";
    } else {
      return curr.item + " " + this.preOrder(curr.left) + this.preOrder(curr.right);
    }
  }

  /**
   Creates and returns a string with a post-order traversal of this
   tree.

   @return A post-order traversal of this tree.
   */
  public String postOrder() {
    return this.postOrder(this.root).trim();
  }

  // Recursive helper method for postOrder.
  private String postOrder(Node<T> curr) {
    if (curr == null) {
      return "";
    } else {
      return this.postOrder(curr.left) + this.postOrder(curr.right) + curr.item + " ";
    }
  }

  /**
   A handy way to view the contents of a tree; when viewing, touch your
   left ear to your left shoulder and it looks like a tree!
   */
  public void display() {
    this.display(this.root, 0);
  }

  // Recursive helper method for display.
  private void display(Node<T> curr, int depth) {
    if (curr == null) {
      return;
    } else {
      this.display(curr.right, depth + 1);
      this.display(depth * 2, curr.item);
      this.display(curr.left, depth + 1);
    }
  }

  // Displays an item preceeded by spaces to show depth in the tree.
  private void display(int n, T item) {
    for (int i = 0; i < n; i++) {
      System.out.print(" ");
    }
    System.out.println(item);
  }

  /**
   An AVL tree would not normally have a main method, but it is handy
   here to demonstrate basic functionality.

   @param args Command line arguments; ignored.
   */
  public static void main(String[] args) {
    AVLTree<Integer> tree = new AVLTree<Integer>();

    // Create My Favorite Tree from a string using a Scanner.
    //       42
    //   25      75
    // 13  37  67  88
    java.util.Scanner scan = new java.util.Scanner("42 25 75 13 37 67 88");
    while (scan.hasNext()) {
      tree.insert(scan.nextInt());
    }
    tree.display();
    System.out.println("==========");

    // Read input from System.in, positive value to insert,
    // negative value to remove, zero to quit.
    System.out.println("Enter positive values to insert, negative values to remove, 0 to quit.");
    scan = new java.util.Scanner(System.in);
    System.out.print("Enter value: ");
    int i = scan.nextInt();
    while (i != 0) {
      if (i > 0) {
        tree.insert(i);
      } else {
        tree.remove(i * -1);
      }
      tree.display();
      System.out.print("Enter value: ");
      i = scan.nextInt();
    }
  }
}
//@Test
//@ExpectedException
//or
//try{
////code to fail
//fail();
//}catch(Exception e){
////passes
//}