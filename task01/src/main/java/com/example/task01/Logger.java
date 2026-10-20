package com.example.task01;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    private static final Map<String, Logger> LOGGERS = new HashMap<>();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private final String name;
    private Level level = Level.DEBUG;

    public Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        return LOGGERS.computeIfAbsent(name, Logger::new);
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void log(Level level, String message) {
        if (level.ordinal() >= this.level.ordinal()) {
            String now = LocalDateTime.now().format(FORMATTER);
            System.out.println(String.format("[%s] %s %s - %s", level, now, name, message));
        }
    }

    public void log(Level level, String format, Object... args) {
        if (level.ordinal() >= this.level.ordinal()) {
            String message;
            try {
                message = MessageFormat.format(format, args);
            } catch (Exception e) {
                message = String.format(format, args);
            }
            log(level, message);
        }
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        log(Level.DEBUG, format, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String format, Object... args) {
        log(Level.INFO, format, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String format, Object... args) {
        log(Level.WARNING, format, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String format, Object... args) {
        log(Level.ERROR, format, args);
    }
}
