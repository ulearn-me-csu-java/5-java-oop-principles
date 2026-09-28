package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
    private final String name;
    private Level currentLevel;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");

    private static final Map<String, Logger> loggers = new HashMap<>();
    private final List<MessageHandler> handlers = new ArrayList<>();

    private Logger(String name) {
        this.name = name;
        this.currentLevel = Level.DEBUG;
    }

    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void log(Level level, String message) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            String now = LocalDateTime.now().format(FORMATTER);
            for (MessageHandler handler : handlers) {
                handler.handle(String.format("[%s] %s %s - %s", level.name(), now, name, message));
            }
        }
    }

    public void log(Level level, String format, Object... args) {
        log(level, String.format(format, args));
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
