package com.example.task04;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class RotationFileHandler implements MessageHandler {

    private final String directory;
    private final String nameFile;
    private final DateTimeFormatter formatter;

    private static final DateTimeFormatter DEFAULT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");

    public RotationFileHandler(String directory, String nameFile, DateTimeFormatter formatter) {
        this.directory = directory;
        this.nameFile = nameFile;
        this.formatter = formatter;
    }

    public RotationFileHandler(String directory, String nameFile) {
        this(directory, nameFile, DEFAULT_FORMATTER);
    }

    @Override
    public void handler(String message) {
        String dateTimeNow = LocalDateTime.now().format(formatter);
        String fullFileName = directory + File.separator + nameFile + dateTimeNow + ".log";
        File file = new File(fullFileName);
        file.getParentFile().mkdirs();

        try (FileWriter writer = new FileWriter(file, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException ex) {
            System.err.println("Ошибка записи в файл: " + ex.getMessage());
        }
    }
}