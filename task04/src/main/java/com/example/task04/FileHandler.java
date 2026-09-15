package com.example.task04;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileHandler implements MessageHandler {

    private final Path file;

    public FileHandler(String fileName) {
        this.file = Path.of(fileName);
    }

    @Override
    public void handle(String message) {
        try {
            Files.writeString(file, message + System.lineSeparator(),
                    java.nio.file.StandardOpenOption.CREATE,
                    java.nio.file.StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}