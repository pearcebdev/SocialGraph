// UMUC CMSC 350 
// Class Graph - Defines an undirected graph
// Adapted by Ioan from:
// Liang - Introduction to Java Programming, 9th Edition
// Source code of the examples available at: 
// http://www.cs.armstrong.edu/liang/intro9e/examplesource.html

package socialgraph;

import java.util.*;

public class Graph<V> {
  protected List<V> vertices = new ArrayList<V>(); // Store vertices
  protected List<List<Integer>> neighbors = new ArrayList<List<Integer>>(); // Adjacency lists

  /** Construct an empty graph */
  protected Graph() {
  }
  
  /** Construct a graph from edges and vertices stored in arrays */
  protected Graph(int[][] edges, V[] vertices) {
    for (int i = 0; i < vertices.length; i++)
      this.vertices.add(vertices[i]);
    
    createAdjacencyLists(edges, vertices.length);
  }

  /** Construct a graph from edges and vertices stored in List */
  protected Graph(List<Edge> edges, List<V> vertices) {
  for (int i = 0; i < vertices.size(); i++)
    this.vertices.add(vertices.get(i));
      
  createAdjacencyLists(edges, vertices.size());
  }

  /** Construct a graph for integer vertices 0, 1, 2 and edge list */
  protected Graph(List<Edge> edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++) 
      vertices.add((V)(new Integer(i))); // vertices is {0, 1, ...}
    
