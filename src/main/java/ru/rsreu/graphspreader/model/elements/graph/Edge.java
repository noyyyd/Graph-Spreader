package ru.rsreu.graphspreader.model.elements.graph;

public class Edge {
    private int weight;
    private Vertex firstVertex;
    private Vertex secondVertex;


    public Edge(int weight, Vertex firstVertex, Vertex secondVertex) {
        this.weight = weight;
        this.firstVertex = firstVertex;
        this.secondVertex = secondVertex;
    }

    public int getWeight() {
        return weight;
    }

    public Vertex getFirstVertex() {
        return firstVertex;
    }

    public Vertex getSecondVertex() {
        return secondVertex;
    }

}
