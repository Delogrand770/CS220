
import java.util.*;

/**
 This class represents a maze and implements the BackTrackable interface
 so the maze can be solved using the BackTracker algorithm.

 @author William.Collins
 @author Randall.Bower
 */
public class Maze implements BackTrackable {

  public static final byte WALL = 0;
  public static final byte CORRIDOR = 1;
  public static final byte PATH = 9;
  public static final byte DEAD_END = 2;
  protected Position startPosition, goalPosition;
  protected byte[][] grid;

  /**
   Initializes this Maze object from a file scanner over a file.

   @param fileScanner - the scanner over the file that holds the maze
   information.

   @throws InputMismatchException - if any of the row or column values
   are non- integers, or if any of the grid entries are non-integers.
   @throws NumberFormatException - if the grid entries are integers but
   neither WALL nor CORRIDOR
   */
  public Maze(Scanner fileScanner) {
    // First two values in file are grid dimensions.
    int rows = fileScanner.nextInt();
    int columns = fileScanner.nextInt();
    grid = new byte[rows][columns];

    // Next four values in file are start and end positions.
    startPosition = new Position(fileScanner.nextInt(), fileScanner.nextInt());
    goalPosition = new Position(fileScanner.nextInt(), fileScanner.nextInt());

    // Remaining values in file fill the grid.
    for (int row = 0; row < rows; row++) {
      for (int col = 0; col < columns; col++) {
        grid[ row][ col] = fileScanner.nextByte();
      }
    }
  }

  /**
   Gets the start position of this maze.

   @return The start position for this maze.
   */
  public Position getStart() {
    return startPosition;
  }

  /**
   Gets the finish or goal position of this maze.

   @return The finish or goal position for this maze.
   */
  public Position getFinish() {
    return goalPosition;
  }

  /**
   Returns a 2-dimensional array that holds a copy of the maze
   configuration.

   @return - a 2-dimensional array that holds a copy of the maze
   configuration.

   */
  public byte[][] getGrid() {
    byte[][] gridCopy = new byte[grid.length][grid[0].length];

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        gridCopy[ row][ col] = grid[ row][ col];
      }
    }

    return gridCopy;
  }

  /**
   Determines if a given position is legal and not a dead end.

   @param pos - the given position.

   @return true if pos is a legal position and not a dead end.
   */
  @Override
  public boolean isOK(Position pos) {
    return pos.getRow() >= 0 && pos.getRow() < grid.length
           && pos.getColumn() >= 0 && pos.getColumn() < grid[0].length
           && grid[ pos.getRow()][ pos.getColumn()] == CORRIDOR;
  }

  /**
   Indicates that a given position is possibly on a path to a goal.

   @param pos the position that has been marked as possibly being on a
   path to a goal.
   */
  @Override
  public void markAsPossible(Position pos) {
    grid[pos.getRow()][pos.getColumn()] = PATH;
  }

  /**
   Indicates whether a given position is a goal position.

   @param pos the position that may or may not be a goal position.

   @return true if pos is a goal position; false otherwise.
   */
  @Override
  public boolean isGoal(Position pos) {
    return pos.equals(goalPosition);
  }

  /**
   Indicates that a given position is not on any path to a goal
   position.

   @param pos the position that has been marked as not being on any path
   to a goal position.
   */
  @Override
  public void markAsDeadEnd(Position pos) {
    grid[ pos.getRow()][ pos.getColumn()] = DEAD_END;
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
        result += String.valueOf(grid[row][column]) + ' ';
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

  protected class MazeIterator implements Iterator<Position> {

    protected static final int MAX_MOVES = 8;
    //protected static final int MAX_MOVES = 4;
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
          nextPosition = new Position(row - 1, column); // north
          break;
        case 1:
          nextPosition = new Position(row, column + 1); // east
          break;
        case 2:
          nextPosition = new Position(row + 1, column); // south
          break;
        case 3:
          nextPosition = new Position(row, column - 1); // west
          break;
        case 4:
          nextPosition = new Position(row - 1, column - 1);
          break;
        case 5:
          nextPosition = new Position(row + 1, column + 1);
          break;
        case 6:
          nextPosition = new Position(row + 1, column - 1);
          break;
        case 7:
          nextPosition = new Position(row - 1, column + 1);
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
    Scanner fileScanner = FileDialogs.selectInputFile(null);

    Maze maze = new Maze(fileScanner);

    System.out.println("Initial state (0 = WALL, 1 = CORRIDOR):\n" + maze);

    BackTracker backTrack = new BackTracker(maze);
    if (backTrack.tryToReachGoal(maze.getStart())) {
      System.out.println("A solution has been found:");
    } else {
      System.out.println("There is no solution:");
    }

    System.out.println("Final state (2 = DEAD END, 9 = PATH):\n" + maze);
  }
}
