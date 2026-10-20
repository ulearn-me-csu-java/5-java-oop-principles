package com.example.task04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String baseFileName;
    private final ChronoUnit rotationUnit;
    private final DateTimeFormatter formatter;

    public RotationFileHandler(String baseFileName, ChronoUnit rotationUnit) {
        this.baseFileName = baseFileName;
        this.rotationUnit = rotationUnit;
        // Формат имени файла зависит от единицы ротации
        if (rotationUnit == ChronoUnit.HOURS) {
            this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH");
        } else if (rotationUnit == ChronoUnit.DAYS) {
            this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        } else if (rotationUnit == ChronoUnit.MINUTES) {
            this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
        } else {
            this.formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        }
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now();
        // Обрезаем время до нужной единицы (например, до начала часа)
        LocalDateTime truncatedNow = now.truncatedTo(rotationUnit);
        String timeSuffix = truncatedNow.format(formatter);

        // Формируем имя файла, например: log_2023-10-25_14.txt
        String fileName = baseFileName + "_" + timeSuffix + ".txt";
        Path path = Paths.get(fileName);

        try {
            Files.writeString(path, message + System.lineSeparator(),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать лог в файл " + fileName, e);
        }
    }
}