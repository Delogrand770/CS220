import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 Description: Uses the SimpleWeightedGraph class from
 http://www.jgrapht.org/ to represent a graph to play the Shannon
 Switching Game: http://en.wikipedia.org/wiki/Shannon_switching_game

 Documentation Statement: none.

 @author C14Brandon.Daley
 @author C14Gavin.Delphia
 @author Randall.Bower
 */
public class ShannonSwitchingGraph {

  // Vertices are represented with the java.awt.Point class,
  // edges use the DefaultWeightedEdge class from JGraphT.
  private SimpleWeightedGraph<Point, DefaultWeightedEdge> graph;
  // The start and end vertices the players are trying to connect/cut.
  private Point start, end;
  // The dimensions of the graphic area where this graph will be displayed.
  private int width, height;
  // A random number generator used to create the random vertices and edges.
  private Random rand;
  // Width and height of each vertex
  private int vertexSize = 6;
  // ArrayList of Line2D objects created from constructed edges
  public ArrayList<Line2D> lines = new ArrayList<>();
  // Reasonable distances calculated from width and height, dealing with proper spacing of vertices
  private int reasonableX, reasonableY, reasonableDistance;

  /**
   Constructs a new graph for the Shannon Switching Game such that all
   vertices are within the given dimensions of a graphic display.

   @param width With of area on which this graph will be displayed.
   @param height Height of area on which this graph will be displayed.
   */
  public ShannonSwitchingGraph(int width, int height) {

    // The dimensions of the graphic area on which this graph will be displayed.
    this.width = width;
    this.height = height;

    // Calcultes reasonable distance variables based on width and height
    this.reasonableX = (int) (this.width * (.10));
    this.reasonableY = (int) (this.height * (.10));
    this.reasonableDistance = (int) Math.sqrt(Math.pow(reasonableX, 2) + Math.pow(reasonableY, 2)) / 3;

    // Random number generated used in creating both vertices and edges.
    // Add a seed here to generate the same graph every time (for testing).
    this.rand = new Random();
    // this.rand.setSeed(2014);

    // Create the graph, vertices, and edges.
    this.graph = new SimpleWeightedGraph(DefaultWeightedEdge.class);
    createVertices();
    createEdges();
  }

  /**
   Creates random vertices within the dimensions of the graphic area on
   which this graph will be displayed while ensuring vertices are spaced
   appropriately from the edges and each other.
   */
  private void createVertices() {
    // buffer space that keeps vertices away from the drawingPanel edges
    int buffer = 25;
    // Number of vertices generated, based on area of the window
    int totalVertices = (int) (this.height * this.width / this.vertexSize / 400);
    // Keeps track of how many vertices are created
    int counter = 0;
    // Counts how many failures to place a vertex
    int attempts = 0;

    // Create the start and end vertices
    this.start = new Point(this.rand.nextInt(reasonableX - buffer) + buffer * 3, this.rand.nextInt(reasonableY - buffer) + buffer * 3);
    this.end = new Point(this.width - (this.rand.nextInt(reasonableX - buffer) + buffer * 3), this.height - (this.rand.nextInt(reasonableY - buffer) + buffer * 3));

    //Add the star and end vertices to the graph
    this.graph.addVertex(this.start);
    this.graph.addVertex(this.end);

    // Attempt to draw vertices until they are all drawn or we exceed the numberof attempts to draw
    while (counter < totalVertices && attempts < totalVertices * 4) {

      // Create a random point
      Point randPoint = new Point(this.rand.nextInt(this.width - buffer * 2) + buffer, this.rand.nextInt(this.height - buffer * 2) + buffer);
      // Variable to set whether we should draw the point or not based on distance
      boolean shouldDraw = true;

      // For all the points in the vertex set
      for (Point current : this.graph.vertexSet()) {

        // Check the distance and determine whether to draw or not

        if (randPoint.distance(current) <= reasonableDistance) {
          shouldDraw = false;
        }
      }

      // Check shouldDraw; if shouldDraw is true, add the vertex to the graph and increase the counter
      if (shouldDraw) {
        this.graph.addVertex(randPoint);
        counter++;
      }

      // Always increment how many drawing attempts, positive or not, that have occurred
      attempts++;
    }
  }

