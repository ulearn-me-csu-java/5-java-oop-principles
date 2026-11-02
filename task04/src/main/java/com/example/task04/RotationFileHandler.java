package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {

    private final String fileNamePrefix;
    private final long interval;
    private final ChronoUnit unit;

    public RotationFileHandler(String fileNamePrefix, long interval, ChronoUnit unit) {
        if (interval <= 0 || unit.getDuration().isZero()
                || unit.getDuration().isNegative()
                || unit.compareTo(ChronoUnit.DAYS) > 0) {
            throw new IllegalArgumentException("Некорректный интервал ротации");
        }

        this.fileNamePrefix = fileNamePrefix;
        this.interval = interval;
        this.unit = unit;
    }

    @Override
    public void handle(String message) {
        long intervalMillis = unit.getDuration().toMillis() * interval;
        long currentTime = Instant.now().toEpochMilli();

        long period = currentTime / intervalMillis;
        long periodStart = period * intervalMillis;

        String fileName = fileNamePrefix + "_"
                + Instant.ofEpochMilli(periodStart).toString().replace(":", "-")
                + ".log";

        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message + System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи в файл", e);
        }
    }
}