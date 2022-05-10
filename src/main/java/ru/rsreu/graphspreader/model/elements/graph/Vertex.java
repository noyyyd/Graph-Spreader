package ru.rsreu.graphspreader.model.elements.graph;


import ru.rsreu.graphspreader.model.elements.Position;

public class Vertex {
    private final int number;
//    private final int weight;
    private final Position position;
    private Position disposition;
    private boolean gravity;

    public Vertex(int number, Position position) {
        this.number = number;
//        this.weight = weight;
        this.position = position;
    }

    public Vertex(int number, Position position, boolean gravity) {
        this.number = number;
        this.position = position;
        this.gravity = gravity;
    }

    public Position getDisposition() {
        return disposition;
    }

    public void setDisposition(Position disposition) {
        this.disposition = disposition;
    }

    public int getNumber() {
        return number;
    }

//    public int getWeight() {
//        return weight;
//    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(double x, double y) {
        position.setX(x);
        position.setY(y);
    }

    public boolean isGravity() {
        return gravity;
    }

    public void setGravity(boolean gravity) {
        this.gravity = gravity;
    }
}
