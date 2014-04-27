
/**
 Description: This file contains several implementations of a Stack.

 @author Randall.Bower
 */
public class MyStack<T> {

  private T[] items;
  private int top;

  public MyStack() {
    this.items = (T[]) new Object[1024];
    this.top = 0;
  }

  /**
   This method pushes an item onto the top of the stack.

   @param item Item to be pushed onto the top of the stack.
   */
  public void push(T item) {
    this.items[this.top++] = item; //post increment so uses top then increments by 1
  }

  /**
   This method removes and returns the top item on the stack.

   @return The top item on the stack.
   */
  public T pop() {
    return this.isEmpty() ? null : this.items[--this.top]; //pre decrease so decreases top then uses top
  }

  /**
   This method returns the top item on the stack without removing it.

   @return The top item on the stack.
   */
  public T peek() {
    return this.isEmpty() ? null : this.items[this.top - 1];
  }

  /**
   Determines if the stack is empty.

   @return True if the stack is empty; false otherwise.
   */
  public boolean isEmpty() {
    return this.top == 0;
  }

  /**
   Returns the current size of this stack.

   @return The current size of this stack.
   */
  public int size() {
    return this.top;
  }
}
