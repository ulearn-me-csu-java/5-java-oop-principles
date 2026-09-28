package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Обработчик, выводящий сообщения в файл с ротацией
 */
public class RotationFileHandler implements MessageHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH");

    private final String baseName;
    private final ChronoUnit rotationUnit;

    public RotationFileHandler(String baseName, ChronoUnit rotationUnit) {
        this.baseName = baseName;
        this.rotationUnit = rotationUnit;
    }

    private String currentFileName() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime truncated;
        switch (rotationUnit) {
            case HOURS:
                truncated = now.truncatedTo(ChronoUnit.HOURS);
                break;
            case DAYS:
                truncated = now.truncatedTo(ChronoUnit.DAYS);
                break;
            case MINUTES:
                truncated = now.truncatedTo(ChronoUnit.MINUTES);
                break;
            default:
                truncated = now;
        }
        return baseName + "_" + truncated.format(FORMATTER) + ".log";
    }

    @Override
    public void handle(String message) {
        try (FileWriter writer = new FileWriter(currentFileName(), true)) {
            writer.write(message);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
