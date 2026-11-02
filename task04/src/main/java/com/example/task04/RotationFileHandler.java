package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String filename;
    private final ChronoUnit interval;
    private String currentFilename;
    private FileHandler fileHandler;

    public RotationFileHandler(String filename, ChronoUnit interval) {
        if (interval != ChronoUnit.SECONDS
                && interval != ChronoUnit.MINUTES
                && interval != ChronoUnit.HOURS
                && interval != ChronoUnit.DAYS) {
            throw new IllegalArgumentException(
                    "Supported intervals: SECONDS, MINUTES, HOURS, DAYS");
        }

        this.filename = filename;
        this.interval = interval;
    }

    @Override
    public void handle(String message) {
        String newFilename = getFilename();

        if (!newFilename.equals(currentFilename)) {
            if (fileHandler != null) {
                fileHandler.close();
            }

            currentFilename = newFilename;
            fileHandler = new FileHandler(currentFilename);
        }

        fileHandler.handle(message);
    }

    private String getFilename() {
        LocalDateTime date = LocalDateTime.now().truncatedTo(interval);
        String formattedDate = date.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));

        return filename + "_" + formattedDate + ".log";
    }

    public void close() {
        if (fileHandler != null) {
            fileHandler.close();
        }
    }
}