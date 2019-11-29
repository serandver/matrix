package com.project.math;

import com.project.math.exception.MatrixMultiplyingException;
import com.project.math.model.Matrix;
import com.project.math.model.MatrixFactory;
import com.project.math.service.MathProcessor;
import com.project.math.service.impl.ParallelMathProcessor;
import com.project.math.service.impl.SequentialMathProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    private final static int TEST_INITIAL_CONSTANT = 10;

    public static void main(String[] args) {

        Matrix squareMatrix1 = MatrixFactory.createSquareMatrix(TEST_INITIAL_CONSTANT);
        log.info("first arg: \n" + squareMatrix1);

        Matrix squareMatrix2 = MatrixFactory.createSquareMatrix(TEST_INITIAL_CONSTANT);
        log.info("second arg: \n" + squareMatrix2);

        sequentialMethod(squareMatrix1, squareMatrix2);
        parallelMethod(squareMatrix1, squareMatrix2);
    }

    private static void sequentialMethod(Matrix squareMatrix1, Matrix squareMatrix2) {
        long startTime = System.nanoTime();
        MathProcessor sequentialProcessor = new SequentialMathProcessor();
        try {
            Matrix multiply = sequentialProcessor.multiply(squareMatrix1, squareMatrix2);
            log.info("result of sequential multiplying matrix: \n" + multiply);
        } catch (MatrixMultiplyingException e) {
            log.warn(e.getMessage());
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        log.info("sequential multiplying time: " + duration);
    }

    private static void parallelMethod(Matrix squareMatrix1, Matrix squareMatrix2) {
        long startTime = System.nanoTime();
        MathProcessor parallelMathProcessor = new ParallelMathProcessor();
        try {
            Matrix multiply = parallelMathProcessor.multiply(squareMatrix1, squareMatrix2);
            log.info("parallel multiplying matrix: \n" + multiply);
        } catch (MatrixMultiplyingException e) {
            log.warn(e.getMessage());
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        log.info("parallel multiplying time: " + duration);
    }
}
