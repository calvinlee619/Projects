package edu.cmu.cs.cs214.hw2;

import java.util.*;

public class DijkstraAlgorithm {
    private final Graph graph;
    private Set<Vertex> settledNodes;
    private Queue<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessor;
    private Map<Vertex, Integer> distance;
    private int durationTime;   
    
    /** parse the stop-time Hashmap.
     * 
     * @param graph stop-time Hashmap
     * @param M1    the Hashmap used to store the vertexes with the same
     */
  public DijkstraAlgorithm(Graph graph, Map<String, ArrayList<Vertex>> M1) {
    this.graph = graph;
  }
    
    
  /** Calculated the shortest distance between the sub-points and the source
   * 
   * @param source the begin stop
   */
  public void execute(Vertex source, Vertex destination) {
    settledNodes = new HashSet<Vertex>();
    unSettledNodes = new LinkedList<Vertex>();
    distance = new HashMap<Vertex, Integer>();
    predecessor = new HashMap<Vertex, Vertex>();
    
    distance.put(source, 0);
    unSettledNodes.add(source);
    
    int prevlevel = 1;
    int curlevel = 0;
    int layer = 1;
    int firstFindLayer = Integer.MAX_VALUE;
    
    while (unSettledNodes.size() > 0) {
    	
      Vertex node = unSettledNodes.poll();
      // ddddddd
      // if (!node.getRoute().contains("64"))
      // System.out.println(node.getTime() + " Find the destination***************************" + node.getRoute());
      
      // ddddddd
     // if ((node.getName().contains("MURRAY AVE AT BARTLETT ST"))){
    	//  System.out.println(" Find the destination***************************" + node.getRoute());
      //}
      
      if (settledNodes.contains(node)){
          continue;

        } else {
          settledNodes.add(node);
          curlevel++;
        }
      
      curlevel += findMinimalDistances(node, destination);
      
      settledNodes.add(node);

      unSettledNodes.remove(node);
        
      prevlevel --;
      if (prevlevel == 0) {
    	  
        if (node.equals(destination)) {
        	firstFindLayer = layer;
        }
        
        prevlevel = curlevel;
        curlevel = 0;   
        layer++;
       // System.out.println(node.getTime() + " Find the layer *******"+ node.getName()+"%%%%%%%%" + node.getRoute());
      }
      
      if ((layer - firstFindLayer)>5)
    	  return;
    }    
  }
    
  /** Calculated the shortest distance between the neighbors and the node.
   * 
   * @param node node
   */
  private int findMinimalDistances(Vertex node, Vertex destination) {
	  
    int totalNeighbor = 0;
    
    ArrayList<Line> adjacentNodes = graph.getNeighbors(node);
    totalNeighbor += adjacentNodes.size();
    
    for (Line LineTarget : adjacentNodes) {
    
      Vertex target = LineTarget.getLineSymbol();
      
      //******************************************88
     //  if ((node.getName().equals("MURRAY AVE AT BARTLETT ST FS"))){
    	//  System.out.println(" Find the transfer***************************" + target.getRoute());
      // }     
    	  
      if (!(settledNodes.contains(target))){
        unSettledNodes.add(target);
      }
      
      if (getShortestDistance(target) > getShortestDistance(node) 
      + getDistance(node, target)) {
        distance.put(target, getShortestDistance(node) + getDistance(node, target));
        predecessor.put(target, node);
        // System.out.println( " getShortestDistance ****************************" + predecessor.size() + "***********"
        		//);
        
      }

     }
    
    return totalNeighbor;
  }
    
    /** Calculate the distance between two points
     * 
     * @param node the begin point
     * @param target the end point
     * @return the distance
     */
    private int getDistance(Vertex node, Vertex target) {
    return target.getTime() - node.getTime();
    }
    
    /** Get the stored shortest distance of one point from the begin point in map(Distance) 
     * 
     * @param destination vertex
     * @return the shortest distance
     */
    private int getShortestDistance (Vertex destination) {
    Integer d = distance.get(destination);
    
    if (d == null) {
    
    return Integer.MAX_VALUE;
    
    } else {
    
    return d;
    }
    }
    
    
    /** Get the path from the begin-point to the end-point
     * 
     * @param target the end-point
     * @return path
     */
  public LinkedList<Vertex> getPath(Vertex target) {
    LinkedList<Vertex> path = new LinkedList<Vertex>();
    Vertex step = target;
    System.out.println( " path***************************");
    
// check if a path exists
    if (predecessor.get(target) == null) {
      durationTime = Integer.MAX_VALUE;
      System.out.println( " No preocessor *************");
    return null;
    }

    path.add(step);

    while (predecessor.get(step)!=null) {
      step = predecessor.get(step);
      path.add(step);
    }

// put it into correct order
    Collections.reverse(path);

      durationTime = path.get(path.size()-1).getTime() - path.get(0).getTime();

    return path;
    }
        
        
        /** return the shortest distance
     * 
     * @return the shortest distance
     */
    public int getDurationtime() {
    return durationTime;
    }
}

