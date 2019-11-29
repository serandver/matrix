package com.project.math.service.impl;

import com.project.math.exception.MatrixMultiplyingException;
import com.project.math.model.Matrix;
import com.project.math.service.MathProcessor;

public class SequentialMathProcessor extends MathProcessor {

    @Override
    public Matrix multiply(Matrix m1, Matrix m2) throws MatrixMultiplyingException {
        validateArgs(m1, m2);

        boolean[][] result = new boolean[m1.getRows()][m2.getColumns()];

        for (int i = 0; i < m1.getRows(); i++) {
            for (int j = 0; j < m2.getColumns(); j++) {
                boolean sum = false;
                for (int k = 0; k < result.length; k++) {
                    sum ^= m1.getValues()[i][k] && m2.getValues()[k][j];
                }
                result[i][j] = sum;
            }
        }

        return new Matrix(m1.getRows(), m1.getColumns(), result);
    }
}
