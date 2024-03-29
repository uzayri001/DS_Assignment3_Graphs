import java.util.ArrayList;
/**
 *
 * @author uzayri
 */
public class Graph {
    private ArrayList<Vertex> vertices;
    private boolean isWeighted;
    private boolean isDirected;

    public Graph(boolean isWeighted, boolean isDirected) {
        this.vertices = new ArrayList<>();
        this.isWeighted = isWeighted;
        this.isDirected = isDirected;
    }

    public void addVertex(String data) {
        Vertex newVertex = new Vertex(data);
        this.vertices.add(newVertex);
    }

    public void addEdge(Vertex start, Vertex end, Integer weight) {
        if (!this.isWeighted) {
            weight = 0;
        }
        else {
            start.addEdge(end, weight);
        }
        if (!this.isDirected) {
            end.addEdge(start,weight);
        }
    }

    public ArrayList<Vertex> getVertices() {
        return this.vertices;
    }

    public Vertex searchVertex(String data) {
        for (Vertex v:this.vertices) {
            if (v.getData().equalsIgnoreCase(data)) {
                return v;
            }
        }
        return null;
    }
}
