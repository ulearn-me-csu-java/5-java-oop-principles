package com.example.task04.Handlers;
import com.example.task04.MessageHandler;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandler implements MessageHandler {
    private final Path filePath;

    public FileHandler(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public void handle(String formattedMessage) {
        try {
            Files.writeString(filePath, formattedMessage + System.lineSeparator(), 
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Не удалось записать в файл: " + e.getMessage());
        }
    }
}