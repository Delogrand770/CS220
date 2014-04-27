
/**
 CS220: Lesson 32/33 - breadth first and depth first graph traversals
 Exercise: 1 - Implement a (non-recursive) breadth first graph traversal
 2 - Implement a (non-recursive) depth first graph traversal 3 -
 Implement a recursive depth first graph traversal 4 - Create a
 SimpleWeightedGraph using the DefaultWeightedEdge and add weights to
 the graph 5 - Implement Prim's algorithm to find a minimum spanning
 tree

 @author

 */
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class GraphTraversals {

  public static void main(String[] args) {

    SimpleGraph<Character, DefaultEdge> graph;

    // build a simple graph and display its verticies and edges
    graph = buildSimpleGraph();
    System.out.println(graph.toString());

    // breadth first traverse graph starting at the given vertex
    breadthFirstTraverse(graph, 'A');

    // depth first traverse graph starting at the give vertex
    depthFirstTraverseIterative(graph, 'A');
    depthFirstTraverseRecursive(graph, 'A');

  }

  /**
   preforms a breadth first traversal of graph from startVertex and
   displays the order of vertex visits

   @param graph - graph traversed
   @param startVertex - first vertex visited
   */
  private static void breadthFirstTraverse(SimpleGraph<Character, DefaultEdge> graph,
                                           Character startVertex) {
    // EXERCISE: add code here to implement a beadth first traversal
    //           and display the vertex visit order
    // HINT: This method should not be recursive
    // HINT: Use a list as a queue

    // Choose a vertex v and mark it as visited
    LinkedList<Character> visited = new LinkedList();
    visited.add(startVertex);

    // Add all ov v's neighbors to a list of verticies that can be visited
    Queue<Character> toVisit = new LinkedList();
    for (DefaultEdge edge : graph.edgesOf(startVertex)) {
      Character v = graph.getEdgeSource(edge);
      if (v == startVertex) {
        v = graph.getEdgeTarget(edge);
      }
      toVisit.add(v);
    }

    // While there are verticies that can be visited and all verticies haven't been visited
    while (!toVisit.isEmpty() && visited.size() < graph.vertexSet().size()) {
      // Get a vertex v from the list of verticies that can be visited
      Character vertex = toVisit.remove(); //use pop for stack

      // Check if we have already visited this vertex
      if (!visited.contains(vertex)) {
        // Mark v as visited
        visited.add(vertex);

        // Add all of vertex's neighbors to the list of verticies that can be visited.
        for (DefaultEdge edge : graph.edgesOf(vertex)) {
          Character v = graph.getEdgeSource(edge);
          if (v == vertex) {
            v = graph.getEdgeTarget(edge);
          }
          toVisit.add(v);
        }
      }
    }

    System.out.println(visited);
  }

  /**
   preforms a depth first traversal of graph from startVertex and
   displays the order of vertex visits

   @param graph - graph traversed
   @param startVertex - first vertex visited
   */
  private static void depthFirstTraverseIterative(SimpleGraph<Character, DefaultEdge> graph,
                                                  Character startVertex) {
    // EXERCISE: add code here to implement a depth first traversal
    //           and display the vertex visit order
    // HINT: This method should not be recursive
    // HINT: Use a stack
    LinkedList<Character> visited = new LinkedList();
    visited.add(startVertex);

    // Add all ov v's neighbors to a list of verticies that can be visited
    Stack<Character> toVisit = new Stack<>();
    for (DefaultEdge edge : graph.edgesOf(startVertex)) {
      Character v = graph.getEdgeSource(edge);
      if (v == startVertex) {
        v = graph.getEdgeTarget(edge);
      }
      toVisit.add(v);
    }

    // While there are verticies that can be visited and all verticies haven't been visited
    while (!toVisit.isEmpty() && visited.size() < graph.vertexSet().size()) {
      // Get a vertex v from the list of verticies that can be visited
      Character vertex = toVisit.pop(); //use pop for stack

      // Check if we have already visited this vertex
      if (!visited.contains(vertex)) {
        // Mark v as visited
        visited.add(vertex);

        // Add all of vertex's neighbors to the list of verticies that can be visited.
        for (DefaultEdge edge : graph.edgesOf(vertex)) {
          Character v = graph.getEdgeSource(edge);
          if (v == vertex) {
            v = graph.getEdgeTarget(edge);
          }
          toVisit.add(v);
        }
      }
    }

    System.out.println(visited);
  }

  /**
   preforms a depth first traversal of graph from startVertex and
   displays the order of vertex visits

   @param graph - graph traversed
   @param startVertex - first vertex visited
   */
  private static void depthFirstTraverseRecursive(SimpleGraph<Character, DefaultEdge> graph,
                                                  Character startVertex) {
    // EXERCISE: add code here to implement a depth first traversal
    //           and display the vertex visit order
    // HINT: this method should call a recursive helper method to do the traversal
    // HINT: pass as a parameter a list that holds visited vertices in visit order
  }

  /**
   Build the following SimpleGraph:

   C ------------- F / \ / \ / \ / \ / \ / \ A ----- B ----- D ----- H \
   / \ / \ / I / \ / / E ------------- G

   @return graph, a JGraphT representation of the above graph
   */
  private static SimpleGraph<Character, DefaultEdge> buildSimpleGraph() {

    SimpleGraph<Character, DefaultEdge> graph = new SimpleGraph(DefaultEdge.class);

    char[] vertices = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I'};

    // add all verticies to the graph
    for (Character ch : vertices) {
      graph.addVertex(ch);
    }

    // add all edges to the graph
    graph.addEdge('A', 'B');
    graph.addEdge('A', 'C');
    graph.addEdge('A', 'E');
    graph.addEdge('B', 'C');
    graph.addEdge('B', 'D');
    graph.addEdge('B', 'E');
    graph.addEdge('B', 'I');
    graph.addEdge('C', 'F');
    graph.addEdge('D', 'F');
    graph.addEdge('D', 'H');
    graph.addEdge('E', 'G');
    graph.addEdge('F', 'H');
    graph.addEdge('G', 'H');

    return graph;
  }
}