  /**
   Randomly creates edges attempting to make the degree of each vertex
   between 3 and 5. Must ensure no two edges overlap, so some vertices
   may end up with a smaller degree (especially those near the corners
   of the graph ... which is why the start and end vertices are not
   placed completely in the corners!)
   */
  private void createEdges() {

    //Go through all the vertices
    for (Point current : this.graph.vertexSet()) {

      // Get an ArrayList of sorted points by relative distance from the current point, nondecreasing
      ArrayList<Point> sortedPoints = getSortedPoints(current);

      int numberEdges = this.rand.nextInt(2) + 3; // At least 3, up to 5.
      int counter = 0; // Tracks how many points we have attempted to connect to.

      //While the vertice does not have enough edges connected to it try to add another
      //Also ends if we have have no more points to make an edge with
      while (counter < sortedPoints.size() && this.graph.degreeOf(current) < numberEdges) {

        Line2D newLine = new Line2D.Float(current, sortedPoints.get(counter));

        //Add the edge if it does not intersect any other edges, if its degree is less than 5 and if its not too close to other vertices
        if (this.graph.degreeOf(sortedPoints.get(counter)) < 5 && checkIntersects(newLine, lines) && notTooClose(newLine)) {

          DefaultWeightedEdge edge = this.graph.addEdge(current, sortedPoints.get(counter));

          // if the instance of the edge does'nt exist yet, because it doesn't support multiplicity
          if (edge != null) {
            this.graph.setEdgeWeight(edge, 1.0);
            lines.add(newLine);
          }
        }

        counter++;
      }
    }
  }

  /**
   Checks if one line intersects another line

   @param source The potential line (edge) we are trying to add
   @param targets The arraylist of lines (edges) that we have already
   created
   @return boolean if the intersection occurs or not
   */
  private boolean checkIntersects(Line2D source, ArrayList<Line2D> targets) {

    // FOr all the lines already created
    for (Line2D line : targets) {

      // If an intersection occurs
      if (line.intersectsLine(source) && !line.getP1().equals(source.getP1()) && !line.getP1().equals(source.getP2()) && !line.getP2().equals(source.getP1()) && !line.getP2().equals(source.getP2())) {
        return false;
      }
    }

    return true;
  }

  /**
   Determines if the Short player wins the game.

   @return True if the Short player wins the game; false otherwise.
   */
  public boolean shortWins() {

    // Get a shortest path from start to end
    DijkstraShortestPath path = new DijkstraShortestPath(graph, start, end);

    // Return whether there is a shortest path and if the weight is 0.0 along the path, meaning short wins
    return path.getPath() == null ? false : path.getPath().getWeight() == 0.0;
  }

  /**
   Determines if the Cut player wins the game.

   @return True if the Cut player wins the game; false otherwise.
   */
  public boolean cutWins() {

    // Get a shortest path from start to end
    DijkstraShortestPath path = new DijkstraShortestPath(graph, start, end);

    // If the path is null, there is no path from start to finish, meaning cut wins
    return path.getPath() == null;
  }

  /**
   This method is called when a human is playing as the Short player. It
   finds the edge in the graph closest to the given coordinates and if
   the edge is within four pixels and has a weight greater than 0.0, it
   will be locked by setting the edge weight to 0.0. If there is no edge
   within four pixels of the given coordinates with weight greater than
   0.0, no edge is locked.

   @param x X-coordinate of the edge to lock.
   @param y Y-coordinate of the edge to lock.
   @return True if an edge is successfully locked; false otherwise.
   */
  public boolean lockEdgeAt(Point currentPoint) {

    // Get the index of the line nearest the mouse
    int index = getClosestLine(currentPoint);

    // If  it is a valid index
    if (index != -1) {

      // Set the current line to the closest line
      Line2D current = lines.get(index);
      // Set an instance of an edge to a form of the current line
      DefaultWeightedEdge edge = graph.getEdge(toPoint(current.getP1()), toPoint(current.getP2()));

      // If the line still has a weight of 1.0
      if (graph.getEdgeWeight(edge) > 0.0) {

        // Set the weight to 0.0, effectively locking it
        graph.setEdgeWeight(edge, 0.0);
        return true;
      }
    }

    return false;
  }

