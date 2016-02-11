/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package socialgraph;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author Angmar
 */
public class TestSocialGraph<V> {
    
    static SocialGraph graph;
    static Scanner userScanner;
    static int choice;
    static boolean quit = false;
    static int person;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner fileScan = new Scanner(System.in);
        userScanner = new Scanner(System.in);
        File myFile = null;
        String path = "";
        boolean fileBool = false;
        try {
            myFile = new File("F:\\Documents\\.\\social_input.txt");
            fileScan = new Scanner(myFile);
        } catch (java.io.FileNotFoundException io) {
            System.out.println(io);
        }
        graph = new SocialGraph(fileScan);
        
        System.out.println("-+:::::: Welcome to the Social Graph Demo :::::+-");
        System.out.println();
        displayMenu();
        
        System.out.println("Vertices: " + graph.getSize());
        for (int i=0;i<graph.getSize();i++) {
            System.out.println("Vertex[" + i +"] " + "= " + graph.getVertex(i));
            
        }
        System.out.println("Neighbors :");
        for (int i=0;i<graph.getSize();i++) {
            System.out.println(graph.getNeighbors(i));
        }
        System.out.println("Degree :");
        for (int i=0;i<graph.getSize();i++) {
            System.out.println(graph.getDegree(i));
        }
        
        graph.printEdges();
        
        System.out.println("DFS: " + graph.dfs(0).toString());
        
        
        while (!quit) {
            System.out.println();
            System.out.println("Please submit your query");
            System.out.print("> ");
            choices();
        }
    }
    
    public static void displayMenu() {
        System.out.println("*****************  Main Menu  *******************");
        System.out.println("*************************************************");
        System.out.println("[1] normalizedDegreeOfCentrality");
        System.out.println("[2] numberOfTrianglesIncidentToVertex");
        System.out.println("[3] listOfTrianglesIncidentToVertex");
        System.out.println("[4] clusterIndividual");
        System.out.println("[5] averageClustering");
        System.out.println("[6] isAcquaintance");
        System.out.println("[7] addVertex");
        System.out.println("[8] addEdge");
        System.out.println("[9] printEdges");
        System.out.println("[0] quit demo");
    }
    
    public static void choices() {
            try {
                choice = userScanner.nextInt();
                if (choice == 1) {
                    System.out.println("[1]");
                    System.out.println();
                    for (int i=0;i<graph.getSize();i++) {
                        float n;
                        n = graph.normalizedDegreeOfCentrality(graph.getVertex(i));
                        System.out.println(n);
                    }
                }
                if (choice == 2) {
                    System.out.println("[2]");
                    chooseVertex();
                    System.out.println(person);
                    int n;
                    n = graph.numberOfTrianglesIncidentToVertex(graph.getVertex(person));
                    System.out.println("Triangles Incident: " + n);
                }
                if (choice == 3) {
                    System.out.println("[3]");
                    chooseVertex();
                    String s;
                    s = graph.listOfTrianglesIncidentToVertex(graph.getVertex(person)).toString();
                    
                    System.out.println(s);
                }
                if (choice == 4) {
                    System.out.println("[4]");
                    
                }
                if (choice == 5) {
                    System.out.println("[5]");
                    
                }
                if (choice == 6) {
                    System.out.println("[6]");
                    boolean acq;
                    chooseVertex();
                    int p1 = person;
                    chooseVertex();
                    int p2 = person;
                    acq = graph.isAcquaintance(graph.getVertex(p1),graph.getVertex(p2) );
                    System.out.println("Are they Acquainted? " +acq);
                }
                if (choice == 7) {
                    System.out.println("[7]");
                    
                }
                if (choice == 8) {
                    System.out.println("[8]");
                    
                }
                if (choice == 9) {
                    System.out.println("[9]");
                    graph.printEdges();
                }
                else if (choice == 0) {
                    System.out.println("Exiting demo.  Goodbye!");
                    quit = true;
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Only options 1-11 are allowed!");
                System.out.println(e);
                userScanner.nextLine();
            }
    }
    
    public static void chooseVertex() {
        System.out.println(">>> Please select for which person you'd like to run the query");
        System.out.println("[0] john");
        System.out.println("[1] peter");
        System.out.println("[2] mary");
        System.out.println("[3] susan");
        System.out.println("[4] george");
        System.out.println("[5] debbie");
        System.out.println("[6] tom");
        System.out.println("[7] bill");
        person=8;
        while ( (person > 7) || (person < 0) ) {
        try {
            System.out.print(">>> ");
            person = userScanner.nextInt();
            if ( (person > 7) || (person < 0) ) {
                System.out.println("Please select a valid choice");
            } else {
               
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Only options 0-7 are allowed!");
        } 
        }
    }
}