    createAdjacencyLists(edges, numberOfVertices);
  }

  /** Construct a graph from integer vertices 0, 1, and edge array */
  protected Graph(int[][] edges, int numberOfVertices) {
    for (int i = 0; i < numberOfVertices; i++) 
      vertices.add((V)(new Integer(i))); // vertices is {0, 1, ...}
    
    createAdjacencyLists(edges, numberOfVertices);
  }
  
  public Graph(Scanner scanner) {
      // Constructor for scanning data and reading information to construct graph
      int MAXE=20;
      int MAXV;
      int[][] e = new int[MAXE][2];
      V[] v;
      //int numEdges=0;
      String s = scanner.next();
      System.out.println(s);
      String[] sReg = s.split("[^a-z]");//regex delimit to match names
      MAXV = sReg.length;
      v = (V[]) new Object[MAXV];
      int position = 0;
      for (int i = 0;i<sReg.length;i++) {
            v[i] = (V) sReg[i];
            System.out.println("v[" + i + "] = " + sReg[i]);
            addVertex(v[i]);//add names as vertices
      }
      while (scanner.hasNext()) {
          String str = scanner.next();
          
          if ("#".equals(str)) { //start reading data after # seperator
              System.out.println(str);
              
              while (scanner.hasNext()) {
                  System.out.println();
                  System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                  scanner.useDelimiter("\n");
                  scanner.nextLine();
                  String a = scanner.next();
                  a = a.trim();
                  System.out.println(">>> " + a);
                  String[] aReg = a.split("\\s*,\\s*");
                  int[] arr = new int[aReg.length];
                  System.out.println("Length: " + aReg.length);
                  
                  System.out.println("-- ARRAY --");
                  for (int i=0;i<aReg.length;i++) {
                      arr[i] = Integer.parseInt(aReg[i].trim());
                      System.out.print(arr[i] + " ");
                  }
                  System.out.println();
                  System.out.println("-----------");
                  System.out.println("*** "+vertices.get(arr[0])+" ***");
                  System.out.println();
                  /*for (int i = 0;i<arr.length-1;i++) {
                      numEdges++;
                  }
                  MAXE = numEdges;
                  System.out.println("MAXE = " + MAXE);*/
                  
                  for (int i = 1;i<arr.length;i++) {
                      e[position][0] = arr[0];
                      System.out.println("arr[" + i +"]" + "= " + arr[i]);
                      
                      e[position][1] = arr[i];
                      //System.out.println("arr[" + (i+1) +"]" + "= " + arr[i+1]);
                      //System.out.println("e : " + e[position][0] +", " + e[position][1]);
                      
                      position++;
                      int count = arr[0];
                      boolean toAdd = true;
                      System.out.println("Count: "+count);
                      if (count>0) { //only perform after john
                          // verifying the egdge to possibly call addEdge()
                          System.out.println("Verifying edge["+arr[0]+","+arr[i]+"]");
                          //int checkCount = count-1;
                          toAdd=true; //resets true
                          if (arr[i] < count) { //check previous persons edges
                            for (int z=0;z<count;z++) {
                              //List<Integer> check = neighbors.get(z);
                              int nSize = neighbors.get(z).size();
                              String nb = neighbors.get(z).toString();
                              System.out.println("Neighbors of "+vertices.get(z)+"("+z+") : "+nb);
                              for (int j =0;j<nSize;j++) { //loop thru neighbors of (z)
                                  int checkInt = neighbors.get(z).get(j);
                                  System.out.println("Check: " +checkInt);
                                  if (checkInt == count) {
                                      //if they are already neighbors, then the edge exists
                                      toAdd = false;
                                      System.out.println("Edge already exists!");
                                  }
                              }
                            }
                          }
                      }
                      System.out.println("toAdd:" +toAdd);
                      if (toAdd) {
                          addEdge(arr[0], arr[i]); //if toAdd was never falsified, addEdge
                          System.out.println("Adding edge : " + arr[0]+","+arr[i]);
                      }
                  }
                  
              }
          }
      }
      Graph g = new Graph(e,v);
      for (int i=0;i<MAXE;i++) {
          for (int j=0;j<2;j++) {
              System.out.print(e[i][j] + " ");
          }
          System.out.println( "" );
      }
      System.out.println();
  }
  
  /** Create adjacency lists for each vertex */
  private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
    // Create a linked list
    for (int i = 0; i < numberOfVertices; i++) {
      neighbors.add(new ArrayList<Integer>());
    }

    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0];
      int v = edges[i][1];
      neighbors.get(u).add(v);
    }
  }

  /** Create adjacency lists for each vertex */
  private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
    // Create a linked list for each vertex
    for (int i = 0; i < numberOfVertices; i++) {
      neighbors.add(new ArrayList<Integer>());
    }

    for (Edge edge: edges) {
      neighbors.get(edge.u).add(edge.v);
    }
  }

  /** Return the number of vertices in the graph */
  public int getSize() {
    return vertices.size();
  }

  /** Return the vertices in the graph */
  public List<V> getVertices() {
    return vertices;
  }

  /** Return the object for the specified vertex */
  public V getVertex(int index) {
    return vertices.get(index);
  }

  /** Return the index for the specified vertex object */
  public int getIndex(V v) {
    return vertices.indexOf(v);
  }

  /** Return the neighbors of the specified vertex */
  public List<Integer> getNeighbors(int index) {
    return neighbors.get(index);
  }

  /** Return the degree for a specified vertex */
  public int getDegree(int v) {
    return neighbors.get(v).size();
  }

  /** Print the edges */
  public void printEdges() {
    for (int u = 0; u < neighbors.size(); u++) {
      System.out.print(getVertex(u) + " (" + u + "): ");
      for (int j = 0; j < neighbors.get(u).size(); j++) {
        System.out.print("(" + u + ", " +
          neighbors.get(u).get(j) + ") ");
      }
      System.out.println();
    }
  }

  /** Clear graph */
  public void clear() {
    vertices.clear();
    neighbors.clear();
  }
  
  /** Add a vertex to the graph */  
  public void addVertex(V vertex) {
    vertices.add(vertex);
    neighbors.add(new ArrayList<Integer>());
  }

  /** Add an edge to the graph */  
  public void addEdge(int u, int v) {
    neighbors.get(u).add(v);
    neighbors.get(v).add(u);
  }
   /*public boolean hasEdge(int i, int j) {
       return neighbors;
   }*/
  
  /** DFS Graph Traversal */
  /** Obtain a DFS List of vertices starting from vertex v */
  /** Original code modified to return List (instead of Tree)*/
  public List<Integer> dfs(int v) {
    List<Integer> searchOrder = new ArrayList<Integer>();
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1; // Initialize parent[i] to -1

    // Mark visited vertices
    boolean[] isVisited = new boolean[vertices.size()];

    // Recursively search
    dfs(v, parent, searchOrder, isVisited);

    // Return a search tree
    return searchOrder;
  }
  public int numTriangles(V v) throws IndexOutOfBoundsException {
      int vx = vertices.indexOf(v);
      int n2; 
      int n1;
      int numTriangles =0;
      for (int i=0;i<neighbors.get(vx).size()-1;i++) {
          n2 = neighbors.get(vx).get(i+1);
          n1 = neighbors.get(vx).get(i);
          int nb = n2-n1;
          System.out.println("n2 -n1:" + nb);
          if (nb == 1 ) {
              for (int j=0;j<neighbors.get(i).size();j++) {
                  int n3 = neighbors.get(i).get(j);
                  if (n3 == n2) {
                      numTriangles++;
                  }
              }
          }
      }
      return numTriangles;
  }
  public List<String> triangleHelper(V v) {
      List<String> triangleList = new ArrayList<String>();
      int vx = vertices.indexOf(v);
      int n2;
      int n1;
      for (int i=0;i<neighbors.get(vx).size()-1;i++) {
          n2 = neighbors.get(vx).get(i+1);
          n1 = neighbors.get(vx).get(i);
          int nb = n2-n1;
          if (nb == 1 ) {
              for (int j=0;j<neighbors.get(i).size();j++) {
                  int n3 = neighbors.get(i).get(j);
                  if (n3 == n2) {
                      int[] arr ={vx, n1, n2};
                      String triV = arr.toString();
                      triangleList.add(triV);
                  }
              }
          }
      }
      return triangleList;
  }
  public boolean acqHelper(V v, V z) {
      boolean acq = false;
      int vx = getIndex(v);
      int vz = getIndex(z);
      for (int i=0;i<neighbors.get(vx).size();i++) {
          int nb = neighbors.get(vx).get(i);
          if (nb == vz)
              acq=true;
      }
      return acq;
  }

  /** Recursive method for DFS search */
  private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
    // Store the visited vertex
    searchOrder.add(v);
    isVisited[v] = true; // Vertex v visited

    for (int i : neighbors.get(v)) {
      if (!isVisited[i]) {
        parent[i] = v; // The parent of vertex i is v
        dfs(i, parent, searchOrder, isVisited); // Recursive search
      }
    }
  }
  
  /** BFS Graph Traversal */
  /** Obtain a BFS List of vertices starting from vertex v */
  /** Original code modified to return List (instead of Tree)*/
  public List<Integer> bfs(int v) {
    List<Integer> searchOrder = new ArrayList<Integer>();
    int[] parent = new int[vertices.size()];
    for (int i = 0; i < parent.length; i++)
      parent[i] = -1; // Initialize parent[i] to -1

    java.util.LinkedList<Integer> queue =
      new java.util.LinkedList<Integer>(); // list used as a queue
    boolean[] isVisited = new boolean[vertices.size()];
    queue.offer(v); // Enqueue v
    isVisited[v] = true; // Mark it visited

    while (!queue.isEmpty()) {
      int u = queue.poll(); // Dequeue to u
      searchOrder.add(u); // u searched
      for (int w : neighbors.get(u)) {
        if (!isVisited[w]) {
          queue.offer(w); // Enqueue w
          parent[w] = u; // The parent of w is u
          isVisited[w] = true; // Mark it visited
        }
      }
    }
    return searchOrder;
  }
 
  /** Edge inner class inside the Graph class */
  public static class Edge {
    public int u; // Starting vertex of the edge
    public int v; // Ending vertex of the edge

    /** Construct an edge for (u, v) */
    public Edge(int u, int v) {
      this.u = u;
      this.v = v;
    }
  }
}