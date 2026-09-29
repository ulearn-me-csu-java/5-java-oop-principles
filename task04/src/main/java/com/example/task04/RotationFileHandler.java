package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Пишет в файл вида prefix-2026.09.29_14-00-00.log.
 * Время в имени обрезается до интервала ротации (например, до часа),
 * поэтому при смене интервала автоматически начинается новый файл.
 * Поддерживаются интервалы до ChronoUnit.DAYS.
 */
public class RotationFileHandler implements MessageHandler {

    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd_HH-mm-ss");

    private final String prefix;
    private final ChronoUnit interval;

    public RotationFileHandler(String prefix, ChronoUnit interval) {
        this.prefix = prefix;
        this.interval = interval;
    }

    @Override
    public void handle(String message) {
        String time = LocalDateTime.now().truncatedTo(interval).format(FORMAT);
        new FileHandler(prefix + "-" + time + ".log").handle(message);
    }
}
