package com.example.task04;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {

    private final String baseName;
    private final ChronoUnit rotationUnit;
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public RotationFileHandler(String baseName, ChronoUnit rotationUnit) {
        this.baseName = baseName;
        this.rotationUnit = rotationUnit;
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime rounded = now.truncatedTo(rotationUnit);
        String fileName = baseName + "_" + rounded.format(FORMAT) + ".log";
        Path path = Path.of(fileName);

        try {
            Files.writeString(path, message + System.lineSeparator(),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}