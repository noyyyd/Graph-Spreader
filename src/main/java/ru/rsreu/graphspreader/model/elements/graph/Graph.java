package ru.rsreu.graphspreader.model.elements.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    private List<Vertex> vertexes = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();

    public void setVertexes(List<Vertex> vertexes) {
        this.vertexes = vertexes;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public List<Edge> getEdges() {
        return edges;
    }
}
