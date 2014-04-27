
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

/**
 An implementation of Prim's Minimal Spanning Tree algorithm.

 @author Randall.Bower
 */
public class PrimsMinimalSpanningTree {

  /**
   Main method builds the graph and calls the minimal spanning tree
   method.

   @param args Ignored.
   */
  public static void main(String[] args) {
    SimpleWeightedGraph<Character, DefaultWeightedEdge> graph = buildGraph();

    System.out.println("Mimimal Spanning Trees:");
    prim(graph, 'A');
    prim(graph, 'D');
    prim(graph, 'Q');
    prim(graph, 'H');
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

  // Uses Prim's algorithm to find a minimal spanning tree starting with the given vertex.
  private static void prim(SimpleWeightedGraph<Character, DefaultWeightedEdge> graph, Character vertex) {
    // The minimal spanning tree we are trying to build.
    LinkedList<DefaultWeightedEdge> mst = new LinkedList();
    // List of vertices that have already been included in the minimal spanning tree.
    LinkedList<Character> visitedVertices = new LinkedList();
    // Priority queue of edges connected to the current minimal spanning tree, sorted by lowest weight,
    PriorityQueue<DefaultWeightedEdge> pq = new PriorityQueue(graph.edgeSet().size(),
                                                              new EdgeWeightComparator(graph));

    // Start by visiting the start vertex (passed as a parameter to this method).
    visitedVertices.add(vertex);

    // Add all edges from this vertex to the priority queue.
    for (DefaultWeightedEdge edge : graph.edgesOf(vertex)) {
      pq.add(edge);
    }
    // Continue until all vertices are visited or we run out of edges (a disconnected graph)
    while (visitedVertices.size() < graph.vertexSet().size() && !pq.isEmpty()) {
      // Get the next edge from the priority queue, which will be the one with the smallest weight.
      DefaultWeightedEdge edge = pq.poll();

      // One of the two vertices has already been visited, so find the other.
      vertex = graph.getEdgeSource(edge);
      if (visitedVertices.contains(vertex)) {
        vertex = graph.getEdgeTarget(edge);
      }

      // It both vertices of the edge have not been visited.
      if (!visitedVertices.contains(vertex)) {
        // Found a non-visited vertex, so mark it as visited, add the edge that got us
        // here to the tree, and all all edges from this vertex to the priority queue.
        visitedVertices.add(vertex);
        mst.add(edge);
        for (DefaultWeightedEdge edge2 : graph.edgesOf(vertex)) {
          pq.add(edge2);
        }
      }
    }

    // A minimal spanning tree will connect all vertices, which requires
    //  V-1 edges where V is the number of vertices in the graph.
    // So, if a minimal spanning tree was found, show it; otherwise show an error.
    System.out.println(mst);
  }

  // A comparator for DefaultWeightedEdge objects that compares edge weight.
  private static class EdgeWeightComparator implements Comparator<DefaultWeightedEdge> {

    private SimpleWeightedGraph<Character, DefaultWeightedEdge> graph;

    public EdgeWeightComparator(SimpleWeightedGraph<Character, DefaultWeightedEdge> graph) {
      this.graph = graph;
    }

    @Override
    public int compare(DefaultWeightedEdge e1, DefaultWeightedEdge e2) {
      return new Double(this.graph.getEdgeWeight(e1)).compareTo(this.graph.getEdgeWeight(e2));
    }
  }
}
