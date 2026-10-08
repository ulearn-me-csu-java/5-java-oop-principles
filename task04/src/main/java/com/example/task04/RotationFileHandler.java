package com.example.task04;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler extends FileHandler {
    private String basePath;
    private ChronoUnit rotationUnit;
    private LocalDateTime lastRotation;
    private static final DateTimeFormatter nameFormat =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public static String buildName(String basePath) {
        LocalDateTime now = LocalDateTime.now();
        return basePath + "-" + now.format(nameFormat) + ".txt";
    }

    public RotationFileHandler(String basePath, ChronoUnit rotationUnit) throws IOException {
        super(Path.of(buildName(basePath)));
        this.basePath = basePath;
        this.lastRotation = LocalDateTime.now();
        this.rotationUnit = rotationUnit;
    }

    private boolean shouldRotate() {
        LocalDateTime now = LocalDateTime.now();
        if (rotationUnit == null) {
            return false;
        }
        long diff = rotationUnit.between(lastRotation, now);
        return diff >= 1;
    }

    public void rotate() {
        try {
            close();
        } catch (IOException e) {
            System.err.println("Не удалось закрыть файл: " + e.getMessage());
        }
        String newName = buildName(basePath);
        open(Path.of(newName));
        lastRotation = LocalDateTime.now();
    }

    @Override
    public void handle(String message) {
        if (shouldRotate()) {
            rotate();
        }
        super.handle(message);

    }
}
