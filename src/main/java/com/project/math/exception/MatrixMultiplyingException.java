package com.project.math.exception;

public class MatrixMultiplyingException extends Exception {
    @Override
    public String getMessage() {
        return "Number of columns of the first matrix should be equal to number of rows of the second matrix";
    }
}
