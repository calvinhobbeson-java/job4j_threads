package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool(int size) {
        for (int i = 0; i < size; i++) {
            threads.add(new Thread(() -> {
                try {
                    tasks.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    shutdown();
                }
            }));
        }
    }


    public void work(Runnable job) throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            tasks.offer(job);
        }
    }

    public void shutdown() {
        Thread.currentThread().interrupt();
    }
}