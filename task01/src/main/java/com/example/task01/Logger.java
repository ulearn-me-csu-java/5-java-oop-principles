package com.example.task01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Logger {

    private static final Map<String, Logger> LOGGERS = new ConcurrentHashMap<>();
    private static final DateTimeFormatter DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private final String name;
    private volatile Level level = Level.DEBUG;

    private Logger(String name) {
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

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void debug(String template, Object... args) {
        log(Level.DEBUG, template, args);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void info(String template, Object... args) {
        log(Level.INFO, template, args);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void warning(String template, Object... args) {
        log(Level.WARNING, template, args);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }

    public void error(String template, Object... args) {
        log(Level.ERROR, template, args);
    }

    public void log(Level level, String message) {
        if (level.compareTo(this.level) < 0) {
            return;
        }
        System.out.println(String.format("[%s] %s %s - %s",
                level, LocalDateTime.now().format(DATE_TIME_FORMAT), name, message));
    }

    public void log(Level level, String template, Object... args) {
        if (level.compareTo(this.level) < 0) {
            return; // не форматируем сообщение, если оно всё равно не будет выведено
        }
        log(level, String.format(template, args));
    }
}
