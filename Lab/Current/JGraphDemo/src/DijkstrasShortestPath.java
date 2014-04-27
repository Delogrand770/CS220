
import java.util.PriorityQueue;
import java.util.TreeMap;
import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 An implementation of the Dijkstra's Shortest Path algorithm.

 @author Randall.Bower
 */
public class DijkstrasShortestPath {

  /**
   Main method builds the graph and calls the shortest path method. The
   org.jgrapht.alg.DijkstraShortestPath class is also used as a
   reference.

   @param args Ignored.
   */
  public static void main(String[] args) {
    SimpleWeightedGraph<Character, DefaultWeightedEdge> graph = buildGraph();

    System.out.println("Shortest paths:");
    dijkstra(graph, 'A', 'H');
    dijkstra(graph, 'H', 'A');
    dijkstra(graph, 'A', 'G');
    dijkstra(graph, 'G', 'A');
    dijkstra(graph, 'C', 'G');
    dijkstra(graph, 'G', 'C');
    dijkstra(graph, 'B', 'Q');
    dijkstra(graph, 'Q', 'B');
    dijkstra(graph, 'B', 'B');

    System.out.println("DijkstraShortestPath:");
    System.out.println(new DijkstraShortestPath(graph, 'A', 'H').getPath());
    System.out.println(new DijkstraShortestPath(graph, 'H', 'A').getPath());
    System.out.println(new DijkstraShortestPath(graph, 'A', 'G').getPath());
    System.out.println(new DijkstraShortestPath(graph, 'G', 'A').getPath());
    System.out.println(new DijkstraShortestPath(graph, 'C', 'G').getPath());
    System.out.println(new DijkstraShortestPath(graph, 'G', 'C').getPath());
    System.out.println(new DijkstraShortestPath(graph, 'B', 'Q').getPath());
    System.out.println(new DijkstraShortestPath(graph, 'Q', 'B').getPath());
    System.out.println(new DijkstraShortestPath(graph, 'B', 'B').getPath());
  }

  /**
   Graph from Lesson 33.

   18 C ------------- F / \ / \ 3/ \15 4/ \10 / 7 \ 5 / 19 \ A ----- B
   ----- D ----- H \ / \3 / 6\ 5/ Q /4 \ / / E ------------- G 22
   */
  private static SimpleWeightedGraph<Character, DefaultWeightedEdge> buildGraph() {
    SimpleWeightedGraph<Character, DefaultWeightedEdge> graph = new SimpleWeightedGraph(DefaultWeightedEdge.class);

    for (char ch = 'A'; ch <= 'H'; ch++) {
      graph.addVertex(ch);
    }
    graph.addVertex('Q'); // For some reason this graph has vertices A-H ... and then Q. Go figure.

    DefaultWeightedEdge edge = graph.addEdge('A', 'B');
    graph.setEdgeWeight(edge, 7.0);
    edge = graph.addEdge('A', 'C');
    graph.setEdgeWeight(edge, 3.0);
    edge = graph.addEdge('A', 'E');
    graph.setEdgeWeight(edge, 6.0);
    edge = graph.addEdge('B', 'C');
    graph.setEdgeWeight(edge, 15.0);
    edge = graph.addEdge('B', 'D');
    graph.setEdgeWeight(edge, 5.0);
    edge = graph.addEdge('B', 'E');
    graph.setEdgeWeight(edge, 5.0);
    edge = graph.addEdge('B', 'Q');
    graph.setEdgeWeight(edge, 3.0);
    edge = graph.addEdge('C', 'F');
    graph.setEdgeWeight(edge, 18.0);
    edge = graph.addEdge('D', 'F');
    graph.setEdgeWeight(edge, 4.0);
    edge = graph.addEdge('D', 'H');
    graph.setEdgeWeight(edge, 19.0);
    edge = graph.addEdge('E', 'G');
    graph.setEdgeWeight(edge, 22.0);
    edge = graph.addEdge('F', 'H');
    graph.setEdgeWeight(edge, 10.0);
    edge = graph.addEdge('G', 'H');
    graph.setEdgeWeight(edge, 4.0);

    return graph;
  }

  // Uses Dijkstra's algorithm to find the shortest path between two vertices.
  private static void dijkstra(SimpleWeightedGraph<Character, DefaultWeightedEdge> graph,
                               Character startVertex, Character endVertex) {
    PriorityQueue<Tuple> pq = new PriorityQueue();
    TreeMap<Character, Double> weightSum = new TreeMap();
    TreeMap<Character, Character> predecessor = new TreeMap();
    // Initialize the weightSum for all vertices to Double.MAX_VALUE.

    // Initialize the weightSum of the start vertex to 0.0.

    // Initialize the predecessor of the start vertex to itself.


    // Add a tuple to pq for the start vertex (with itself as predecessor) and weight 0.0.

    // Initialize a temporary vertex variable (v) to the start vertex.
    Character v = startVertex;

    // While the end vertex hasn't been removed from pq and pq is not empty
    while (true) {
      // Remove a tuple from pg (which will have the lowest weight) and set v to the vertex in the tuple.

      // If this is a better way to get to the vertex than is currently
      // stored in the weightSum map, update info about the vertex.

      // Put all of v's neighbors in the priority queue with v as the
      // predecessor and the weight in the tuple as the sum of the weight
      // to get to v plus the weight of the edge from v to the neighbor.
      for (DefaultWeightedEdge edge : graph.edgesOf(v)) {
      }
    }

    // If a path was found, show it; otherwise show an error.

  }

  // A class to hold a tuple of values: a vertex in the path, its predecessor
  // in the path that reached it, and the weight of the path to reach it.
  private static class Tuple implements Comparable<Tuple> {

    public Character vertex; // The vertex along the shortest path this tuple holds.
    public Character predecessor; // The predecessor of this vertex along the shortest path.
    public Double weight; // The weight of getting to this vertex along the shortest path.

    /**
     Constructs a tuple with the given values.

     @param v The vertex to store in this tuple.
     @param p The predecessor of this vertex in the shortest path so
     far.
     @param w The weight to get to this vertex in the shortest path so
     far.
     */
    public Tuple(Character v, Character p, Double w) {
      this.vertex = v;
      this.predecessor = p;
      this.weight = w;
    }

    /**
     Tuples are compared based on the weight in the tuple.

     @param that The tuple to compare this tuple to.
     @return True if this tuple is "less than" that tuple.
     */
    @Override
    public int compareTo(Tuple that) {
      return this.weight.compareTo(that.weight);
    }

    /**
     String representation of this tuple.

     @return String representation of this tuple.
     */
    @Override
    public String toString() {
      return this.vertex + ", " + this.predecessor + ", " + this.weight;
    }
  }
}
