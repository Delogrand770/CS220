import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 Client.java consists of a ShannonSwitchingGraph game that supports four
 different game modes: human v. human, human v. computer, computer v.
 human, and computer v. computer. The game appropriately draws a graph,
 and identifies which player wins, asking if the user would like to play
 another game.

 @author C14Brandon.Daley
 @author C14Gavin.Delphia

 Documentation: none.

 @version 1.0 - Apr 15, 2012 at 4:16:59 PM
 */
public class Client {

  public static final int WIDTH = 800; //Width of the game window
  public static final int HEIGHT = 600; //Height of the game window
  public static int gameType; //Stores the type of game based on player types
  public static DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT); // Panel on which the graph is displayed
  public static Point oldLocation = new Point(-1, -1); //The (x,y) position of the mouse
  public static ShannonSwitchingGraph graph; //Instance of the graph
  public static int[] playerType = {1, 1}; // Initial player types, assuming Human v. Human game
  public static int turn = 0; // Whose turn it is
  public static boolean[] winner = {false, false}; // Instantiates both players to not winning

  /**
   Main begins the game, going to the start() method which determines
   gameplay, sets players, and tracks status of the current game

   @param args not used
   */
  public static void main(String[] args) {
    start();
  }

  private static void start() {

    // Reset both players to not winning
    winner[0] = false;
    winner[1] = false;

    // If the user wants to cease playing
    if (!gameSetup()) {
      System.exit(0);
    }

    // Set up the initial graph and draw it on the panel
    graph = new ShannonSwitchingGraph(WIDTH, HEIGHT);
    graph.draw(panel);

    // Get the initial location of the mouse
    oldLocation = new Point(panel.getMouseX(), panel.getMouseY());

    panel.setWindowTitle("Shannon Switching Graph Game");

    // Play until there is a winner or there are no more lines to click
    while (!winner[0] && !winner[1] && !graph.lines.isEmpty()) {

      getMouseMovement();
      determineMoveType();
      panel.sleep(10);
    }

    // Display who won and prompt whether the user wants to play another game
    results();
  }

  /**
   Asks for what kind of players are going to play.
   */
  private static boolean gameSetup() {

    JFrame gameOptions = new JFrame();

    // Gameplay options
    Object[] options = {"Human v. Human",
                        "Computer v. Human", "Human v. Computer", "Computer v. Computer", "Cancel"};

    // Allows the user to input a gameplay option
    gameType = JOptionPane.showOptionDialog(gameOptions,
                                            "Pick a gameplay option.... Short vs. Cut",
                                            "Select a game mode",
                                            JOptionPane.YES_NO_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            options,
                                            options[4]);

    // If the user chose to cancel or clicked the close button
    if (gameType == 4 || gameType == -1) {
      return false;
    }

    // Continue with setting up the players for the game
    playerSetup(gameType);
    return true;
  }

  /**
   Updates the mouse x,y coordinates and has the graph redrawn only when
   the mouse is moved.
   */
  private static void getMouseMovement() {

    // Get the next location of the mouse
    Point newLocation = new Point(panel.getMouseX(), panel.getMouseY());

    // If the mouse is in a new location
    if (!newLocation.equals(oldLocation)) {

      // Change the location and redraw the panel
      oldLocation = newLocation;
      graph.draw(panel);
    }
  }

  /**
   Will setup the players.

   @param option Game play option
   */
  private static void playerSetup(int option) {
    playerType[0] = 1;
    playerType[1] = 1;
    // Sets the players according to human and computer based on user option; 1 = human and 0 = computer
    if (option == 1) {

      playerType[0] = 0;

    } else if (option == 2) {

      playerType[1] = 0;

    } else if (option == 3) {

      playerType[0] = 0;
      playerType[1] = 0;
    }
  }

  /**
   Waits for a mouse click and then attempts to make that move.
   */
  private static void waitForMouse() {

    // Wait for a mouse click, if it has occurred
    if (panel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {

      // If a human has made a move
      if (makeHumanMove()) {

        // Refresh the panel, checkf or a potential winner, and change whose turn it is if necessary
        checkWinner();
        changeTurn();
        graph.draw(panel);
      }
    }
  }

  /**
   Makes the computer move based on if it is player ones or player twos
   turn.
   */
  private static void waitForComputer() {

    // Allow the computer to make a move based on whether it is playing cut or short
    if (turn == 0) {

      graph.makeShortMove();

    } else {

      graph.makeCutMove();
    }

    // Check for a potential winner and change the turn if necessary
    panel.sleep(1000);
    checkWinner();
    changeTurn();
  }

  /**
   If the current player is a human wait for the mouse otherwise wait
   for the computer.
   */
  private static void determineMoveType() {

    //If the player is human, wait for mouse input
    if (playerType[turn] == 1) {

      waitForMouse();

      // Else, wait for the computer to calculate its move
    } else {

      waitForComputer();
    }
  }

  /**
   Makes the correct human move call based on who's turn it is.

   @return boolean if the move was successful
   */
  private static boolean makeHumanMove() {
    return turn == 0 ? graph.lockEdgeAt(oldLocation) : graph.cutEdgeAt(oldLocation);
  }

  /**
   Changes the turn to the next player.
   */
  private static void changeTurn() {

    // Toggle what player has the current turn
    turn = (turn == 1) ? 0 : 1;
  }

  /**
   Update the winner variables to see if a player has won.
   */
  private static void checkWinner() {

    winner[0] = graph.shortWins();
    winner[1] = graph.cutWins();
  }

  /**
   Display the winner of the game and asks to play again.
   */
  private static void results() {

    JFrame gameOptions = new JFrame();

    Object[] options = {"Yes", "No, exit",};

    // Set the proper message for who won
    String winMsg = winner[0] ? "Short Wins!" : "Cut Wins!";

    // Set the game type to a new game or quit
    gameType = JOptionPane.showOptionDialog(gameOptions,
                                            winMsg + "\nPlay again?",
                                            winMsg,
                                            JOptionPane.YES_NO_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            options,
                                            options[1]);

    // If the user chose to quit, do so
    if (gameType == 1 || gameType == -1) {

      System.exit(0);

      // Else, start a new game
    } else {

      start();
    }
  }
}