package ru.job4j.concurrent;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimpleBlockingQueueTest {

    SimpleBlockingQueue simpleQueue = new SimpleBlockingQueue(3);

    @Test
    public void whenAllWorksFine() throws InterruptedException {
        simpleQueue.offer(1);
        simpleQueue.offer(2);
        simpleQueue.offer(3);
        Thread producer = new Thread(
                () -> {
                    try {
                        simpleQueue.offer(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        Thread consumer = new Thread(
                () -> {
                    try {
                        simpleQueue.poll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertEquals(simpleQueue.poll(), 3);
    }
}