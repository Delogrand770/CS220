
import java.util.Iterator;
import java.util.Random;

/**
 This class represents a maze and implements the BackTrackable interface
 so the maze can be solved using the BackTracker algorithm.

 @author William.Collins
 @author Randall.Bower
 */
public class Knight implements BackTrackable {

  public static final int FINISH_COUNT = 64;
  protected Position startPosition;
  protected int[][] grid;
  protected int currentCount;

  /**
   Initializes this Knight object from an array.
   */
  public Knight() {
    Random gen = new Random();

    int rows = 8;
    int columns = 8;
    grid = new int[rows][columns];

    // startPosition is randomly generated
    startPosition = new Position(gen.nextInt(rows), gen.nextInt(columns));
    currentCount = 0;

    // Array is filled with zeros
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        grid[row][col] = 0;
      }
    }
  }

  /**
   Gets the start position of this knight.

   @return The start position for this knight.
   */
  public Position getStart() {
    return startPosition;
  }

  /**
   Gets the current move count.

   @return The current move count.
   */
  public int getCurrentCount() {
    return currentCount;
  }

  /**
   Returns a 2-dimensional array that holds a copy of the board
   configuration.

   @return - a 2-dimensional array that holds a copy of the board
   configuration.

   */
  public int[][] getGrid() {
    int[][] gridCopy = new int[grid.length][grid[0].length];

    for (int row = 0; row < grid.length; row++) {
      System.arraycopy(grid[ row], 0, gridCopy[ row], 0, grid[row].length);
    }

    return gridCopy;
  }

  /**
   Determines if a given position is legal and not already filled.

   @param pos - the given position.

   @return true if pos is a legal position and not filled.
   */
  @Override
  public boolean isOK(Position pos) {
    return pos.getRow() >= 0 && pos.getRow() < grid.length
           && pos.getColumn() >= 0 && pos.getColumn() < grid[0].length
           && grid[ pos.getRow()][ pos.getColumn()] == 0;
  }

  /**
   Indicates that a given position has been visited. Also increments the
   currentCount

   @param pos the position that has been marked as having been visited.
   */
  @Override
  public void markAsPossible(Position pos) {
    currentCount++;
    grid[pos.getRow()][pos.getColumn()] = currentCount;
  }

  /**
   Not needed for the knight tour.

   @param pos the position that has been marked as not being on any path
   to a goal position.
   */
  @Override
  public void markAsDeadEnd(Position pos) {
    //Not needed
  }

  /**
   Converts this BackTrackable object into a String object.

   @return the String representation of this BackTrackable object.
   */
  @Override
  public String toString() {
    String result = "";

    for (int row = 0; row < grid.length; row++) {
      for (int column = 0; column < grid[0].length; column++) {
        String spot = String.valueOf(grid[row][column]);
        result += (spot.length() == 1) ? " " : "";
        result += spot + ' ';
      }
      result += "\n";
    }
    return result;
  }

  /**
   Produces an Iterator object, over elements of type Position, that
   starts at a given position.

   @param pos - the position the Iterator object starts at.

   @return the Iterator object.
   */
  @Override
  public Iterator<Position> iterator(Position pos) {
    return new MazeIterator(pos);
  }

  @Override
  public boolean isGoal(Position pos) {
    return currentCount == FINISH_COUNT;
  }

  protected class MazeIterator implements Iterator<Position> {

    protected static final int MAX_MOVES = 8;
    protected int row, column, count;

    /**
     Initializes this MazeIterator object to start at a given position.

     @param pos the position the Iterator objects starts at.
     */
    public MazeIterator(Position pos) {
      row = pos.getRow();
      column = pos.getColumn();
      count = 0;
    }

    /**
     Determines if this MazeIterator object can advance to another
     position.

     @return true if this MazeIterator object can advance; false
     otherwise.
     */
    @Override
    public boolean hasNext() {
      return count < MAX_MOVES;
    }

    /**
     Advances this MazeIterator object to the next position.

     @return the position advanced to.
     */
    @Override
    public Position next() {
      Position nextPosition = new Position();
      switch (count++) {
        case 0:
          nextPosition = new Position(row - 2, column + 1); // up 2 right 1
          break;
        case 1:
          nextPosition = new Position(row - 2, column - 1); // up 2 left 1
          break;
        case 2:
          nextPosition = new Position(row + 2, column + 1); // down 2 right 1
          break;
        case 3:
          nextPosition = new Position(row + 2, column - 1); // down 2 left 1
          break;
        case 4:
          nextPosition = new Position(row - 1, column - 2); //up 1 left 2
          break;
        case 5:
          nextPosition = new Position(row + 1, column - 2); //down 1 left 2
          break;
        case 6:
          nextPosition = new Position(row - 1, column + 2); //up 1 right 2
          break;
        case 7:
          nextPosition = new Position(row + 1, column + 2); //down 1 right 2
      }
      return nextPosition;
    }

    /**
     Removal is illegal for a Maze iterator; calling this method results
     in an exception.

     @throws UnsupportedOperationException
     */
    @Override
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

  public static void main(String[] args) {

    Knight maze = new Knight();

    System.out.println("Initial state with starting position of " + maze.getStart() + "\n" + maze);

    BackTracker backTrack = new BackTracker(maze);
    if (backTrack.tryToReachGoal(maze.getStart())) {
      System.out.println("A solution has been found:");
    } else {
      System.out.println("There is no solution:");
    }

    System.out.println("Final state \n" + maze);
  }
}
