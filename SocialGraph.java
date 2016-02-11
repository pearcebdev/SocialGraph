/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialgraph;

import java.util.*;

/**
 *
 * @author Angmar
 */
public class SocialGraph<V> extends Graph {
    
    protected SocialGraph() {
        super();
    }
    
    protected SocialGraph(int[][] edges, V[] vertices) {
        super(edges, vertices);
        
    }
    
    public SocialGraph(Scanner scanner) {
        super(scanner);
    }
    
    public float normalizedDegreeOfCentrality(V v) {
        float nDC;
        float degree = (float) getDegree(getIndex(v));
        float n = (float) getSize();
        nDC = degree/(n-1);
        return nDC;
    }
    public int numberOfTrianglesIncidentToVertex(V v) {
        int numTriangles = numTriangles(v);
        return numTriangles;
    }
    
    public List<String> listOfTrianglesIncidentToVertex(V v) {
        List<String> triangleList = new ArrayList<String>();
        triangleList = triangleHelper(v);
        
        return triangleList;
    }
    /*public float clusterIndividual(V v) {
        float cluster;
        
    }*/
    public boolean isAcquaintance(V v, V z) {
        boolean acq = acqHelper(v,z);
        return acq;
    }
    
}