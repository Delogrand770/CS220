
/**
 * CS220 Lesson 31: Demonstration of JGraphT graph library
 * Exercise 1: Get this file to compile and load jgrapht JavaDoc
 *  a. download JGraphT from http://prdownloads.sourceforge.net/jgrapht/jgrapht-0.8.3.zip?download
 *  b. unzip jgraph7-0.8.3.zip to your NetBeans project folder
 *  c. right click on the Libraries folder of the package containing this file
 *  d. select Add JAR/Folder ... and browse to the jgrapht-jdk1.6.jar file in jgrapht-0.8.3
 *  e. select Tools -> Libraries -> Javadoc tab -> Add ZIP/Folder ...
 *  f. select the javadoc folder in jgrapht-0.8.3
 * 
 * @author David.Gibson
 * 
 * @version 1.0 - Apr 9, 2012 at 8:59:50 PM
 * 
 * Documentation: borrowed code from HelloJGraphT.java demo program
 */
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class WeightedGraphDemo {

  /**
   * Create a simple undirected graph of String nodes and display it
   * @param args 
   */
  
  public static void main(String[] args) {

    SimpleGraph<String, DefaultEdge> stringGraph = createGraph();

    System.out.println(stringGraph.toString());

    SimpleWeightedGraph<String, DefaultWeightedEdge> weightedGraph = createWeightedGraph();
    
    System.out.println(weightedGraph.toString());
    
  }

  /**
   * Create an undirected graph g with 4 vertices {v1, v2, v3, v4} represented
   * as strings and 4 undirected edges {<v1,v2>, <v2,v3>, <v3, v4>, <v4,v1>}
   * @return graph g
   */
  private static SimpleGraph<String, DefaultEdge> createGraph() {

    // use a SimpleGraph representation to represent an UndirectedGraph
    
    SimpleGraph<String, DefaultEdge> g = new SimpleGraph(DefaultEdge.class);

    String v1 = "Denver";
    String v2 = "Colorado Springs";
    String v3 = "Pueblo";
    String v4 = "Limon";

    // add the vertices to graph
    g.addVertex(v1);
    g.addVertex(v2);
    g.addVertex(v3);
    g.addVertex(v4);

    // add edges to graph
    g.addEdge(v1, v2); // I-25 connects Denver to Colorado Springs
    g.addEdge(v2, v3); // I-25 connects Colorado Springs to Pueblo
    g.addEdge(v1, v4); // I-70 connects Denver to Limon
    g.addEdge(v2, v4); // SR-24 connects Colorado Springs to Limon

    // return created graph
    return g;
  }
  
    /**
   * Create a SimpleGraph g with 4 vertices {v1, v2, v3, v4} represented
   * as strings and 4 undirected edges {<v1,v2>, <v2,v3>, <v3, v4>, <v4,v1>}
   * @return graph g
   */
  private static SimpleWeightedGraph<String, DefaultWeightedEdge> createWeightedGraph() {

    // use a SimpleGraph representation to represent an UndirectedGraph
    
    SimpleWeightedGraph<String, DefaultWeightedEdge> g = new SimpleWeightedGraph(DefaultWeightedEdge.class);

    String v1 = "Denver";
    String v2 = "Colorado Springs";
    String v3 = "Pueblo";
    String v4 = "Limon";

    // add the vertices to graph
    g.addVertex(v1);
    g.addVertex(v2);
    g.addVertex(v3);
    g.addVertex(v4);

    // add edges and wights to graph

    DefaultWeightedEdge edge;
    
    edge = g.addEdge(v1, v2); // I-25 connects Denver to Colorado Springs
    g.setEdgeWeight(edge, 68.5);
    
    edge = g.addEdge(v2, v3); // I-25 connects Colorado Springs to Pueblo
    g.setEdgeWeight(edge, 43.6);
    
    edge = g.addEdge(v1, v4); // I-70 connects Denver to Limon
    g.setEdgeWeight(edge, 88.1);
    
    edge = g.addEdge(v2, v4); // SR-24 connects Colorado Springs to Limon
    g.setEdgeWeight(edge, 73.2);
    
    // return created graph
    return g;
  }
  
}
