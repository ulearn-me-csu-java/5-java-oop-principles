package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String baseFileName;
    private final ChronoUnit rotationUnit;

    public RotationFileHandler(String baseFileName, ChronoUnit rotationUnit) {
        this.baseFileName = baseFileName;
        this.rotationUnit = rotationUnit;
    }

    @Override
    public void handle(String formattedMessage) {
        // Обрезаем текущее время до нужного интервала (например, до часов)
        LocalDateTime now = LocalDateTime.now().truncatedTo(rotationUnit);
        // Создаем имя файла на основе времени: log_2023-10-27T14-00.log
        String timeString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm"));
        String currentFileName = baseFileName + "_" + timeString + ".log";

        try (FileWriter writer = new FileWriter(currentFileName, true)) {
            writer.write(formattedMessage + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
