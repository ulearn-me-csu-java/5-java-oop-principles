package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class FileHandler implements MessageHandler {

    private final Path filePath;

    public FileHandler(String filePath){
        this.filePath = Path.of(filePath);
    }

    @Override
    public void handle(Logger.Level level, String formattedMessage){
        try {
            FileWriter writer = new FileWriter(filePath.toFile(), true);
            writer.write(formattedMessage);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
