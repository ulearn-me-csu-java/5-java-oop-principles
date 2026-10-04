package com.example.task04;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {

    private final String directory;
    private final long rotationInterval;
    private final ChronoUnit rotationUnit;

    private LocalDateTime nextRotation;
    private String currentFileName;

    private static final DateTimeFormatter FILE_DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    public RotationFileHandler(
            String directory,
            long rotationInterval,
            ChronoUnit rotationUnit) {

        this.directory = directory;
        this.rotationInterval = rotationInterval;
        this.rotationUnit = rotationUnit;

        File dir = new File(directory);

        if (!dir.exists()) {
            dir.mkdirs();
        }

        createNewFile();
    }

    private void createNewFile() {
        LocalDateTime now = LocalDateTime.now();

        currentFileName = directory
                + File.separator
                + "log_"
                + now.format(FILE_DATE_FORMAT)
                + ".txt";

        nextRotation = now.plus(rotationInterval, rotationUnit);
    }

    @Override
    public void handle(String message) {

        LocalDateTime now = LocalDateTime.now();

        if (!now.isBefore(nextRotation)) {
            createNewFile();
        }

        try (PrintWriter writer = new PrintWriter(
                new FileWriter(currentFileName, true))) {

            writer.println(message);

        } catch (IOException e) {
            throw new RuntimeException(
                    "Ошибка записи в файл ротации", e);
        }
    }
}