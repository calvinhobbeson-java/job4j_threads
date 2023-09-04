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
            count++;
            monitor.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (monitor) {
            while (count < total) {
                monitor.wait();
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(5);
        Thread master = new Thread(
                () -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " started");
                        for (int i = 1; i <= countBarrier.total; i++) {
                            countBarrier.count();
                            System.out.println(countBarrier.count);
                        }
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                },
                "Master"
        );

        Thread slave = new Thread(
                () -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + " started");
                        countBarrier.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                },
                "Slave"
        );

        master.start();
        slave.start();
    }
}