  /**
   This method is called when a human is playing as the Cut player. It
   finds the edge in the graph closest to the given coordinates and if
   the edge is within four pixels and has weight greater than 0.0, it is
   removed from the graph. If there is no edge within four pixels of the
   given coordinates with weight greater than 0.0, no edge is removed.

   @param x X-coordinate of the edge to cut.
   @param y Y-coordinate of the edge to cut.
   @return True if an edge is successfully cut; false otherwise.
   */
  public boolean cutEdgeAt(Point currentPoint) {

    // Get the index of the line nearest the mouse
    int index = getClosestLine(currentPoint);

    // If  it is a valid index
    if (index != -1) {

      // Set the current line to the closest line
      Line2D current = lines.get(index);
      // Set an instance of an edge to a form of the current line
      DefaultWeightedEdge edge = graph.getEdge(toPoint(current.getP1()), toPoint(current.getP2()));

      // If the line still has a weight of 1.0
      if (graph.getEdgeWeight(edge) > 0.0) {

        // Remove the line from the line ArrayList and the edge from the vertex
        lines.remove(index);
        graph.removeEdge(edge);
        return true;
      }
    }

    return false;
  }

  /**
   Converts a Point2D to a normal point since type casting does not work
   and sometimes we can't use a Point2D.

   @param Point2D to convert
   @return a point representation of the Point2D
   */
  private Point toPoint(Point2D point) {
    return new Point((int) point.getX(), (int) point.getY());
  }

  /**
   This method is called when the computer is playing as the Short
   player. It will select an edge and lock it by setting the edge weight
   to 0.0.
   */
  public void makeShortMove() {
    DijkstraShortestPath path = new DijkstraShortestPath(graph, start, end);
    java.util.List edge = path.getPathEdgeList();
    ArrayList<DefaultWeightedEdge> edges = new ArrayList<>();
    for (Iterator it = edge.iterator(); it.hasNext();) {
      DefaultWeightedEdge e = (DefaultWeightedEdge) it.next();
      if (graph.getEdgeWeight(e) != 0.0) {
        edges.add(e);
      }
    }
    int index = rand.nextBoolean() ? 0 : edges.size() - 1;
    graph.setEdgeWeight(edges.get(index), 0.0);
    draw(Client.panel);
  }

  /**
   This method is called when the computer is playing as the Cut player.
   It will select an edge and remove it from the graph.
   */
  public void makeCutMove() {
    DijkstraShortestPath path = new DijkstraShortestPath(graph, start, end);
    java.util.List edge = path.getPathEdgeList();
    for (Iterator it = edge.iterator(); it.hasNext();) {
      DefaultWeightedEdge e = (DefaultWeightedEdge) it.next();
      if (graph.getEdgeWeight(e) != 0.0) {
        removeLine(e);
        graph.removeEdge(e);
        break;
      }
    }
    draw(Client.panel);
  }

  /**
   Removes a line from the arrayList lines based on a given edge

   @param e The defaultWeightedEdge to compare lines against
   */
  public void removeLine(DefaultWeightedEdge e) {
    Point v1 = graph.getEdgeSource(e);
    Point v2 = graph.getEdgeTarget(e);
    for (int i = 0; i < lines.size(); i++) {
      Line2D line = lines.get(i);
      if (line.getX1() == v1.getX() && line.getX2() == v2.getX() && line.getY1() == v1.getY() && line.getY2() == v2.getY()) {
        lines.remove(i);
        break;
      }
    }
  }

  /**
   Draws this graph on a DrawingPanel. This method will only be used if
   the GUI is done with a DrawingPanel object.

   @param panel The DrawingPanel object on which to draw this graph.
   */
  public void draw(DrawingPanel panel) {
    panel.setBackground(Color.WHITE); // Clears the drawing panel.
    this.paint(panel.getGraphics());  // Uses the paint method to actually draw the graph.
    panel.copyGraphicsToScreen();       // Updates the screen due to double buffering.
  }

  /**
   Draws this graph using the given Graphics object. This method will be
   called from the draw method above if using a DrawingPanel or from the
   paintComponent method if using your own GUI with a JPanel.

   @param graphics Graphics object to use to draw this graph.
   */
  public void paint(Graphics graphics) {

    // Convert graphics to a Graphics2D object for smoother graphic views
    Graphics2D g2d = (Graphics2D) graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    // Set the thickness of the lines
    g2d.setStroke(new BasicStroke(2));

    g2d.drawString(Client.turn == 0 ? "Short's Turn" : "Cut's Turn", 10, 15);

    // Draw all the edges
    for (DefaultWeightedEdge current : this.graph.edgeSet()) {

      graphics.setColor(Color.BLACK);
      Double weight = graph.getEdgeWeight(current);
      Point source = graph.getEdgeSource(current);
      Point target = graph.getEdgeTarget(current);

      // If the line is locked, draw it in a different color
      if (weight == 0.0) {

        graphics.setColor(Color.PINK);
      }

      graphics.drawLine((int) source.getX(), (int) source.getY(), (int) target.getX(), (int) target.getY());
    }

    //Start highlighting
    int index = getClosestLine(Client.oldLocation);

    // If there is a closest edge, -1 means no closest edges
    if (index != -1) {

      Line2D line = lines.get(index);
      graphics.setColor(Color.GREEN);
      graphics.drawLine((int) line.getP1().getX(), (int) line.getP1().getY(), (int) line.getP2().getX(), (int) line.getP2().getY());
      graphics.setColor(Color.BLACK);
    }
    //End highlight code


    int count = 0;
    graphics.setColor(Color.RED);

    // Draws all the vertices
    for (Point current : this.graph.vertexSet()) {

      // If the start and end vertices are drawn, change the color
      if (count > 1) {

        graphics.setColor(Color.BLACK);
      }

      graphics.fillOval((int) current.getX() - (vertexSize / 2), (int) current.getY() - (vertexSize / 2), vertexSize, vertexSize);
      count++;
    }
  }

