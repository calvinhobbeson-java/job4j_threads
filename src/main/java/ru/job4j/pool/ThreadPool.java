package ru.job4j.pool;

import ru.job4j.concurrent.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private static final int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool(int size) {
        for (int i = 0; i < size; i++) {
            while (!Thread.currentThread().isInterrupted()) {
                threads.add(new Thread(() -> {
                    try {
                        tasks.poll();
                        Thread.currentThread().start();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }));
            }
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (int i = 0; i < size; i++) {
            threads.get(i).interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool(size);
        for (int i = 0; i < size; i++) {
            pool.work(pool.threads.get(i));
        }
        pool.wait(5000);
        pool.shutdown();
    }
}