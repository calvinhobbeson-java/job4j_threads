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
        StringBuilder output = new StringBuilder();
        int data;
        try {
            while ((data = i.read()) != -1) {
                if (filter.test(data)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public String getContentWithoutUnicode() throws IOException {
        return content(data -> data < 0x80);
    }

    public String getContent() throws IOException {
        return content(data -> true);
    }
}