package ru.job4j.io;

import java.io.*;

public class ParseFile {
    private final File file;

    public ParseFile(final File file) {
        this.file = file;
    }
    
    public String getContent() throws IOException {
        BufferedInputStream i = new BufferedInputStream(new FileInputStream(file));
        String output = "";
        int data;
        try {
            while ((data = i.read()) > 0) {
                output += (char) data;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContentWithoutUnicode() throws IOException {
        BufferedInputStream i = new BufferedInputStream(new FileInputStream(file));
        String output = "";
        int data;
        try {
        while ((data = i.read()) > 0) {
            if (data < 0x80) {
                output += (char) data;
            }
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }
}