package ru.job4j.pools;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        int rows = matrix.length;
        Sums[] allSums = new Sums[rows];
        for (int i = 0; i < rows; i++) {
            int rowSum = 0;
            int colSum = 0;
            Sums sums = new Sums();
            for (int j = 0; j < rows; j++) {
                rowSum += matrix[i][j];
                colSum += matrix[j][i];
                sums.setRowSum(rowSum);
                sums.setRowSum(colSum);
                allSums[i] = sums;
            }
        }
        return allSums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        Map<Integer, CompletableFuture<Integer>> futuresRow = new HashMap<>();
        Map<Integer, CompletableFuture<Integer>> futuresCol = new HashMap<>();
        for (int i = 0; i < n; i++) {
            futuresRow.put(i, getRowTask(matrix, i));
            futuresCol.put(i, getColTask(matrix, i));
        }
        for (Integer key : futuresRow.keySet()) {
            sums[key].setRowSum(futuresRow.get(key).get());
            sums[key].setColSum(futuresCol.get(key).get());
        }
        return sums;
    }

    public static CompletableFuture<Integer> getRowTask(int[][] data, int startRow) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            int row = startRow;
            for (int i = 0; i < data.length; i++) {
                sum += data[row][i];
                row++;
            }
            return sum;
        });
    }

    public static CompletableFuture<Integer> getColTask(int[][] data, int startCol) {
        return CompletableFuture.supplyAsync(() -> {
            int sum = 0;
            int col = startCol;
            for (int i = 0; i < data.length; i++) {
                sum += data[i][col];
                col++;
            }
            return sum;
        });
    }
}