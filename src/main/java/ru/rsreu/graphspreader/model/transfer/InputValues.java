package ru.rsreu.graphspreader.model.transfer;

import javafx.scene.control.Label;
import ru.rsreu.graphspreader.model.elements.graph.Graph;

public class InputValues {
    private int iterations;
    private int gravity;
    private int attraction;
    private int repulsive;
    private Graph graph;
    private Label l;

    public Graph getGraph() {
        return graph;
    }

    public void setGraph(Graph graph) {
        this.graph = graph;
    }

    public Label getL() {
        return l;
    }

    public void setL(Label l) {
        this.l = l;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        if (iterations > 0) {
            this.iterations = iterations;
        } else {
            this.iterations = 100;
        }
    }

    public int getGravity() {
        return gravity;
    }

    public void setGravity(int gravity) {
        if (gravity >= 0) {
            this.gravity = gravity;
        } else {
            this.gravity = 10;
        }
    }

    public int getAttraction() {
        return attraction;
    }

    public void setAttraction(int attraction) {
        if (attraction > 0) {
            this.attraction = attraction;
        } else {
            this.attraction = 1;
        }
    }

    public int getRepulsive() {
        return repulsive;
    }

    public void setRepulsive(int repulsive) {
        if (repulsive > 0) {
            this.repulsive = repulsive;
        } else {
            this.repulsive = 1;
        }
    }
}
