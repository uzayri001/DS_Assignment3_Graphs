/**
 * Edge class to store edges in a graph
 * Contains start and end vertices
 * edges have weights
 * @author Uzayr Ismail
 * @version 1.0
 * @since 27/03/2024
 */

public class Edge {
    private Vertex destinationVertex;
    private Vertex startVertex;
    private int weight;

    public Edge(Vertex start, Vertex end, int weight) {
        this.destinationVertex = end;
        this.startVertex = start;
        this.weight = weight;
    }

    public Vertex getStarVertex() {
        return this.startVertex;
    }

    public Vertex getDestinationVertex() {
        return this.destinationVertex;
    }

    public int getWeight() {
        return this.weight;
    }
}
