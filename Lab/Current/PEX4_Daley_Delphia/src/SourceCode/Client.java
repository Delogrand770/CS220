package SourceCode;

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
  public static boolean[] winner = {false, false}; // Instantiates both players to not winning, prevents re-calculating the shortest path since game continuously runs for edge highlighting
  // variables that define the state of a player or input
  public static final int SHORT = 0;
  public static final int CUT = 1;
  public static final int COMPUTER = 0;
  public static final int HUMAN = 1;
  public static final int INVALID_INPUT = -1;

  /**
  Main begins the game, going to the start() method which determines gameplay, sets players, and tracks status of the current game
  
  @param args not used
   */
  public static void main(String[] args) {

    // Reset both players to not winning and player types to human
    winner[SHORT] = false;
    winner[CUT] = false;

    playerType[0] = HUMAN;
    playerType[1] = HUMAN;

    // Set up the initial graph and draw it on the panel
    graph = new ShannonSwitchingGraph(WIDTH, HEIGHT);
    graph.draw(panel);

    // If the user wants to cease playing
    if (!gameSetup()) {
      System.exit(0);
    }

    // Get the initial location of the mouse
    oldLocation = new Point(panel.getMouseX(), panel.getMouseY());

    panel.setWindowTitle("Shannon Switching Graph Game");

    // Play until there is a winner or there are no more lines to click, checks winner at a certain point so that the game can run for highlighting
    while (!winner[SHORT] && !winner[CUT] && !graph.lines.isEmpty()) {

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
                                            "Pick a gameplay option.",
                                            "Select a game mode",
                                            JOptionPane.YES_NO_CANCEL_OPTION,
                                            JOptionPane.QUESTION_MESSAGE,
                                            null,
                                            options,
                                            options[4]);

    // If the user chose to cancel or clicked the close button
    if (gameType == 4 || gameType == INVALID_INPUT) {
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

    int compHuman = 1;
    int humanComp = 2;
    int compComp = 3;

    // Sets the players according to human and computer based on user option; 1 = human and 0 = computer
    if (option == compHuman) {

      playerType[SHORT] = COMPUTER;

    } else if (option == humanComp) {

      playerType[CUT] = COMPUTER;

    } else if (option == compComp) {

      playerType[SHORT] = COMPUTER;
      playerType[CUT] = COMPUTER;
    }
  }

  /**
  Waits for a mouse click and then attempts to make that move.
   */
  private static void getHumanMove() {

    // Wait for a mouse click, if it has occurred
    if (panel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {

      // If a human has made a move
      if (makeHumanMove()) {

        // Refresh the panel, check for a potential winner, and change whose turn it is if necessary
        checkWinner();
        changeTurn();
        graph.draw(panel); // draw needs to occur so the player turn on the drawing panel updates
      }
    }
  }

  /**
  Makes the computer move based on if it is player ones or player twos
  turn.
   */
  private static void getComputerMove() {

    // Allow the computer to make a move based on whether it is playing cut or short
    if (turn == SHORT) {

      graph.makeShortMove();

    } else {

      graph.makeCutMove();
    }

    // Check for a potential winner and change the turn if necessary
    panel.sleep(500); //Delay for the computers. 500 = 1/2 second
    checkWinner();
    changeTurn();
    graph.draw(panel); //draw needs to occur so that the player turn on the drawing panel updates
  }

  /**
  If the current player is a human wait for the mouse otherwise wait
  for the computer.
   */
  private static void determineMoveType() {

    //If the player is human, wait for mouse input
    if (playerType[turn] == HUMAN) {

      getHumanMove();

      // Else, wait for the computer to calculate its move
    } else {

      getComputerMove();
    }
  }

  /**
  Makes the correct human move call based on who's turn it is.
  
  @return boolean if the move was successful
   */
  private static boolean makeHumanMove() {

    return turn == SHORT ? graph.lockEdgeAt(oldLocation) : graph.cutEdgeAt(oldLocation);
  }

  /**
  Changes the turn to the next player.
   */
  private static void changeTurn() {

    // Toggle what player has the current turn
    turn = (turn == CUT) ? SHORT : CUT;

  }

  /**
  Update the winner variables to see if a player has won.
   */
  private static void checkWinner() {

    winner[SHORT] = graph.shortWins();
    winner[CUT] = graph.cutWins();
  }

  /**
  Display the winner of the game and asks to play again.
   */
  private static void results() {

    int exit = 1;

    JFrame gameOptions = new JFrame();

    Object[] options = {"Yes", "No, exit.",};

    // Set the proper message for who won
    String winMsg = winner[SHORT] ? "Short Wins!" : "Cut Wins!";

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
    if (gameType == exit || gameType == INVALID_INPUT) {

      System.exit(0);

      // Else, start a new game
    } else {

      String[] args = null;
      main(args);
    }
  }
}
