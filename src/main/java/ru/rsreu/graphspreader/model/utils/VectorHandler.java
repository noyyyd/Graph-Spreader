package ru.rsreu.graphspreader.model.utils;

import ru.rsreu.graphspreader.model.elements.Vector;

public class VectorHandler {

    public Vector sumVectors(Vector v, Vector v1) {
        return new Vector(
                v.getX() + v1.getX(),
                v.getY() + v1.getY());
    }

    public Vector diffVectors(Vector v, Vector v1) {
        return new Vector(
                v.getX() - v1.getX(),
                v.getY() - v1.getY());
    }
}
