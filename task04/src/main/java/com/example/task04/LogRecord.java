package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record LogRecord(Level level, String name, LocalDateTime time, String message) {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
    public String format() {
        return String.format("[%s] %s %s - %s", level, time.format(FORMATTER), name, message);
    }
}
