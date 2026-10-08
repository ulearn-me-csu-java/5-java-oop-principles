package com.example.task04;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;


public class FileHandler implements MessageHandler, AutoCloseable {
    private final Path file;
    private BufferedWriter writer;

    public FileHandler(Path file) throws IOException {
        this.file = file;
        Path parent = file.getParent();
        if (parent != null) {
            Files.createDirectories(file.getParent());
        }
        this.writer = Files.newBufferedWriter(file, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    @Override
    public void handle(String message) {
        if (writer == null) return;
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.err.println("Ошибка записи: " + e.getMessage());
        }
    }

    @Override
    public void close() throws IOException {
        if (writer != null) {
            writer.close();
        }
        writer = null;
    }

    protected void open(Path path) {
        try {
            writer = Files.newBufferedWriter(path, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Не удалось открыть " + path + ": " + e.getMessage());
        }
    }
}
