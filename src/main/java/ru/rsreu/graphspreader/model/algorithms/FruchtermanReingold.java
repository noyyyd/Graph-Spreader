package ru.rsreu.graphspreader.model.algorithms;

import javafx.scene.control.Label;
import ru.rsreu.graphspreader.model.elements.Vector;
import ru.rsreu.graphspreader.model.elements.graph.Edge;
import ru.rsreu.graphspreader.model.elements.graph.Graph;
import ru.rsreu.graphspreader.model.elements.Position;
import ru.rsreu.graphspreader.model.elements.graph.Vertex;
import ru.rsreu.graphspreader.model.transfer.InputValues;
import ru.rsreu.graphspreader.model.utils.ForceCounter;
import ru.rsreu.graphspreader.model.utils.VectorHandler;

public class FruchtermanReingold implements Algorithm {
    private final ForceCounter forceCounter;
    private final VectorHandler vectorHandler;
    private final int gravity;
    private final int attraction;
    private final int repulsive;
    private final Label l;
    private final Graph graph;
    private int t;

    public FruchtermanReingold(InputValues inputValues) {
        this.forceCounter = new ForceCounter();
        this.vectorHandler = new VectorHandler();

        this.gravity = inputValues.getGravity();
        this.attraction = inputValues.getAttraction();
        this.repulsive = inputValues.getRepulsive();
        this.t = inputValues.getIterations();
        this.l = inputValues.getL();
        this.graph = inputValues.getGraph();
    }

    @Override
    public void start(int count) {
        l.setText("L = " + forceCounter.calcL(graph.getVertexes().size() - 1)); // TODO: 10.05.2022 change position this line

        for (int i = 0; i < count; i++) {
            calculateRepulsivenessDisposition();
            calculateAttractionDisposition();
            moveVertexes();
        }
    }

    private void calculateRepulsivenessDisposition() {
        for (Vertex v : graph.getVertexes()) {
            v.setDisposition(new Position(0, 0));
            Vector disposition = new Vector(v.getPosition().getX(), v.getPosition().getY());

            for (Vertex u : graph.getVertexes()) {
                if (!u.equals(v) && !v.isGravity() && !u.isGravity()) {
                    double remoteness = calculateRemoteness(v.getPosition(), u.getPosition());
                    Position positionRepulsive = calcDirectionRepulsiveness(v, u);

                    Vector originVector = new Vector(
                            disposition.getX() - v.getPosition().getX(),
                            disposition.getY() - v.getPosition().getY());
                    Vector forceVector = new Vector(
                            positionRepulsive.getX() - v.getPosition().getX(),
                            positionRepulsive.getY() - v.getPosition().getY());

                    forceVector.changeLong(forceCounter.calculateRepulsiveness(remoteness) * repulsive, remoteness);

                    disposition = vectorHandler.sumVectors(originVector, forceVector);
                }
            }
            v.setDisposition(new Position(
                    v.getDisposition().getX() + disposition.getX(),
                    v.getDisposition().getY() + disposition.getY()));
        }
    }

    private void calculateAttractionDisposition() {
        for (Edge edge : graph.getEdges()) {
            Vector dispositionV = new Vector(edge.getFirstVertex().getPosition().getX(), edge.getFirstVertex().getPosition().getY());
            Vector dispositionU;

            double remoteness = calculateRemoteness(edge.getFirstVertex().getPosition(), edge.getSecondVertex().getPosition());

            Vector originVector = new Vector(
                    dispositionV.getX() - edge.getFirstVertex().getPosition().getX(),
                    dispositionV.getY() - edge.getFirstVertex().getPosition().getY());
            Vector forceVector = new Vector(
                    edge.getSecondVertex().getPosition().getX() - edge.getFirstVertex().getPosition().getX(),
                    edge.getSecondVertex().getPosition().getY() - edge.getFirstVertex().getPosition().getY());

            if (edge.getFirstVertex().isGravity() || edge.getSecondVertex().isGravity()) {
                forceVector.changeLong(forceCounter.calculateAttraction(remoteness) * gravity, remoteness);
            } else {
                forceVector.changeLong(forceCounter.calculateAttraction(remoteness) * attraction, remoteness);
            }

            dispositionV = vectorHandler.sumVectors(originVector, forceVector);
            dispositionU = vectorHandler.diffVectors(originVector, forceVector);

            edge.getFirstVertex().setDisposition(new Position(
                    edge.getFirstVertex().getDisposition().getX() + dispositionV.getX(),
                    edge.getFirstVertex().getDisposition().getY() + dispositionV.getY()));
            edge.getSecondVertex().setDisposition(new Position(
                    edge.getSecondVertex().getDisposition().getX() + dispositionU.getX(),
                    edge.getSecondVertex().getDisposition().getY() + dispositionU.getY()));
        }
    }

    private void moveVertexes() {
        if (t != 0) {
            for (Vertex vertex : graph.getVertexes()) {
                if (!vertex.isGravity()) {
                    double x = vertex.getPosition().getX() +
                            (vertex.getDisposition().getX() / Math.abs(vertex.getDisposition().getX())) * Math.min(Math.abs(vertex.getDisposition().getX()), t);
                    double y = vertex.getPosition().getY() +
                            (vertex.getDisposition().getY() / Math.abs(vertex.getDisposition().getY())) * Math.min(Math.abs(vertex.getDisposition().getY()), t);

                    if (x > 760) {
                        x = 760;
                    }
                    if (x < 20) {
                        x = 20;
                    }
                    if (y > 555) {
                        y = 555;
                    }
                    if (y < 25) {
                        y = 25;
                    }
                    vertex.setPosition(x, y);
                }
            }
            t--;
        }
    }

    private double calculateRemoteness(Position position, Position
            position1) {
        double x = position.getX();
        double x1 = position1.getX();
        double y = position.getY();
        double y1 = position1.getY();


        double remoteness = Math.sqrt((Math.abs(x - x1) * Math.abs(x - x1)) +
                (Math.abs(y - y1) * Math.abs(y - y1)));

        if (remoteness == 0) {
            remoteness = 10;
        }

        return remoteness;
    }

    private Position calcDirectionRepulsiveness(Vertex v, Vertex u) {
        double x1 = v.getPosition().getX();
        double y1 = v.getPosition().getY();
        double x2 = u.getPosition().getX();
        double y2 = u.getPosition().getY();

        return new Position(x1 + (x1 - x2), y1 + (y1 - y2));
    }
}
