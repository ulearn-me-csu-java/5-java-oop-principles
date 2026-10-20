package com.example.task04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileHandler implements MessageHandler {
    private final Path filePath;

    public FileHandler(String fileName) {
        this.filePath = Paths.get(fileName);
        try {
            if (filePath.getParent() != null) {
                Files.createDirectories(filePath.getParent());
            }
        } catch (IOException e) {
            throw new RuntimeException("Не удалось создать директорию для файла логов", e);
        }
    }

    @Override
    public void handle(String message) {
        try {
            Files.writeString(filePath, message + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать лог в файл", e);
        }
    }
}