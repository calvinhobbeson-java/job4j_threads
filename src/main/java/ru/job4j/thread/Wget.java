package ru.job4j.thread;

import java.io.*;
import java.net.URL;
import java.io.IOException;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1048576];
            int bytesRead;
            int downloadData = 0;
            long start = System.currentTimeMillis();
            long stop = 0;
            while ((bytesRead = in.read(dataBuffer, 0, 1048576)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    long downloadTime = System.currentTimeMillis() - start;
                    if (downloadTime < 1000) {
                        Thread.sleep(1000 - downloadTime);
                    }
                    downloadData = 0;
                    start = System.currentTimeMillis();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        if (args.length != 2) {
            throw new IllegalArgumentException("Parameters added incorrectly");
        }
        wget.start();
        wget.join();
    }
}