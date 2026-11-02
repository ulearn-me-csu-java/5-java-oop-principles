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
    private final List<MessageHandler> handlers = new ArrayList<>();
    private Level level = Level.DEBUG;

    private Logger(String name) {
        this.name = name;
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

    private void printMessage(Level messageLevel, String message) {
        if (messageLevel.ordinal() < level.ordinal()) {
            return;
        }

        LocalDateTime now = LocalDateTime.now();
        String date = now.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        String formattedMessage = "[" + messageLevel + "] " + date + " "
                + time + " " + name + " - " + message;

        for (MessageHandler handler : handlers) {
            handler.handle(formattedMessage);
        }
    }

    private String format(String pattern, Object... args) {
        return String.format(pattern, args);
    }

    public void debug(String message) {
        printMessage(Level.DEBUG, message);
    }

    public void debug(String pattern, Object... args) {
        debug(format(pattern, args));
    }

    public void info(String message) {
        printMessage(Level.INFO, message);
    }

    public void info(String pattern, Object... args) {
        info(format(pattern, args));
    }

    public void warning(String message) {
        printMessage(Level.WARNING, message);
    }

    public void warning(String pattern, Object... args) {
        warning(format(pattern, args));
    }

    public void error(String message) {
        printMessage(Level.ERROR, message);
    }

    public void error(String pattern, Object... args) {
        error(format(pattern, args));
    }

    public void log(Level level, String message) {
        printMessage(level, message);
    }

    public void log(Level level, String pattern, Object... args) {
        log(level, format(pattern, args));
    }
}