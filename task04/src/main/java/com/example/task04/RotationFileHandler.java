package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {

    private final String fileName;
    private final ChronoUnit rotationUnit;

    public RotationFileHandler(String fileName, ChronoUnit rotationUnit) {
        this.fileName = fileName;
        this.rotationUnit = rotationUnit;
    }

    @Override
    public void handle(String message) {
        LocalDateTime time = LocalDateTime.now().truncatedTo(rotationUnit);

        String dateTime = time.format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm")
        );

        String rotatedFileName = fileName + "_" + dateTime + ".log";

        try (FileWriter writer = new FileWriter(rotatedFileName, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл", e);
        }
    }
}