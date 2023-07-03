package ru.job4j.thread;

import java.io.*;
import java.net.URL;
import java.sql.Timestamp;

public class FileDownload {
    public static void main(String[] args) throws Exception {
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("pom_tmp.xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                //Thread.sleep(1000);
            }
            long stop = System.currentTimeMillis();
            System.out.println(stop - start);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}