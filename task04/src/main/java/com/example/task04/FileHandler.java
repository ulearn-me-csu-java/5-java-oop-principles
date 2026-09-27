package com.example.task04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler implements MessageHandler {
    private final Path path;

    public FileHandler(String fileName) {
        this.path = Path.of(fileName);
    }

    @Override
    public void handle(String message) {
        try {
            Files.writeString(
                    path,
                    message + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать в файл", e);
        }
    }
}
