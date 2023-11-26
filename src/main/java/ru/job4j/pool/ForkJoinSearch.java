package ru.job4j.pool;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSearch extends RecursiveTask {

    private final int[] array;
    private final int from;
    private final int to;
    private final int goal;

    public ForkJoinSearch(int[] array, int from, int to, int goal) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.goal = goal;
    }

    @Override
    protected Object compute() {
        int[] result = null;
        if (from == to || from == goal || to == goal) {
            result = new int[]{array[goal]};
        }
        if (array.length > 10) {
            int mid = (from + to) / 2;
            ForkJoinSearch leftSort = new ForkJoinSearch(array, from, mid, goal);
            ForkJoinSearch rightSort = new ForkJoinSearch(array, mid + 1, to, goal);
            leftSort.fork();
            rightSort.fork();
        }
        for (int i : array) {
            if (i == goal) {
                result = new int[]{array[goal]};
            }
        }
        return result;
    }
}
