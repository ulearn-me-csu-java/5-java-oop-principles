package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;

public class FileHandler implements MessageHandler{
    private final PrintWriter writer;

    public FileHandler(String path) {
        try {
            this.writer = new PrintWriter(new FileWriter(path, true), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void handle(String message) {
        writer.println(message);

    }

    @Override
    public void close() {
        writer.close();
    }
}
