package com.project.math.service.impl;

import com.project.math.exception.MatrixMultiplyingException;
import com.project.math.model.Matrix;
import com.project.math.service.MathProcessor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ParallelMathProcessor extends MathProcessor {

    private static int THREAD_NUMBERS = 5;

    @Override
    public Matrix multiply(Matrix m1, Matrix m2) throws MatrixMultiplyingException {

        validateArgs(m1, m2);

        final int rowCount = m1.getRows();
        final int colCount = m2.getColumns();
        boolean[][] result = new boolean[rowCount][colCount];

        if (THREAD_NUMBERS > rowCount) {
            THREAD_NUMBERS = rowCount;
        }

        int count = rowCount / THREAD_NUMBERS;
        int additional = rowCount % THREAD_NUMBERS;

        Thread[] threads = new Thread[THREAD_NUMBERS];
        int start = 0;
        for (int i = 0; i < THREAD_NUMBERS; i++) {
            int cnt = ((i == 0) ? count + additional : count);
            threads[i] = new CalcThread(m1.getValues(), m2.getValues(), result, start, start + cnt - 1);
            start += cnt;
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            log.warn("Interrupted");
        }

        return new Matrix(m1.getRows(), m1.getColumns(), result);
    }

    public static class CalcThread extends Thread {
        private int startRow, endRow;
        private boolean[][] a, b, result;
        private int n;

        public CalcThread(boolean[][] a, boolean[][] b, boolean[][] result, int startRow, int endRow) {
            this.a = a;
            this.b = b;
            this.result = result;
            this.startRow = startRow;
            this.endRow = endRow;
            this.n = b.length;
        }

        @Override
        public void run() {
            for (int row = startRow; row <= endRow ; row++) {
                for (int col = 0; col < result[row].length; col++) {
                    result[row][col] = calcSingleValue(row, col);
                }
            }
        }

        private boolean calcSingleValue(int row, int col) {
            boolean c = a[row][0] & b[0][col];
            for (int i = 1; i < n; i++) {
                c ^= a[row][i] & b[i][col];
            }
            return c;
        }
    }
}
