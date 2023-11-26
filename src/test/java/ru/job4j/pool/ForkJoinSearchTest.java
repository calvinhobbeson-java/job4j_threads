package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class ForkJoinSearchTest {
    @Test
    public void whenLinearSearch()  {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinSearch search = new ForkJoinSearch(new int[] {1, 2, 3, 4, 5, 6, 7, 8},1, 8, 6);
        int result = (int) pool.invoke(search);
        assertEquals(goal, result);
    }
}