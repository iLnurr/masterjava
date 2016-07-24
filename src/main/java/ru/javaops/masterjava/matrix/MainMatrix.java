package ru.javaops.masterjava.matrix;

import java.util.concurrent.ExecutionException;

/**
 * gkislin
 * 03.07.2016
 */
public class MainMatrix {
    // Multiplex matrix
    private static final int MATRIX_SIZE = 1000;
    private static final int THREAD_NUMBER = 10;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        final int[][] matrixA = new int[MATRIX_SIZE][MATRIX_SIZE];
        final int[][] matrixB = new int[MATRIX_SIZE][MATRIX_SIZE];

        long start = System.currentTimeMillis();
        final int[][] matrixC =  MatrixUtil.singleThreadMultiply(matrixA, matrixB);
        System.out.println("Single thread multiplication time, sec: " + (System.currentTimeMillis() - start)/1000.);

        // TODO implement parallel multiplication matrixA*matrixB
        // TODO compare wih matrixC;
        start = System.currentTimeMillis();
        final int[][] matrixC2 =  MatrixUtil.matrixMultiplyUsingThreadPoolExecutor_Callable(matrixA, matrixB,THREAD_NUMBER);
        System.out.println("Thread pool executor using Callable, multiplication time, sec: " + (System.currentTimeMillis() - start)/1000.);

        start = System.currentTimeMillis();
        final int[][] matrixC3 =  MatrixUtil.matrixMultiplyUsingThreadPoolExecutor_Runnable(matrixA, matrixB,THREAD_NUMBER);
        System.out.println("Thread pool executor using Runnable, multiplication time, sec: " + (System.currentTimeMillis() - start)/1000.);
    }
}
