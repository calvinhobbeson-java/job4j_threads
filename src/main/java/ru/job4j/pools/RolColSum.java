package ru.job4j.pools;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        Sums[] allSums = new Sums[rows];
        int sum = 0;
        Sums sums = new Sums();
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < columns; j++) {
                    sum += matrix[i][j];
                    sums.setRowSum(sum);
                    allSums[i] = sums;
                }
            }
            for (int i = 0; i < columns; i++) {
                for (int j = 0; j < rows; j++) {
                    sum += matrix[i][j];
                    sums.setColSum(sum);
                    allSums[i] = sums;
                }
            }
        return allSums;
        }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int rows = matrix.length;
        int columns = matrix[0].length;
        Sums[] allSums = new Sums[rows];
        int sum = 0;
        Sums sums = new Sums();
    }
        public static CompletableFuture<Integer> getTask(int[][] data) {
            return CompletableFuture.supplyAsync(() -> {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        sum += matrix[i][j];
                        sums.setRowSum(sum);
                        allSums[i] = sums;
                    }
                }
            }
    }

}