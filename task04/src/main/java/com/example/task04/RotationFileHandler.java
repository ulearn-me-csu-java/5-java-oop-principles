package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String prefix;
    private final ChronoUnit unit;
    private final DateTimeFormatter fmt;

    private String currentKey;
    private FileHandler current;

    public RotationFileHandler(String prefix, ChronoUnit unit) {
        this.prefix = prefix;
        this.unit = unit;
        this.fmt = DateTimeFormatter.ofPattern(pattern(unit));
    }

    private static String pattern(ChronoUnit unit) {
        if (unit == ChronoUnit.SECONDS) return "yyyy-MM-dd_HH-mm-ss";
        if (unit == ChronoUnit.MINUTES) return "yyyy-MM-dd_HH-mm";
        if (unit == ChronoUnit.HOURS)   return "yyyy-MM-dd_HH";
        if (unit == ChronoUnit.DAYS)    return "yyyy-MM-dd";
        throw new IllegalArgumentException("Wrong unit: " + unit);
    }

    @Override
    public void handle(String message) {
        String key = LocalDateTime.now().truncatedTo(unit).format(fmt);
        if (!key.equals(currentKey)) {
            if (current != null) current.close();
            current = new FileHandler(prefix + "_" + key + ".log");
            currentKey = key;
        }
        current.handle(message);
    }

    public void close() {
        if (current != null) current.close();
    }
}
