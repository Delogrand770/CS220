
import java.util.Scanner;

/**
 Description: This program can be used to time the execution of a
 method. In this file is a Selection Sort. Try to write Bubble and
 Insertion sorts.

 @author Randall.Bower
 */
public class ExecutionTimer {

  public static void main(String[] args) {
    // The NumberFormat class formats numbers with commas and such. Very pretty.
    java.text.NumberFormat nf = java.text.NumberFormat.getInstance();

    Scanner console = new Scanner(System.in);

    System.out.print("How many values to sort (0 to quit): ");
    int n = console.nextInt();

    while (n != 0) {
      System.out.print("Original data (A)secnding, (D)escending, (E)qual, or (R)andom: ");
      char order = console.next().toUpperCase().charAt(0);

      System.out.println(nf.format(executionTime(n, order)) + " nano seconds.");

      System.out.print("\nHow many values to sort (0 to quit): ");
      n = console.nextInt();
    }
  }

  /**
   Measures the execution time of a sorting method by running the sort
   multiple times and returning the median of the five execution times.

   @param n The size of the data array to be sorted.
   @param order The original ordering of the data in the array.
   @return The median execution time of five runs of the sort method.
   */
  private static long executionTime(int n, char order) {
    // Declare the array to be filled with numbers.
    int[] data = new int[n];

    // To compensate for potentially skewed times due to such things as the
    // Java garbage collector deciding to run in the middle of a sort, we'll
    // do the experiment multiple times and return the median value.
    long[] times = new long[9]; // Nine times seems like a reasonable number.
    for (int experimentNumber = 0; experimentNumber < times.length; experimentNumber++) {
      // Fills the data array with the requested original ordering of data.
      fillData(data, order);

      // Save the start time, run the experiment, and then put the total time in the array.
      long startTime = System.nanoTime();
      //selectionSort(data);
      bubbleSort(data);
      times[ experimentNumber] = System.nanoTime() - startTime;
    }

    // Sort the array of times and return the median value.
    java.util.Arrays.sort(times);
    return times[ times.length / 2];
  }

  /**
   Fills an array of int values depending on an original ordering.

   @param data The array to be filled.
   @param order The original ordering of the data the array is to be
   filled with.
   */
  private static void fillData(int[] data, char order) {
    if (order == 'A') // Ascending order
    {
      for (int i = 0; i < data.length; i++) {
        data[ i] = i;
      }
    } else if (order == 'D') // Descending order
    {
      for (int i = 0; i < data.length; i++) {
        data[ i] = data.length - i;
      }
    } else if (order == 'E') // All values equal
    {
      for (int i = 0; i < data.length; i++) {
        data[ i] = 42;
      }
    } else // Random
    {
      for (int i = 0; i < data.length; i++) {
        data[ i] = (int) (Math.random() * Integer.MAX_VALUE);
      }
    }
  }

  /**
   Sorts an array of int values into ascending order using a selection
   sort. http://en.wikipedia.org/wiki/Selection_sort

   @param data The array to be sorted.
   */
  private static void selectionSort(int[] data) {
    // Go through each element of the array except the last. (Why?)
    for (int i = 0; i < data.length - 1; i++) {
      // Starting at position i, find the smallest.
      int positionOfSmallest = i;
      for (int j = i + 1; j < data.length; j++) {
        if (data[ j] < data[ positionOfSmallest]) {
          positionOfSmallest = j;
        }
      }
      // Swap the smallest with the value in position i.
      int temp = data[ i];
      data[ i] = data[ positionOfSmallest];
      data[ positionOfSmallest] = temp;
    }
  }

  /**
   Sorts an array of int values into ascending order using a bubble
   sort. http://en.wikipedia.org/wiki/Bubble_sort

   @param data The array to be sorted.
   */
  private static void bubbleSort(int[] data) {
    int n = data.length;
    while (n != 0) {
      int newn = 0;
      for (int i = 1; i <= n - 1; i++) {
        if (data[i - 1] > data[i]) {
          int[] temp = {data[i - 1], data[i]};
          data[i - 1] = temp[1];
          data[i] = temp[0];
          newn = i;
        }
      }
      n = newn;
    }
  }

  /**
   Sorts an array of int values into ascending order using an insertion
   sort. http://en.wikipedia.org/wiki/Insertion_sort

   @param data The array to be sorted.
   */
  private static void insertionSort(int[] data) {
    int n = data.length;
    for (int i = 1; i <= n - 1; i++) {
      int value = data[i];
      int j = i - 1;
      boolean done = false;
      while (!done) {
        if (data[j] > value) {
          data[j + 1] = data[j];
          j = j - 1;
          if (j < 0) {
            done = true;
          }
        } else {
          done = true;
        }
      }
      data[j + 1] = value;
    }
  }

  /**
   Sorts an array of int values into ascending order using a bogo sort.
   http://en.wikipedia.org/wiki/Bogosort

   WARNING: Implement and test at your own risk!

   @param data The array to be sorted.
   */
  private static void bogoSort(int[] data) {
  }
}
