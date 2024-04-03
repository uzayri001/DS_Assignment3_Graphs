import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
/**
 * Contains the main method to simulate the Graph for a client-taxi service
 * Recieves text input with info regarding clients, taxis and stores to build the graph
 * able to calculate shortest trip from client to store
 * @author uzayri
 * @since 29/03/2024
 * @version 1.0
 */
public class SimulatorOne {
    public static void main( String [] args )
    {
        ArrayList<ArrayList<Integer>> graphArrayList = new ArrayList<>();
        Graph googleMaps = new Graph();
        try {
            FileReader myFile = new FileReader("googlemaps.txt");
            try (BufferedReader myReader = new BufferedReader(myFile)) {
                String line;
                while((line = myReader.readLine()) != null) {
                    String[] tokens = line.split("\\s+");
                    ArrayList<Integer> row = new ArrayList<>();
                    for (String token : tokens) {
                        row.add(Integer.valueOf(token));
                    }
                    graphArrayList.add(row);
                }
            }
        }
        catch (IOException e) {
            System.out.println("File not found.");
        }
        System.out.println(graphArrayList);
        int num = graphArrayList.get(0).get(0);
        for (int i=0;i<graphArrayList.size();i++) {
            List<Integer> row = graphArrayList.get(i);
            for (int column=0;column<row.size();column+=2) {
                if (i==0) {
                    for (int x=0;x<num;x++) {
                        String name = String.valueOf(x);
                        Vertex newVertex = new Vertex(name);                            
                        googleMaps.vertexMap.put(name, newVertex);
                    }
                }
                else if (i!=0 && i<=num) {
                    Vertex currentVertex = googleMaps.vertexMap.get(String.valueOf(graphArrayList.get(i).get(0)));
                    if (column==0) {continue;}
                    else {
                        double weight = graphArrayList.get(i).get(column);
                        int endVertexNum = graphArrayList.get(i).get(column-1);
                        Vertex endVertex = googleMaps.vertexMap.get(String.valueOf(endVertexNum));
                        currentVertex.adj.add(new Edge(endVertex,weight));
                    }
                }
            }
        }
        for (Map.Entry<String, Vertex> entry : googleMaps.vertexMap.entrySet()) {
            String vertexName = entry.getKey();
            Vertex vertex = entry.getValue();
            System.out.println("Vertex Name: " + vertexName + ", Vertex Object: " + vertex);
        }
    }
}
