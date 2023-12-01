package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class ForkJoinSearchTest {
    @Test
    public void whenLinearSearch()  {
        Object[] array = new Integer[] {1, 2, 3, 4, 5, 6, 7, 1, 8, 6};
        int result = ForkJoinSearch.search(array, 8);
        assertEquals(result, 8);
    }
}