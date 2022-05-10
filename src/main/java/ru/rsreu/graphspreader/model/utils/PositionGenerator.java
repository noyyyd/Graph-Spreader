package ru.rsreu.graphspreader.model.utils;

import ru.rsreu.graphspreader.model.elements.Position;

public class PositionGenerator {
    private static final int MAX_X = 760;
    private static final int MIN_X = 170;
    private static final int MAX_Y = 560;
    private static final int MIN_Y = 89;

    public static Position getRandomPosition() {
        return new Position(generateRandomX(), generateRandomY());
    }

    private static int generateRandomX() {
        int temp = MAX_X - MIN_X;
        return (int) (Math.random() * ++temp) + MIN_X;
    }

    private static int generateRandomY() {
        int temp = MAX_Y - MIN_Y;
        return (int) (Math.random() * ++temp) + MIN_Y;
    }
}
