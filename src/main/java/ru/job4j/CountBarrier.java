package ru.job4j;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() throws InterruptedException {
        synchronized (monitor) {
            while(count < total) {
                this.await();
                count++;
            }
            monitor.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (monitor) {
            monitor.wait();
        }
    }
}