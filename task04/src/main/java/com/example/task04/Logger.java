package com.example.task04;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {

    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private static final Map<String, Logger> loggers = new HashMap<>();

    private final String name;
    private Level level = Level.DEBUG;
    private final List<MessageHandler> handlers = new ArrayList<>();

    private Logger(String name) {
        this.name = name;
        handlers.add(new ConsoleHandler());
    }

    public String getName() {
        return name;
    }

    public static Logger getLogger(String name) {
        return loggers.computeIfAbsent(name, Logger::new);
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return level;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
    }

    public void log(Level level, String message) {
        if (level.ordinal() < this.level.ordinal()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();

        String date = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        String formattedMessage = String.format(
                "[%s] %s %s %s - %s",
                level, date, time, name, message
        );

        for (MessageHandler handler : handlers) {
            handler.handle(formattedMessage);
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
