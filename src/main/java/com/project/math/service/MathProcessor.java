package com.project.math.service;

import com.project.math.exception.MatrixMultiplyingException;
import com.project.math.model.Matrix;

public abstract class MathProcessor {

    public abstract Matrix multiply(Matrix m1, Matrix m2) throws MatrixMultiplyingException;

    protected void validateArgs(Matrix m1, Matrix m2) throws MatrixMultiplyingException {
        if (m1.getColumns() != m2.getRows()) {
            throw new MatrixMultiplyingException();
        }
    }
}
