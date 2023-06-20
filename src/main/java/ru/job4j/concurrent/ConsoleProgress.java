package ru.job4j.concurrent;

public class ConsoleProgress {
    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(
                () -> {
                    try {
                        var process = new char[] {'-', '\\', '|', '/'};
                        int index = 0;
                        while (!Thread.currentThread().isInterrupted()) {
                            Thread.sleep(500);
                            System.out.print("\r load: " + process[index++]);
                            if (index == 4) {
                                index = 0;
                            }
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        progress.start();
        Thread.sleep(5000); /* симулируем выполнение параллельной задачи в течение 5 секунд. */
        progress.interrupt();
    }
}
