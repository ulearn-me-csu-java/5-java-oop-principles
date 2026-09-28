package com.example.task04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final Path baseDir;
    private final String baseName;
    private final ChronoUnit unit;
    private LocalDateTime lastRotation;
    private Path currentFile;

    public RotationFileHandler(Path baseDir, String baseName, ChronoUnit unit) {
        this.baseDir = baseDir;
        this.baseName = baseName;
        this.unit = unit;
        this.lastRotation = LocalDateTime.now().truncatedTo(unit);
        this.currentFile = makeFile(lastRotation);
    }

    private Path makeFile(LocalDateTime time) {
        String suffix;
        if (unit == ChronoUnit.HOURS) {
            suffix = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH"));
        } else if (unit == ChronoUnit.DAYS) {
            suffix = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } else if (unit == ChronoUnit.MINUTES) {
            suffix = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
        } else {
            suffix = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        }
        return baseDir.resolve(baseName + "_" + suffix + ".log");
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime currentTruncated = now.truncatedTo(unit);
        if (currentTruncated.isAfter(lastRotation)) {
            lastRotation = currentTruncated;
            currentFile = makeFile(now);
        }
        try {
            Files.writeString(currentFile, message + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.err.println("RotationFileHandler error: " + e.getMessage());
        }
    }
}
