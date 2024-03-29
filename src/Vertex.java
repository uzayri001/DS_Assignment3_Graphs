/**
 * Vertex class for a graph
 * has a data variable as a String and ArrayList of type Edge
 * @author Uzayr Ismail
 * @since 27/03/2024
 * @version 1.0
 */
import java.util.ArrayList;

public class Vertex {
    private String data;
    private ArrayList<Edge> edges;

    public Vertex(String data) {
        this.data = data;
        this.edges = new ArrayList<>();
    }

    public void addEdge(Vertex endVertex, int weight) {
        this.edges.add(new Edge(this, endVertex, weight));
    }

    public String getData() {return this.data;}

    public ArrayList<Edge> getEdges() {return this.edges;}
}
