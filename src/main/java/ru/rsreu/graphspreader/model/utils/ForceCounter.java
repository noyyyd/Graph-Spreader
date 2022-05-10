package ru.rsreu.graphspreader.model.utils;

public class ForceCounter {
    private static final double HEIGHT = 580;
    private static final double WIDTH = 780;
    private int l;


    public int calcL(int amountVertex) {
        l = (int) Math.sqrt(HEIGHT * WIDTH / amountVertex);
        return l;
    }

    public double calculateRepulsiveness(double remoteness) {
        return Math.pow(l, 2) / remoteness;
    }

    public double calculateAttraction(double remoteness) {
        return Math.pow(remoteness, 2) / l;
    }
}
