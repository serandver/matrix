package com.project.math.model;

import java.util.Random;

public class MatrixFactory {

    public static Matrix createSquareMatrix(int number) {

        boolean[][] values = new boolean[number][number];
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            for (int j = 0; j < number; j++) {
                values[i][j] = random.nextBoolean();
            }
        }
        return new Matrix(number, number, values);
    }
}
