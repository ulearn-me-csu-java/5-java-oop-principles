package com.example.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private String name;
    private String directory;
    private final ChronoUnit rotationUnit;
    private LocalDateTime lastRotationTime;
    private File currentFile;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public RotationFileHandler(String name, ChronoUnit rotationUnit) {
        this(new java.io.File(".").getAbsolutePath(), name, rotationUnit);
    }
    public RotationFileHandler(String directory, String name, ChronoUnit rotationUnit) {
        this.directory = directory;
        this.name = name;
        this.rotationUnit = rotationUnit;
        this.lastRotationTime = LocalDateTime.now();
        createNewFile();
    }

    private void createNewFile() {
        String timestamp = lastRotationTime.format(formatter);
        currentFile = new File(directory, name + "-" + timestamp + ".log");
        try {
            currentFile.getParentFile().mkdirs();
            currentFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void checkRotation() {
        LocalDateTime now = LocalDateTime.now();
        long elapsed = rotationUnit.between(lastRotationTime, now);
        if (elapsed >= 1) {
            lastRotationTime = now;
            createNewFile();
        }
    }

    @Override
    public void handle(String message) {
        checkRotation();
        try (FileWriter writer = new FileWriter(currentFile, true)) {
            writer.write(message + "\n");
            System.out.println("Сообщение успешно записано в файл!");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}