package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String fileNamePrefix;
    private final ChronoUnit chronoUnit;

    public RotationFileHandler(String fileNamePrefix, ChronoUnit chronoUnit) {
        this.fileNamePrefix = fileNamePrefix;
        this.chronoUnit = chronoUnit;
    }

    public RotationFileHandler(String fileNamePrefix) {
        this(fileNamePrefix, ChronoUnit.HOURS);
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now().truncatedTo(chronoUnit);
        String fileName = fileNamePrefix + "_" + now.format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".log";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
