package ru.rsreu.graphspreader.model.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import ru.rsreu.graphspreader.model.elements.Position;
import ru.rsreu.graphspreader.model.elements.graph.Edge;
import ru.rsreu.graphspreader.model.elements.graph.Graph;
import ru.rsreu.graphspreader.model.elements.graph.Vertex;

public class GraphCreator {
    MatrixParser matrixParser;

    public GraphCreator() {
        matrixParser = new MatrixParser();
    }

    public Graph createGraphFromFile(File file) {
        Graph graph = new Graph();
        int[][] matrix = matrixParser.createMatrix(file);

        List<Vertex> vertexes = createVertexes(matrix.length);
        List<Edge> edges = createEdges(matrix, vertexes);

        graph.setVertexes(vertexes);
        graph.setEdges(edges);

        return graph;
    }

    private List<Vertex> createVertexes(int amountVertex) {
        List<Vertex> vertices = new ArrayList<>();

        for (int i = 0; i < amountVertex; i++) {
            vertices.add(new Vertex(i, PositionGenerator.getRandomPosition()));
        }
        // Создание силы притяжения
        vertices.add(createGravity(vertices.size()));

        return vertices;
    }

    private Vertex createGravity(int size) {
        return new Vertex(size + 1, new Position(390, 290), true);
    }

    private List<Edge> createEdges(int[][] matrix, List<Vertex> vertexes) {
        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 1 && !isEdgeExist(edges, i, j)) {
                    edges.add(new Edge(
                            1,
                            getVertexByNumber(vertexes, i),
                            getVertexByNumber(vertexes, j)));
                }
            }
        }

        for (Vertex vertex : vertexes) {
            edges.add(new Edge(
                    1,
                    getVertexByNumber(vertexes, vertexes.size()),
                    vertex));
        }

        return edges;
    }

    private boolean isEdgeExist(List<Edge> edges, int i, int j) {
        for (Edge edge : edges) {
            if (edge.getFirstVertex().getNumber() == j
                    && edge.getSecondVertex().getNumber() == i) {
                return true;
            }
        }

        return false;
    }

    private Vertex getVertexByNumber(List<Vertex> vertices, int number) {
        Vertex searchedVertex = null;

        for (Vertex vertex : vertices) {
            if (vertex.getNumber() == number) {
                searchedVertex = vertex;
            }
        }
        return searchedVertex;
    }
}
