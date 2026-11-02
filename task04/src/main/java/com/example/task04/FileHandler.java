package com.example.task04;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler implements MessageHandler {
    private final Path file;

    public FileHandler(Path file) {
        this.file = file;
    }

    public FileHandler(String fileName) {
        this(Path.of(fileName));
    }

    @Override
    public synchronized void handle(String message) {
        write(file, message);
    }

    static void write(Path file, String message) {
        try {
            Path parent = file.toAbsolutePath().getParent();
            if (parent != null) {
                Files.createDirectories(parent);
            }
            Files.writeString(file, message + System.lineSeparator(), StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException("Не удалось записать в файл " + file, e);
        }
    }
}