package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;

import static org.junit.jupiter.api.Assertions.*;

class ForkJoinSearchTest {
    @Test
    public void whenLinearSearch()  {
        Object[] array = {1, 2, 3, 4, 5, 6, 7, 1, 8};
        int result = ForkJoinSearch.search(array, 8);
        assertEquals(result, 8);
    }

    @Test
    public void whenParallelSearch() {
        Object[] array = {1, "phrase", 2, 6, 5, 2, 4, 5, 6, 7, 4, 6, 18};
        int result = ForkJoinSearch.search(array, 18);
        assertEquals(result, 18);
    }

    @Test
    public void whenDifferentTypes() {
        Object[] array = {1, "phrase", 2, 6, 5, 2, 4, 5, 6, 7, 4, 6, 18};
        int result = ForkJoinSearch.search(array, "phrase");
        assertEquals(result, "phrase");
    }

    @Test
    public void whenItemNotFound() {
        Object[] array = {1, "phrase", 2, 6, 5, 2, 4, 5, 6, 7, 4, 6, 18};
        int result = ForkJoinSearch.search(array, "phrases");
        assertEquals(result, -1);
    }
}