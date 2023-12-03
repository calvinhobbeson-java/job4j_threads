package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T goal;

    public ForkJoinSearch(T[] array, int from, int to, T goal) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.goal = goal;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            linearSearch();
        }
        int mid = (from + to) / 2;
        ForkJoinSearch<T> leftSort = new ForkJoinSearch(array, from, mid, goal);
        ForkJoinSearch<T> rightSort = new ForkJoinSearch(array, mid + 1, to, goal);
        leftSort.fork();
        rightSort.fork();
        int left = leftSort.join();
        int right = rightSort.join();
        return Math.max(left, right);
    }

    private Integer linearSearch() {
        for (int index = from; index < to; index++) {
            if (array[index] == goal) {
                return index;
            }
        }
        return -1;
    }

    public static <T> Integer search(T[] array, T goal) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ForkJoinSearch<T>(array, 0, array.length - 1, goal));
    }
}
