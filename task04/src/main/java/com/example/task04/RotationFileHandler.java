package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {

    private final String baseName;
    private final String extension;
    private final ChronoUnit rotationUnit;
    private final DateTimeFormatter timeFormatter;

    public RotationFileHandler(String baseName, String extension, ChronoUnit rotationUnit) {
        this.baseName = baseName;
        this.extension = extension;
        this.rotationUnit = rotationUnit;
        this.timeFormatter = formatterFor(rotationUnit);
    }

    private DateTimeFormatter formatterFor(ChronoUnit unit) {
        return switch (unit) {
            case DAYS   -> DateTimeFormatter.ofPattern("yyyy-MM-dd");
            case HOURS  -> DateTimeFormatter.ofPattern("yyyy-MM-dd_HH");
            case MINUTES-> DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm");
            default     -> DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        };
    }

    private Path currentFilePath() {
        LocalDateTime truncated = LocalDateTime.now().truncatedTo(rotationUnit);
        String suffix = truncated.format(timeFormatter);
        return Path.of(baseName + "_" + suffix + extension);
    }

    @Override
    public void handle(Logger.Level level, String formattedMessage) {
        Path path = currentFilePath();
        try (FileWriter writer = new FileWriter(path.toFile(), true)) {
            writer.write(formattedMessage);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            System.err.println("Не удалось записать в файл " + path + ": " + e.getMessage());
        }
    }


}
