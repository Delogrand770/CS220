
/**
 CS220 Lesson 31: Demonstration of JGraphT graph library Exercise 1: Get
 this file to compile and load jgrapht JavaDoc a. download JGraphT from
 http://prdownloads.sourceforge.net/jgrapht/jgrapht-0.8.3.zip?download
 b. unzip jgraph7-0.8.3.zip to your NetBeans project folder c. right
 click on the Libraries folder of the package containing this file d.
 select Add JAR/Folder ... and browse to the jgrapht-jdk1.6.jar file in
 jgrapht-0.8.3 e. select Tools -> Libraries -> Javadoc tab -> Add
 ZIP/Folder ... f. select the javadoc folder in jgrapht-0.8.3

 @author David.Gibson

 @version 1.0 - Apr 9, 2012 at 8:59:50 PM

 Documentation: borrowed code from HelloJGraphT.java demo program
 */
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class GraphDemo {

  /**
   Create a simple undirected graph of String nodes and display it

   @param args
   */
  public static void main(String[] args) {

    //SimpleGraph<String, DefaultEdge> stringGraph = createGraph();
    //System.out.println(stringGraph.toString());

    SimpleWeightedGraph<Character, DefaultWeightedEdge> weightedGraph = createWeightedGraph();
    System.out.println(weightedGraph.toString());

    breadthFirstTraversal(weightedGraph, 'A');
    breadthFirstTraversal(weightedGraph, 'D');
    breadthFirstTraversal(weightedGraph, 'Q');
    breadthFirstTraversal(weightedGraph, 'H');

    depthFirstTraversal(weightedGraph, 'A');
    depthFirstTraversal(weightedGraph, 'D');
    depthFirstTraversal(weightedGraph, 'Q');
    depthFirstTraversal(weightedGraph, 'H');
    
    recursiveTraversal(weightedGraph, 'A');
    recursiveTraversal(weightedGraph, 'D');
    recursiveTraversal(weightedGraph, 'Q');
    recursiveTraversal(weightedGraph, 'H');
    
    //Queue<Character> q = new LinkedList<>();
    //Stack s = new Stack();
    //PriorityQueue<DefaultWeightedEdge> pq = new LinkedList<>();

  }

  /**
   Create an undirected graph g with 4 vertices {v1, v2, v3, v4}
   represented as strings and 4 undirected edges {<v1,v2>, <v2,v3>, <v3,
   v4>, <v4,v1>}

   @return graph g
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
   Create a SimpleGraph g with 4 vertices {v1, v2, v3, v4} represented
   as strings and 4 undirected edges {<v1,v2>, <v2,v3>, <v3, v4>,
   <v4,v1>}

   @return graph g
   */
  private static SimpleWeightedGraph<Character, DefaultWeightedEdge> createWeightedGraph() {

    // use a SimpleGraph representation to represent an UndirectedGraph

    SimpleWeightedGraph<Character, DefaultWeightedEdge> g = new SimpleWeightedGraph(DefaultWeightedEdge.class);

    Character v1 = 'A';
    Character v2 = 'B';
    Character v3 = 'C';
    Character v4 = 'D';
    Character v5 = 'E';
    Character v6 = 'F';
    Character v7 = 'G';
    Character v8 = 'H';
    Character v9 = 'Q';

    // add the vertices to graph
    g.addVertex(v1);//a
    g.addVertex(v2);//b
    g.addVertex(v3);//c
    g.addVertex(v4);//d
    g.addVertex(v5);//e
    g.addVertex(v6);//f
    g.addVertex(v7);//g
    g.addVertex(v8);//h
    g.addVertex(v9);//q

    // add edges and wights to graph

    DefaultWeightedEdge edge;

    edge = g.addEdge(v1, v2); // AB-7
    g.setEdgeWeight(edge, 7);

    edge = g.addEdge(v1, v3); // AC-3
    g.setEdgeWeight(edge, 3);

    edge = g.addEdge(v1, v5); // AE-6
    g.setEdgeWeight(edge, 6);

    edge = g.addEdge(v2, v4); // BD-5
    g.setEdgeWeight(edge, 5);

    edge = g.addEdge(v2, v9); // BQ-3
    g.setEdgeWeight(edge, 3);

    edge = g.addEdge(v3, v2); // CB-15
    g.setEdgeWeight(edge, 15);

    edge = g.addEdge(v3, v6); // CF-18
    g.setEdgeWeight(edge, 18);

    edge = g.addEdge(v4, v8); // DH-19
    g.setEdgeWeight(edge, 19);

    edge = g.addEdge(v5, v2); // EB-5
    g.setEdgeWeight(edge, 5);

    edge = g.addEdge(v5, v7); // EG-22
    g.setEdgeWeight(edge, 22);

    edge = g.addEdge(v6, v4); // FD-4
    g.setEdgeWeight(edge, 4);

    edge = g.addEdge(v6, v8); // FH-10
    g.setEdgeWeight(edge, 10);

    edge = g.addEdge(v8, v7); // HG-4
    g.setEdgeWeight(edge, 4);
    // return created graph
    return g;
  }

  private static void breadthFirstTraversal(SimpleWeightedGraph<Character, DefaultWeightedEdge> weightedGraph, char c) {
  }

  private static void depthFirstTraversal(SimpleWeightedGraph<Character, DefaultWeightedEdge> weightedGraph, char c) {
  }

  private static void recursiveTraversal(SimpleWeightedGraph<Character, DefaultWeightedEdge> weightedGraph, char c) {
  }
}
