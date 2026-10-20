package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final ChronoUnit unit;

    public RotationFileHandler(ChronoUnit unit) {
        this.unit = unit;
    }

    public RotationFileHandler() {
        this(ChronoUnit.HOURS);
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now().truncatedTo(unit);
        String fileName = String.format("log_%s.txt", now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss")));
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
