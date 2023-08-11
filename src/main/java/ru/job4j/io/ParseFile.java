package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(final File file) {
        this.file = file;
    }

    public String content(Predicate<Integer> filter) throws IOException {
        BufferedInputStream i = new BufferedInputStream(new FileInputStream(file));
        String output = "";
        int data;
        try {
            while ((data = i.read()) > 0) {
                if (filter.test(data)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public String getContentWithoutUnicode() throws IOException {
        return content(data -> data < 0x80);
    }

    public String getContent() throws IOException {
        return content(data -> true);
    }
}