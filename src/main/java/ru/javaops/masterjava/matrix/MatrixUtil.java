package ru.javaops.masterjava.matrix;

import java.util.concurrent.*;

/**
 * gkislin
 * 03.07.2016
 */
public class MatrixUtil {

    public static int[][] singleThreadMultiply(int[][] matrixA, int[][] matrixB) {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                int sum = 0;
                for (int k = 0; k < matrixSize; k++) {
                    sum += matrixA[i][k] * matrixB[k][j];
                }
                matrixC[i][j] = sum;
            }
        }
        return matrixC;
    }

    public static int[][] matrixMultiplyUsingThreadPoolExecutor_Callable(int[][] matrixA, int[][] matrixB, int THREAD_NUMBER) throws ExecutionException, InterruptedException {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBER);
        Future<Integer> task;

        for (int i = 0; i < matrixSize; i++) {
            int finalI = i;
            task = executorService.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    for (int j = 0; j < matrixSize; j++) {
                        int sum = 0;
                        for (int k = 0; k < matrixSize; k++) {
                            sum += matrixA[finalI][k] * matrixB[k][j];
                        }
                        matrixC[finalI][j] = sum;
                    }
                    return 1;
                }
            });
        }
        executorService.shutdown();
        return matrixC;
    }

    public static int[][] matrixMultiplyUsingThreadPoolExecutor_Runnable(int[][] matrixA, int[][] matrixB, int THREAD_NUMBER) throws ExecutionException, InterruptedException {
        final int matrixSize = matrixA.length;
        final int[][] matrixC = new int[matrixSize][matrixSize];

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_NUMBER);

        for (int i = 0; i < matrixSize; i++) {
            int finalI = i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < matrixSize; j++) {
                        int sum = 0;
                        for (int k = 0; k < matrixSize; k++) {
                            sum += matrixA[finalI][k] * matrixB[k][j];
                        }
                        matrixC[finalI][j] = sum;
                    }
                }
            });
        }
        executorService.shutdown();
        return matrixC;
    }
}
