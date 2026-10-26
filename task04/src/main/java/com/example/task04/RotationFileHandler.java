package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String baseFileName;
    private final ChronoUnit rotationInterval;
    private String currentFileName;
    private LocalDateTime lastRotationTime;

    public RotationFileHandler(String baseFileName, ChronoUnit rotationInterval) {
        this.baseFileName = baseFileName;
        this.rotationInterval = rotationInterval;
        this.lastRotationTime = LocalDateTime.now();
        updateFileName();
    }

    private void updateFileName() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        currentFileName = baseFileName + "_" + lastRotationTime.format(fmt) + ".log";
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now();
        // Проверяем, не пора ли создать новый файл (прошло ли достаточно времени)
        if (lastRotationTime.plus(1, rotationInterval).isBefore(now)) {
            lastRotationTime = now;
            updateFileName();
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(currentFileName, true))) {
            writer.println(message);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать в файл " + currentFileName, e);
        }
    }
}