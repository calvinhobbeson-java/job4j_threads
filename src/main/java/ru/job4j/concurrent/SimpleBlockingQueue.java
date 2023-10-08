package ru.job4j.concurrent;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private final int limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public void offer(T value) throws InterruptedException {
        while (queue.size() == limit) {
            wait();
        }
        queue.add(value);
        notifyAll();
    }

    public T poll() throws InterruptedException {
        while (queue.size() == 0) {
            wait();
        }
        notifyAll();
        return queue.poll();
    }
}