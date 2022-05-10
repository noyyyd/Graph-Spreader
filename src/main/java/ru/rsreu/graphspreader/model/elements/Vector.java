package ru.rsreu.graphspreader.model.elements;

public class Vector {
    private double x;
    private double y;

    public Vector(double start, double end) {
        this.x = start;
        this.y = end;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void changeLong(double force, double remoteness) {
        x = (x / (remoteness / force));
        y = (y / (remoteness / force));
    }
}
