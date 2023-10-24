package ru.job4j.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CacheTest {
    @Test
    void whenUpdateThenUpdateCorrectly() {
        Base base = new Base(1, 0);
        Cache cache = new Cache();
        base.setName("base1");
        cache.add(base);
        base.setName("base2");
        cache.update(base);
        assertEquals(base.getName(), "base2");
    }

    @Test
    void whenDeleteThenDeleteCorrectly() {
        Base base = new Base(1, 0);
        Cache cache = new Cache();
        cache.add(base);
        cache.delete(base);
        assertNull(cache.getId(base.getId()));
    }
}