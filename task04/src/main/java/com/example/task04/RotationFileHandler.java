package com.example.task04;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Пишет в файл, имя которого зависит от текущего "периода".
 * Интервал ротации задаётся через ChronoUnit (от NANOS до DAYS),
 * например ChronoUnit.HOURS - новый файл каждый час.
 */
public class RotationFileHandler implements MessageHandler {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");

    private final Path directory;
    private final String prefix;
    private final ChronoUnit interval;

    public RotationFileHandler(Path directory, String prefix, ChronoUnit interval) {
        // fail-fast: truncatedTo не поддерживает единицы больше DAYS
        LocalDateTime.now().truncatedTo(interval);
        this.directory = directory;
        this.prefix = prefix;
        this.interval = interval;
    }

    @Override
    public synchronized void handle(String message) {
        LocalDateTime period = LocalDateTime.now().truncatedTo(interval);
        Path file = directory.resolve(prefix + "_" + FORMAT.format(period) + ".log");
        FileHandler.write(file, message);
    }
}
