package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger(0);

    public void increment() {
        int temp;
        do {
            temp = get();
        }
        while (!count.compareAndSet(temp, count.get() + 1));
    }

    public int get() {
       return count.get();
    }
}