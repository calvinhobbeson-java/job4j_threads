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
        T result = null;
        if ((to - from) <= 10) {
            linearSearch();
        }
        if (array.length > 10) {
            int mid = (from + to) / 2;
            ForkJoinSearch leftSort = new ForkJoinSearch(array, from, mid, goal);
            ForkJoinSearch rightSort = new ForkJoinSearch(array, mid + 1, to, goal);
            leftSort.fork();
            rightSort.fork();
        }
        for (T i : array) {
            if (i == goal) {
                result = i;
            }
        }
        return (Integer) result;
    }

    private Integer linearSearch() {
        T result = null;
        for (T i : array) {
            if (i == goal) {
                result = i;
            }
        }
        return (Integer) result;
    }

    public static <T> Integer search(T[] array, T goal) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ForkJoinSearch<T>(array, 0, array.length - 1, goal));
    }
}
