package com.example.task04.Handlers;
import com.example.task04.MessageHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String baseFileName;
    private final ChronoUnit rotationUnit;
    private final String extension;

    public RotationFileHandler(String baseFileName, String extension, ChronoUnit rotationUnit) {
        this.baseFileName = baseFileName;
        this.extension = extension;
        this.rotationUnit = rotationUnit;
    }

    @Override
    public void handle(String formattedMessage) {
        String datePattern = switch (rotationUnit) {
            case HOURS -> "yyyy-MM-dd_HH";
            case MINUTES -> "yyyy-MM-dd_HH-mm";
            case DAYS -> "yyyy-MM-dd";
            default -> "yyyy-MM-dd_HH-mm-ss";
        };
        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(datePattern);
        String currentSuffix = LocalDateTime.now().format(fmt);
        
        Path currentFile = Path.of(baseFileName + "_" + currentSuffix + extension);

        try {
            Files.writeString(currentFile, formattedMessage + System.lineSeparator(), 
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("Ошибка ротации/записи в файл: " + e.getMessage());
        }
    }
}
