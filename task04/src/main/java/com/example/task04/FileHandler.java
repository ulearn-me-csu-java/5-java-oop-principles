package com.example.task04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler implements MessageHandler {
    String fileName;
    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void handle(String message) {
        Path filePath = Path.of(fileName);
        try {
            Files.writeString(filePath, message,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("FileHandler error: " + e.getMessage());
        }
    }

}
