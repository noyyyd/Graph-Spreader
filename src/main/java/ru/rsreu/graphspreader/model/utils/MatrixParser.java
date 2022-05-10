package ru.rsreu.graphspreader.model.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class MatrixParser {

    public int[][] createMatrix(File file) {
        StringBuilder matrixFromFile = readMatrix(file);

        int amountOfVertex = countAmountOfVertex(matrixFromFile);

        int[][] matrix = new int[amountOfVertex][amountOfVertex];

        int j = 0;
        int k = 0;

        for (int i = 0; i < matrixFromFile.length(); i++) {
            if (k >= amountOfVertex) {
                j++;
                k = 0;
            }

            matrix[j][k] = Character.getNumericValue(matrixFromFile.charAt(i));
            k++;
        }

        return matrix;
    }

    private StringBuilder readMatrix(File file) {
        StringBuilder matrix = new StringBuilder();
        int data;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while ((data = bufferedReader.read()) != -1) {
                if (data == '0' || data == '1') {
                    matrix.append(Character.toString(data));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return matrix;
    }

    private int countAmountOfVertex(StringBuilder matrixFromFile) {
        return (int) Math.sqrt(matrixFromFile.length());
    }
}
