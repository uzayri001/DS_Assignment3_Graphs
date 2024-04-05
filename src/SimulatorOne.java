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
    public static void main( String [] args ) {
        ArrayList<ArrayList<Integer>> graphArrayList = new ArrayList<>();
        Graph googleMaps = new Graph();
        try (Scanner kb = new Scanner(System.in)) {
            System.out.println("Enter your file name:");
            String fileName = kb.nextLine();
            try {
                FileReader myFile = new FileReader(fileName);
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
                if (i==0) {
                    for (int x=0;x<num;x++) {
                        String name = String.valueOf(x);
                        Vertex newVertex = new Vertex(name);
                        googleMaps.vertexMap.put(name, newVertex);
                    }
                }
                for (int column=0;column<row.size();column+=2) {
                    if (i<=num) {
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
                for (int column=0;column<row.size();column++) {
                    if (i==num+2) {
                        String vertexNum = String.valueOf(graphArrayList.get(i).get(column));
                        Vertex vertex = googleMaps.vertexMap.get(vertexNum);
                        vertex.setName("shop " + vertexNum);
                    }
                    else if (i == graphArrayList.size()-1) {
                        String vertexNum = String.valueOf(graphArrayList.get(i).get(column));
                        Vertex vertex = googleMaps.vertexMap.get(vertexNum);
                        vertex.setName("client " + vertexNum);
                    }
                }
            }
            ArrayList<Integer> lastRow = graphArrayList.get(graphArrayList.size()-1);
            for (int v: lastRow) {
                String startName = "client " +v;
                googleMaps.dijkstra(startName);
                double distance = Double.POSITIVE_INFINITY;
                Vertex closestTaxi = null;
                for (Map.Entry<String, Vertex> entry : googleMaps.vertexMap.entrySet()) {
                    if (entry.getKey().contains("shop")) {
                        if (entry.getValue().dist < distance) {
                            distance = entry.getValue().dist;
                            closestTaxi = entry.getValue();
                        }
                    }
                }
            }
        }
    }
}