  /**
   Returns a arraylist of all the vertices sorted against a point

   @param target The point to sort against
   @return The arraylist of sorted points
   */
  private ArrayList<Point> getSortedPoints(Point target) {

    ArrayList<Point> sortedPoints = new ArrayList<>();

    for (Point current : this.graph.vertexSet()) {

      //Don't add our target point to the list
      if (!target.equals(current)) {

        sortedPoints.add(current);
      }
    }

    return bubble(0, sortedPoints.size() - 1, sortedPoints, target);
  }

  /**
   Bubble sorts an arraylist of points against another point in terms of
   distance.

   @param left Left bound of the bubble sort
   @param right Right bound of the bubble sort
   @param data The arraylist of points
   @param start The point to sort against
   @return The sorted list of points
   */
  private ArrayList<Point> bubble(int left, int right, ArrayList<Point> data, Point start) {

    // This variable allows the sort to stop if it ever
    // makes a pass without swapping at least one value.
    boolean swappedOne = true; // True so the loop can start.

    // Each pass "bubbles" the largest element to the right.
    for (int pass = left; pass < right && swappedOne; pass++) {

      swappedOne = false; // Haven't swapped one yet on this pass.

      // Why does this loop stop at (right - pass)?
      for (int i = left; i < right - pass; i++) {

        if (data.get(i).distance(start) > data.get(i + 1).distance(start)) {

          Point temp = data.get(i);
          data.set(i, data.get(i + 1));
          data.set(i + 1, temp);
          swappedOne = true;
        }
      }
    }

    return data;
  }

  /**
   Prevents edges from drawing to close to other vertices

   @param line line representation of the edge currently trying to be
   added
   @return boolean whether the possible edge is too close or not
   */
  private boolean notTooClose(Line2D line) {

    //Looks through all the verticies and checks their distance from the line
    for (Point current : this.graph.vertexSet()) {

      double dist = line.ptSegDist(current.getX(), current.getY());

      //If the distance is 0.0 it means the vertice in question is part of the edge
      if (dist < this.reasonableDistance / 3 && dist != 0.0) {

        return false;
      }
    }

    return true;
  }

  /**
   Takes the x and y coordinates of the mouse and returns the index of
   the closest line.

   @param x x-coordinate of the mouse
   @param y y-coordinate of the mouse
   @return index of closest line to mouse
   */
  private int getClosestLine(Point currentPoint) {

    // Default values to prevent a line from highlighting before the mouse is on the screen
    int index = -1;
    int smallestDistance = -1;

    // If the mouse is on the screen
    if (!currentPoint.equals(new Point(-1, -1))) {

      // Finds the closest line to the mouse
      for (int i = 0; i < lines.size(); i++) {

        Line2D line = lines.get(i);
        int currentDistance = (int) line.ptSegDist(currentPoint.getX(), currentPoint.getY());
        DefaultWeightedEdge edge = graph.getEdge(toPoint(line.getP1()), toPoint(line.getP2()));

        if (graph.getEdgeWeight(edge) != 0.0) {

          // If a smaller distance is found, set the index to that line as the current closest
          if (currentDistance <= smallestDistance || smallestDistance == -1) {

            smallestDistance = currentDistance;
            index = i;
          }
        }
      }
    }

    // Return the index of the closest line to the mouse
    return (smallestDistance < this.reasonableDistance / 3) ? index : -1;
  }

  /**
   Runs the client code.

   @param args not used
   */
  public static void main(String[] args) {

    Client.main(args);
  }
}