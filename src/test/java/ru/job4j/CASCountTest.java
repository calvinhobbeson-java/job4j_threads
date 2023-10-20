package ru.job4j;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CASCountTest {
    @Test
    public void whenIncrementThenTwo() throws InterruptedException {
        CASCount count = new CASCount();
        Thread first = new Thread(count::increment);
        Thread second = new Thread(count::increment);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(count.get()).isEqualTo(2);
    }
}